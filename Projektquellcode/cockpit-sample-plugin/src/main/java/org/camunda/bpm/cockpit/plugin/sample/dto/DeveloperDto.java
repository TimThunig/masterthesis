package org.camunda.bpm.cockpit.plugin.sample.dto;

/**
 * Created by Tim on 09.08.2017.
 */
public class DeveloperDto {

    private int id;
    private int socialInfoId;
    private String userId;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSocialInfoId() {
        return socialInfoId;
    }

    public void setSocialInfoId(int socialInfoId) {
        this.socialInfoId = socialInfoId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
