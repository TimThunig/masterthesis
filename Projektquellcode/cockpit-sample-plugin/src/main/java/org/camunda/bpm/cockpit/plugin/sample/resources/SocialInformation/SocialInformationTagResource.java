package org.camunda.bpm.cockpit.plugin.sample.resources.SocialInformation;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.SocialInformationDto;
import org.camunda.bpm.cockpit.plugin.sample.dto.TagDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by Tim on 06.08.2017.
 */
public class SocialInformationTagResource extends AbstractCockpitPluginResource {

    private TagDto tag;
    private int tagId;


    public SocialInformationTagResource(String engineName, TagDto tag) {
        super(engineName);
        this.tag = tag;
    }

    public SocialInformationTagResource(String engineName, int tagId) {
        super(engineName);
        this.tagId = tagId;
    }

    @POST
    public List<SocialInformationDto> postTag() {
        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertTag", new ListQueryParameterObject(tag, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @DELETE
    public List<SocialInformationDto> deleteTag() {
        Command<List<SocialInformationDto>> command = new Command<List<SocialInformationDto>>() {
            @SuppressWarnings("unchecked")
            public List<SocialInformationDto> execute(CommandContext commandContext) {
                return (List<SocialInformationDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.deleteTag", new ListQueryParameterObject(tagId, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }
}