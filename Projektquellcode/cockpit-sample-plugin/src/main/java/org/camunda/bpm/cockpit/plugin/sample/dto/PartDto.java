package org.camunda.bpm.cockpit.plugin.sample.dto;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Tim on 06.08.2017.
 */
public class PartDto {

    private int id;
    private int wikiId;
    private String title;
    private String body;
    private Timestamp creationTime;
    private String creatorId;
    private List<RevisionDto> history;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWikiId() {
        return wikiId;
    }

    public void setWikiId(int wikiId) {
        this.wikiId = wikiId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public List<RevisionDto> getHistory() {
        return history;
    }

    public void setHistory(List<RevisionDto> history) {
        this.history = history;
    }
}
