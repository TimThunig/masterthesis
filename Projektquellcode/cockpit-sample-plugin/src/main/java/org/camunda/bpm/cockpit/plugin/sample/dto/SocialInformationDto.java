package org.camunda.bpm.cockpit.plugin.sample.dto;

import java.util.List;

/**
 * Created by Tim on 05.08.2017.
 */
public class SocialInformationDto {

    private int id;
    private String processId;
    private String key;
    private double ratingValue;
    private double ratingAbsolute;
    private List<RaterDto> raters;
    private String ownerId;
    private List<TagDto> tags;
    private List<DeveloperDto> developer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public double getRatingAbsolute() {
        return ratingAbsolute;
    }

    public void setRatingAbsolute(double ratingAbsolute) {
        this.ratingAbsolute = ratingAbsolute;
    }

    public List<RaterDto> getRaters() {
        return raters;
    }

    public void setRaters(List<RaterDto> raters) {
        this.raters = raters;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public List<DeveloperDto> getDeveloper() {
        return developer;
    }

    public void setDeveloper(List<DeveloperDto> developer) {
        this.developer = developer;
    }
}
