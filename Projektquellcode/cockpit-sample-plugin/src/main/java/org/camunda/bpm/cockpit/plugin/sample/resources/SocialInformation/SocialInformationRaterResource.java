package org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.RaterDto;
import org.camunda.bpm.cockpit.plugin.sample.dto.SocialInformationDto;
import org.camunda.bpm.cockpit.plugin.sample.dto.TagDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.util.List;

/**
 * Created by Tim on 06.08.2017.
 */
public class SocialInformationRaterResource extends AbstractCockpitPluginResource {

    private RaterDto rater;


    public SocialInformationRaterResource(String engineName, RaterDto rater) {
        super(engineName);
        this.rater = rater;
    }

    @PUT
    public List<SocialInformationDto> updateRater() {
        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.updateRater", new ListQueryParameterObject(rater, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @POST
    public List<SocialInformationDto> postRater() {
        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertRater", new ListQueryParameterObject(rater, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

}