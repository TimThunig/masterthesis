package org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.DeveloperDto;
import org.camunda.bpm.cockpit.plugin.sample.dto.SocialInformationDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by Tim on 09.08.2017.
 */
public class SocialInformationDeveloperResource extends AbstractCockpitPluginResource {

    private DeveloperDto developer;
    private int developerId;


    public SocialInformationDeveloperResource(String engineName, DeveloperDto developer) {
        super(engineName);
        this.developer = developer;
    }

    public SocialInformationDeveloperResource(String engineName, int developerId) {
        super(engineName);
        this.developerId = developerId;
    }

    @POST
    public List<SocialInformationDto> postDeveloper() {
        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertDeveloper", new ListQueryParameterObject(developer, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @DELETE
    public List<SocialInformationDto> deleteDeveloper() {
        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.deleteDeveloper", new ListQueryParameterObject(developerId, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }
}