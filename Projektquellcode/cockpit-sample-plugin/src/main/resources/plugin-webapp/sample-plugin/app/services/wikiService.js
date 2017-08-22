/**
 * Created by Tim on 15.07.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.services', function (module) {
    module.factory('wikiService', ['$http', 'Uri', function ($http, Uri) {
        var getWiki = function (processId) {
            return $http.get(Uri.appUri("plugin://sample-plugin/:engine/wiki?processId="+processId));
        };

        var postWiki = function (wiki) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/wiki/post"), wiki);
        };

        var postPart = function (part) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/wiki/part/post"), part);
        };

        var updatePart = function (part) {
            return $http.put(Uri.appUri("plugin://sample-plugin/:engine/wiki/part/update"), part);
        };

        var postRevision = function (revision, part) {
            updatePart(part);
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/wiki/history/post"), revision);
        };

        return {
            postWiki: postWiki,
            postRevision: postRevision,
            getWiki: getWiki,
            postPart:postPart,
            updatePart:updatePart
        };
    }]);
});