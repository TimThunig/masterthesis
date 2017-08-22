/**
 * Created by Tim on 18.06.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.wiki', function (module) {
    module.controller('wikiProcessDefinitionTabCtrl', ['$scope', '$modal', 'wikiService', 'userProfileService', 'feedService', '$location', 'socialProcessService',
        function ($scope, $modal, wikiService, userProfileService, feedService, $location, socialProcessService) {

            var process = $scope.$parent.processDefinition;

            $scope.adminUrl = $location.$$absUrl.replace("cockpit", "admin");
            $scope.adminUrl = $scope.adminUrl.split("process-definition")[0] + "users/";


            $scope.userProfile = {};
            //Get user profile from Server
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
                        })
                    });
                }
            });
            var getWiki = function () {
                wikiService.getWiki(process.id).then(function (data) {
                    $scope.wiki = data.data[0];
                    //Init wiki if no wiki was previously initialized
                    if ($scope.wiki === undefined) {
                        var wiki = {
                            processId: process.id,
                            creatorId: $scope.userProfile.userId,
                            processName: process.key
                        };
                        wikiService.postWiki(wiki).then(function () {
                            wikiService.getWiki(process.id).then(function (data) {
                                $scope.wiki = data.data[0];
                            });
                        });
                    }
                });
            };

            //Initialize social information for process if not existing
            socialProcessService.getSocialProcess(process.id).then(function (data) {
                if(data.data[0] === undefined) {
                    var socialInformation = {
                        processId:process.id,
                        key:process.name,
                        ownerId:$scope.$root.authentication.name
                    };
                    socialProcessService.postSocialInformation(socialInformation).then(function () {
                        getWiki();
                    });
                }
                else{
                    getWiki();
                }
            });


            $scope.createWiki = function () {
                $scope.modalInstance = $modal.open({
                    templateUrl: 'wikiCreationModalView',
                    controller: 'wikiCreationModalCtrl',
                    backdrop: false,
                    size: 'lg',
                    resolve: {
                        isAbstract: function () {
                            return $scope.wiki.parts.length === 0;
                        }
                    }
                });

                $scope.modalInstance.result.then(function (part) {
                    var part = {
                        wikiId: $scope.wiki.id,
                        title: part.title,
                        body: part.body,
                        creatorId: $scope.userProfile.userId
                    };
                    wikiService.postPart(part).then(function (data) {
                        var news = {
                            actorId: $scope.userProfile.userId,
                            type: "wiki",
                            action: "created part",
                            objectKey: $scope.wiki.id,
                            title: part.title,
                            objectRef: $scope.wiki.processId
                        };
                        wikiService.getWiki(process.id).then(function (data) {
                            $scope.wiki = data.data[0];
                        });
                        feedService.postNews(news);
                    });
                });
            };

            $scope.openWikiPart = function (wikiPart) {
                $scope.modalInstance = $modal.open({
                    templateUrl: 'wikiModalView',
                    controller: 'wikiModalCtrl',
                    backdrop: false,
                    size: 'lg',
                    resolve: {
                        wikiPart: function () {
                            return wikiPart;
                        }
                    }
                });

                $scope.modalInstance.result.then(function (newRevision) {
                    var revision = {
                        revision:  wikiPart.history[0].body === null ? 0 :1,
                        partId: wikiPart.id,
                        body: wikiPart.body,
                        modifierId: $scope.userProfile.userId
                    };
                    wikiService.postRevision(revision, {body:newRevision.body, id:wikiPart.id}).then(function () {
                        var news = {
                            actorId: $scope.userProfile.userId,
                            type: "wiki",
                            action: "updated part",
                            objectKey: $scope.wiki.id,
                            title: wikiPart.title,
                            objectRef: $scope.wiki.processId
                        };
                        wikiService.getWiki(process.id).then(function (data) {
                            $scope.wiki = data.data[0];
                        });
                        feedService.postNews(news);
                    });
                });


            }

            $scope.isFollowed = function (report) {
                return $scope.userProfile.followedReports.indexOf(report.id) > -1;
            };

            $scope.follow = function (report) {
                var feed = {
                    profileId: $scope.userProfile.id,
                    objectKey: $scope.wiki.id,
                    type: "wiki"
                };
                if (!$scope.isFollowed(report)) {
                    feedService.follow(feed);
                }
                else {
                    feedService.unFollow(feed);
                }

            };

        }]);
});