package org.camunda.bpm.cockpit.plugin.sample.resources.SocialProfile;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.SocialProfileDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by Tim on 03.08.2017.
 */
public class SocialProfileResource extends AbstractCockpitPluginResource {

    private String userId;
    private SocialProfileDto user;

    public SocialProfileResource(String engineName, String userId) {
        super(engineName);
        this.userId = userId;
    }

    public SocialProfileResource(String engineName, SocialProfileDto user) {
        super(engineName);
        this.user = user;
    }

    @GET
    public List<SocialProfileDto> getSocialProfile() {

        Command<List<SocialProfileDto>> command = new Command<List<SocialProfileDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialProfileDto> execute(CommandContext commandContext) {
                return (List<SocialProfileDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.selectSocialProfile", new ListQueryParameterObject(userId, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @POST
    public List<SocialProfileDto> postSocialProfile() {

         Command<List<SocialProfileDto>> command = new Command<List<SocialProfileDto>>() {
            @SuppressWarnings("unchecked")
           public List<SocialProfileDto> execute(CommandContext commandContext) {
                return (List<SocialProfileDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertSocialProfile", new ListQueryParameterObject(user, 0, 2147483647));
           }
       };
        return getCommandExecutor().executeCommand(command);
    }
}
