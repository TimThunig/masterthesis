package org.camunda.bpm.cockpit.plugin.sample.resources.News;

import org.camunda.bpm.cockpit.plugin.resource.AbstractCockpitPluginResource;
import org.camunda.bpm.cockpit.plugin.sample.dto.NewsDto;
import org.camunda.bpm.engine.impl.db.ListQueryParameterObject;
import org.camunda.bpm.engine.impl.interceptor.Command;
import org.camunda.bpm.engine.impl.interceptor.CommandContext;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;

/**
 * Created by Tim on 07.08.2017.
 */
public class NewsResource extends AbstractCockpitPluginResource {

    private NewsDto news;
    private int max;

    public NewsResource(String engineName,  int max) {
        super(engineName);
        this.max = max;
    }

    public NewsResource(String engineName, NewsDto news) {
        super(engineName);
        this.news = news;
    }

    @GET
    public List<NewsDto> getNews() {

        Command<List<NewsDto>> command = new Command<List<NewsDto>>() {
            @SuppressWarnings("unchecked")
            public List<NewsDto> execute(CommandContext commandContext) {
                return (List<NewsDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.selectNews", new ListQueryParameterObject("", 0, max));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

    @POST
    public List<NewsDto> postNews() {

        Command<List<NewsDto>> command = new Command<List<NewsDto>>() {
            @SuppressWarnings("unchecked")
            public List<NewsDto> execute(CommandContext commandContext) {
                return (List<NewsDto>) commandContext.getDbSqlSession().selectList("cockpit.sample.insertNews", new ListQueryParameterObject(news, 0, 2147483647));
            }
        };
        return getCommandExecutor().executeCommand(command);
    }

}
