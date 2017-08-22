/**
 * Created by Tim on 02.07.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.services', function(module) {
    module.factory('userProfileService', ['$http', 'Uri', function($http, Uri) {

        var getUserProfile = function (userId) {
            return $http.get(Uri.appUri("plugin://sample-plugin/:engine/social-profile?userId=" + userId));
        };


        var postUserProfile = function (profile) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/social-profile/post"), profile);
        };

        return {
            getUserProfile:getUserProfile,
            postUserProfile:postUserProfile
        };
    }]);
});