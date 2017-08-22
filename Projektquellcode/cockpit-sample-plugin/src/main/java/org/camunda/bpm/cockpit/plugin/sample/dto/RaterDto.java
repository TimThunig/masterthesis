package org.camunda.bpm.cockpit.plugin.sample.dto;

/**
 * Created by Tim on 06.08.2017.
 */
public class RaterDto {

    private int id;
    private String raterId;
    private int socialInfoId;
    private double rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaterId() {
        return raterId;
    }

    public void setRaterId(String raterId) {
        this.raterId = raterId;
    }

    public int getSocialInfoId() {
        return socialInfoId;
    }

    public void setSocialInfoId(int socialInfoId) {
        this.socialInfoId = socialInfoId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
