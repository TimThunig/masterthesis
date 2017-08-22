package org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.SocialInformationDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.util.List;

/**
 * Created by Tim on 05.08.2017.
 */
public class SocialInformationResource extends AbstractCockpitPluginResource {

    private String processId;
    private SocialInformationDto socialInformation;

    public SocialInformationResource(String engineName, String processId) {
        super(engineName);
        this.processId = processId;
    }

    public SocialInformationResource(String engineName, SocialInformationDto socialInformation) {
        super(engineName);
        this.socialInformation = socialInformation;
    }


    @GET
    public List<SocialInformationDto> getSocialInformation() {

        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.selectSocialInformation", new ListQueryParameterObject(processId, 0, 2147483647));
            }
        };

        return getCommandExecutor().executeCommand(command);
    }

    @POST
    public List<SocialInformationDto> postSocialInformation() {

        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertSocialInformation", new ListQueryParameterObject(socialInformation, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @PUT
    public List<SocialInformationDto> updateSocialInformation() {
        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.updateSocialInformation", new ListQueryParameterObject(socialInformation, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

}
