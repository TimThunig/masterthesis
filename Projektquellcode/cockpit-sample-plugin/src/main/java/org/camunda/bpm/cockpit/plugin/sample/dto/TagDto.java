package org.camunda.bpm.cockpit.plugin.sample.dto;

/**
 * Created by Tim on 06.08.2017.
 */
public class TagDto {

    private int id;
    private int socialInfoId;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
