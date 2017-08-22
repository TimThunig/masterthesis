/**
 * Created by Tim on 30.07.2017.
 */
/**
 * Created by Tim on 15.07.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.services', function (module) {
    module.factory('socialProcessService', ['$http', 'Uri', function ($http, Uri) {


        var getSocialProcess = function (processId) {
             return $http.get(Uri.appUri("plugin://sample-plugin/:engine/social-information?processId="+processId));
        };

        var postSocialInformation = function (socialInformation) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/social-information/post"),socialInformation);
        };

        var updateSocialInformation = function (socialInformation) {
            return $http.put(Uri.appUri("plugin://sample-plugin/:engine/social-information/update"), socialInformation);
        };

        var rateForProcess = function (socialInformation, newRating, user) {
            var oldRating = 0;
            var raterCount = socialInformation.raters.length;
            for(var i = 0; i < raterCount ; i++) {
                //Update exisitng rater
                if(socialInformation.raters[i].raterId === user.userId) {
                    oldRating = socialInformation.raters[i].rating;
                    socialInformation.raters[i].rating = newRating;
                    $http.put(Uri.appUri("plugin://sample-plugin/:engine/social-information/rater/update"), socialInformation.raters[i]);
                }
            }
            //New rater
            if(oldRating === 0 && newRating !== 0){
                var rater = {
                    socialInfoId: socialInformation.id,
                    raterId: user.userId,
                    rating: newRating
                };
                raterCount++;
                $http.post(Uri.appUri("plugin://sample-plugin/:engine/social-information/rater"), rater);
            }
            socialInformation.ratingAbsolute = socialInformation.ratingAbsolute - oldRating + newRating;
            socialInformation.ratingValue = socialInformation.ratingAbsolute/raterCount;
            return $http.put(Uri.appUri("plugin://sample-plugin/:engine/social-information/update"), socialInformation);
        };

        var addTagToProcess = function (socialInformation, tagName) {
            var tag = {
                socialInfoId:socialInformation.id,
                name:tagName
            };
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/social-information/tag"), tag);
        };

        var deleteTagFromProcess = function (process, tag) {
            return $http.delete(Uri.appUri("plugin://sample-plugin/:engine/social-information/tag/delete?tagId=" + tag.id));
        };

        var addDeveloper = function (dev) {
            return $http.post(Uri.appUri("plugin://sample-plugin/:engine/social-information/developer/post"),dev);
        };

        var deleteDeveloper = function (dev) {
            return $http.delete(Uri.appUri("plugin://sample-plugin/:engine/social-information/developer/delete?developerId=" + dev.id));
        };

        return {
            postSocialInformation:postSocialInformation,
            getSocialProcess: getSocialProcess,
            updateSocialInformation:updateSocialInformation,
            rateForProcess:rateForProcess,
            addTagToProcess:addTagToProcess,
            deleteTagFromProcess:deleteTagFromProcess,
            addDeveloper:addDeveloper,
            deleteDeveloper:deleteDeveloper
        };
    }]);
});