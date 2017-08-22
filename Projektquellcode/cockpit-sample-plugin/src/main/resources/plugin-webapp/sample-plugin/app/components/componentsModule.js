/**
 * Created by Tim on 17.06.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.components', [
    './process-definition/processDefinitionDiagramCtrl',
    'module:cockpit.plugin.sample-plugin.dashboard:./dashboard/dashboardModule',
    'module:cockpit.plugin.sample-plugin.reports:./process-definition/reports/reportsModule',
    'module:cockpit.plugin.sample-plugin.wiki:./process-definition/wiki/wikiModule',
    'module:cockpit.plugin.sample-plugin.rating:./process-definition/rating/ratingModule'
], function(module) {

    var Configuration = function Configuration(ViewsProvider) {

        /* components on cockpit dashboard */

        ViewsProvider.registerDefaultView('cockpit.dashboard', {
            id: 'process-test',
            label: 'Deployed Processes',
            url: 'plugin://sample-plugin/static/app/components/dashboard/dashboard.html',
            dashboardMenuLabel: 'Sample',
            controller: 'dashboardCtrl',

            // make sure we have a higher priority than the default plugin
            priority: 12
        });

        ViewsProvider.registerDefaultView('cockpit.processDefinition.runtime.tab', {
            id: 'report',
            priority: 10,
            controller:'reportsProcessDefinitionTabCtrl',
            label: 'Remarks',
            url: 'plugin://sample-plugin/static/app/components/process-definition/reportsProcessDefinitionTabView.html'
        });

        ViewsProvider.registerDefaultView('cockpit.processDefinition.runtime.tab', {
            id: 'wiki',
            priority: 9,
            controller:'wikiProcessDefinitionTabCtrl',
            label: 'Wiki',
            url: 'plugin://sample-plugin/static/app/components/process-definition/wikiProcessDefinitionTabView.html'
        });

        ViewsProvider.registerDefaultView('cockpit.processDefinition.runtime.action', {
            id: 'process-definition-runtime-info',
            priority: 20,
            url: 'plugin://sample-plugin/static/app/components/process-definition/processDefinitionMenuView.html'
        });


    };

    Configuration.$inject = ['ViewsProvider'];

    module.config(Configuration);

    return module;

});