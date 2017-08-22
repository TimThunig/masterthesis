package org.camunda.bpm.cockpit.plugin.sample.resources.News;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.FeedDto;
import org.camunda.bpm.cockpit.plugin.sample.dto.NewsDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 07.08.2017.
 */
public class FeedResource extends AbstractCockpitPluginResource {

    private String profileId;
    private FeedDto feed;
    private int max;
    private int objectKey;
    private String type;
    private Map<String, Object> params;

    public FeedResource(String engineName, String profileId , int max) {
        super(engineName);
        this.profileId = profileId;
        this.max = max;
    }

    public FeedResource(String engineName, FeedDto feed) {
        super(engineName);
        this.feed = feed;
    }

    public FeedResource(String engineName, String profileId , int objectKey, String type) {
        super(engineName);
        this.profileId = profileId;
        this.objectKey = objectKey;
        this.type = type;
    }

    @GET
    public List<NewsDto> getFeed() {

        Command<List<NewsDto>> command = new Command<List<NewsDto>>() {
            @SuppressWarnings("unchecked")
            public List<NewsDto> execute(CommandContext commandContext) {
                return (List<NewsDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.selectFeed", new ListQueryParameterObject(profileId, 0, max));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @POST
    public List<NewsDto> postFeed() {

        Command<List<NewsDto>> command = new Command<List<NewsDto>>() {
            @SuppressWarnings("unchecked")
            public List<NewsDto> execute(CommandContext commandContext) {
                return (List<NewsDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertFeed", new ListQueryParameterObject(feed, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @DELETE
    public List<NewsDto> deleteFeed() {

        params = new HashMap<String,Object>();

        params.put("profileId",Integer.valueOf(profileId));
        params.put("objectKey", objectKey);
        params.put("type",type);

        Command<List<NewsDto>> command = new Command<List<NewsDto>>() {
            @SuppressWarnings("unchecked")
            public List<NewsDto> execute(CommandContext commandContext) {
                return (List<NewsDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.deleteFeed", new ListQueryParameterObject(params, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }


}

