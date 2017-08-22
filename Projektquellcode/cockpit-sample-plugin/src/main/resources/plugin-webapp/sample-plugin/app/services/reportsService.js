/**
 * Created by Tim on 02.07.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.services', function (module) {
    module.factory('reportsService', ['$http', 'Uri', function ($http, Uri) {

        var getReports = function (processId) {
            return $http.get(Uri.appUri("plugin://sample-plugin/:engine/report?processId="+processId));
        };

        var postReport = function (report) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/report/post"), report);
        };

        var updateReport = function (report) {
            return $http.put(Uri.appUri("plugin://sample-plugin/:engine/report/update"), report);
        };

        var comment = function (comment) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/report/comment/post"), comment);
        };

        return {
            getReports: getReports,
            postReport: postReport,
            updateReport: updateReport,
            comment:comment
        };
    }]);
});