/**
 * Created by Tim on 17.06.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.dashboard', function (module) {

    module.controller('dashboardCtrl',
        ['$scope', '$http', 'Uri', '$templateCache', '$modal', 'feedService', 'userProfileService','$location', 'socialProcessService',
            function ($scope, $http, Uri, $templateCache, $modal, feedService, userProfileService,$location,socialProcessService) {

                $scope.adminUrl= $location.$$absUrl.replace("cockpit","admin");
                $scope.adminUrl = $scope.adminUrl.replace("dashboard","") + "users/";
                $scope.stream = {favoriteNews:[], news:[]};


                //Initialize userProfile and news
                userProfileService.getUserProfile($scope.$root.authentication.name).then(function (data) {
                    $scope.userProfile = data.data[0];
                    //Create social profile of no existed
                    if ($scope.userProfile === undefined) {
                        var profile = {
                            userId:$scope.$root.authentication.name,
                            name:$scope.$root.authentication.name
                        };
                        userProfileService.postUserProfile(profile).then(function () {
                            userProfileService.getUserProfile($scope.$root.authentication.name).then(function (data) {
                                $scope.userProfile = data.data[0];
                                //Get feed for user
                                feedService.getFeed($scope.userProfile.id, 10).then(function (data) {
                                    $scope.stream.favoriteNews = data.data;
                                });
                            })
                        });
                    }
                    else{
                        //Get feed for user
                        feedService.getFeed($scope.userProfile.id, 10).then(function (data) {
                            $scope.stream.favoriteNews = data.data;
                        });
                    }
                });
                //Get general news
                feedService.getNews(10).then(function (data) {
                    $scope.stream.news = data.data;
                });
                console.log(window.location);

            }]);

});

