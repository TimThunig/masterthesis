package org.camunda.bpm.cockpit.plugin.sample.resources.Email;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Message;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by Tim on 09.08.2017.
 */
public class EmailResource extends AbstractCockpitPluginResource {

    public EmailResource(String engineName) {
        super(engineName);
    }

    @POST
    public void sendEmail() {

        final String username = "username@gmail.com";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(props, null);


        try {

            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("<email_recipient_adress>"));
            message.setFrom(new InternetAddress("<email_sender_adress>"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("<email_recipient_adress>"));
            message.setSubject("<email_subject>");
            String emailBody = "<email_body>";
            message.setContent(emailBody, "text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }


}
