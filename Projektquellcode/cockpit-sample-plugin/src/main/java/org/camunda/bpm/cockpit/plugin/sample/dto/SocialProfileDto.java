package org.camunda.bpm.cockpit.plugin.sample.dto;

import java.util.List;

/**
 * Created by Tim on 03.08.2017.
 */
public class SocialProfileDto {

    private int id;
    private String userId;
    private String userName;
    private String email;
    private List<String> followedWikis;
    private List<String> followedReports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getFollowedWikis() {
        return followedWikis;
    }

    public void setFollowedWikis(List<String> followedWikis) {
        this.followedWikis = followedWikis;
    }

    public List<String> getFollowedReports() {
        return followedReports;
    }

    public void setFollowedReports(List<String> followedReports) {
        this.followedReports = followedReports;
    }
}
