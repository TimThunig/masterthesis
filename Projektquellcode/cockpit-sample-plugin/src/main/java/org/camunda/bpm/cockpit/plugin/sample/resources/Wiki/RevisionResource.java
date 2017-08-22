package org.camunda.bpm.cockpit.plugin.sample.resources.Wiki;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.RevisionDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by Tim on 07.08.2017.
 */
public class RevisionResource extends AbstractCockpitPluginResource {

    private String partId;
    private RevisionDto revision;

    public RevisionResource(String engineName, String partId) {
        super(engineName);
        this.partId = partId;
    }

    public RevisionResource(String engineName, RevisionDto revision) {
        super(engineName);
        this.revision = revision;
    }

    @POST
    public List<RevisionDto> postRevision() {

        Command<List<RevisionDto>> command = new Command<List<RevisionDto>>() {
            @SuppressWarnings("unchecked")
            public List<RevisionDto> execute(CommandContext commandContext) {
                return (List<RevisionDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertRevision", new ListQueryParameterObject(revision, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }
}
