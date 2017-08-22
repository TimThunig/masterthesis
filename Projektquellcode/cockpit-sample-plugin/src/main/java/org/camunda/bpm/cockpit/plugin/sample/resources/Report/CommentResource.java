package org.camunda.bpm.cockpit.plugin.sample.resources.Report;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.CommentDto;
import org.camunda.bpm.cockpit.plugin.sample.dto.ReportDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.util.List;

/**
 * Created by Tim on 07.08.2017.
 */
public class CommentResource extends AbstractCockpitPluginResource {

    private CommentDto comment;

    public CommentResource(String engineName, CommentDto comment) {
        super(engineName);
        this.comment = comment;
    }


    @POST
    public List<CommentDto> postComments() {

        Command<List<CommentDto>> command = new Command<List<CommentDto>>() {
            @SuppressWarnings("unchecked")
            public List<CommentDto> execute(CommandContext commandContext) {
                return (List<CommentDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertComment", new ListQueryParameterObject(comment, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

}

