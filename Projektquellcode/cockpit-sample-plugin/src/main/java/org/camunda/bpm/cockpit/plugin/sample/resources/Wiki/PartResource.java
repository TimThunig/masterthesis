package org.camunda.bpm.cockpit.plugin.sample.resources.Wiki;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.PartDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.util.List;

/**
 * Created by Tim on 07.08.2017.
 */
public class PartResource extends AbstractCockpitPluginResource {

    private PartDto part;

    public PartResource(String engineName, PartDto part) {
        super(engineName);
        this.part = part;
    }

    @POST
    public List<PartDto> postPart() {

        Command<List<PartDto>> command = new Command<List<PartDto>>() {
            @SuppressWarnings("unchecked")
            public List<PartDto> execute(CommandContext commandContext) {
                return (List<PartDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertPart", new ListQueryParameterObject(part, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @PUT
    public List<PartDto> updatePart() {

        Command<List<PartDto>> command = new Command<List<PartDto>>() {
            @SuppressWarnings("unchecked")
            public List<PartDto> execute(CommandContext commandContext) {
                return (List<PartDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.updatePart", new ListQueryParameterObject(part, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }
}

