/**
 * Created by Tim on 23.07.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.services', function (module) {
    module.factory('feedService', ['$http', 'Uri', function ($http, Uri) {

        var getNews = function (max) {
            max = max !== undefined ? max : 20;
            return $http.get(Uri.appUri("plugin://sample-plugin/:engine/news?max=" + max));
        };

        var postNews = function (news) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/news/post"), news);
        };

        var getFeed = function (profileId, max) {
            max = max !== undefined ? max : 20;
            return $http.get(Uri.appUri("plugin://sample-plugin/:engine/feed?profileId="+profileId+"&dmax=" + max));
        };

        var follow = function (feed) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/feed/post"), feed);
        };

        var unFollow = function (feed) {
            return $http.delete(Uri.appUri("plugin://sample-plugin/:engine/feed/delete?profileId="+feed.profileId.toString()+"&objectKey="+feed.objectKey+"&type="+feed.type));
        };

        var sendMail = function () {
            $http.post(Uri.appUri("plugin://sample-plugin/:engine/email"));
        };

        return {
            getNews: getNews,
            postNews: postNews,
            getFeed: getFeed,
            follow:follow,
            unFollow:unFollow,
            sendMail:sendMail
        };
    }]);
});