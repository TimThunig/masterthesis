package org.camunda.bpm.cockpit.plugin.sample.resources.Wiki;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.WikiDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by Tim on 06.08.2017.
 */
public class WikiResource extends AbstractCockpitPluginResource {

    private String processId;
    private WikiDto wiki;

    public WikiResource(String engineName, String processId) {
        super(engineName);
        this.processId = processId;
    }

    public WikiResource(String engineName, WikiDto wiki) {
        super(engineName);
        this.wiki = wiki;
    }

    @GET
    public List<WikiDto> getWiki() {

        Command<List<WikiDto>> command = new Command<List<WikiDto>>() {
            @SuppressWarnings("unchecked")
            public List<WikiDto> execute(CommandContext commandContext) {
                return (List<WikiDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.selectWiki", new ListQueryParameterObject(processId, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @POST
    public List<WikiDto> postWiki() {


        Command<List<WikiDto>> command = new Command<List<WikiDto>>() {
            @SuppressWarnings("unchecked")
            public List<WikiDto> execute(CommandContext commandContext) {
                return (List<WikiDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertWiki", new ListQueryParameterObject(wiki, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }
}
