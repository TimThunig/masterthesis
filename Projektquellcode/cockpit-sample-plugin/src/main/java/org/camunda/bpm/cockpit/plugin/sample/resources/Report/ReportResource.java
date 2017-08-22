package org.camunda.bpm.cockpit.plugin.sample.resources.Report;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
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
public class ReportResource extends AbstractCockpitPluginResource {

    private String processId;
    private ReportDto report;

    public ReportResource(String engineName, String processId) {
        super(engineName);
        this.processId = processId;
    }

    public ReportResource(String engineName, ReportDto report) {
        super(engineName);
        this.report = report;
    }

    @GET
    public List<ReportDto> getReport() {

        Command<List<ReportDto>> command = new Command<List<ReportDto>>() {
            @SuppressWarnings("unchecked")
            public List<ReportDto> execute(CommandContext commandContext) {
                return (List<ReportDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.selectReport", new ListQueryParameterObject(processId, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @POST
    public List<ReportDto> postReport() {

        Command<List<ReportDto>> command = new Command<List<ReportDto>>() {
            @SuppressWarnings("unchecked")
            public List<ReportDto> execute(CommandContext commandContext) {
                return (List<ReportDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertReport", new ListQueryParameterObject(report, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @PUT
    public List<ReportDto> updateReport() {

        Command<List<ReportDto>> command = new Command<List<ReportDto>>() {
            @SuppressWarnings("unchecked")
            public List<ReportDto> execute(CommandContext commandContext) {
                return (List<ReportDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.updateReport", new ListQueryParameterObject(report, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }
}
