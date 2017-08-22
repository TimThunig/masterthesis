/**
 * Created by Tim on 18.06.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.reports', function(module) {
    module.controller('reportsProcessDefinitionTabCtrl',['$scope', '$http', 'Uri', '$modal',
        'reportsService', 'userProfileService', 'feedService' ,'$location','socialProcessService',
        function($scope,$http, Uri, $modal, reportsService, userProfileService, feedService, $location, socialProcessService) {

        var process = $scope.$parent.processDefinition;
        //http://localhost:8080/camunda/app/cockpit/default/#/process-definition/...
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

            //Initialize social information for process if not existing
            socialProcessService.getSocialProcess(process.id).then(function (data) {
                if(data.data[0] === undefined) {
                    var socialInformation = {
                        processId:process.id,
                        key:process.name,
                        ownerId:$scope.$root.authentication.name
                    };
                    socialProcessService.postSocialInformation(socialInformation);
                }
            });

        //Get reports for the process
        reportsService.getReports(process.id).then(function (data) {
            $scope.reports = data.data;
        });

        var initProfile = function () {
            userProfileService.getUserProfile($scope.$root.authentication.name).then(function(data) {
                $scope.userProfile = data.data[0];
            });
        };


        $scope.typeFilterOptions = [
            {name:"All",filterString:""},
            {name:"Bugs", filterString:"Bug"},
            {name:"Comments", filterString:"Comment"},
            {name:"Feature request", filterString:"Feature request"}
        ];

        $scope.changeTypeFilter = function (filter) {
            $scope.typeFilter = filter;
        };

        $scope.filterTypeFunc = function (report) {
            if($scope.typeFilter.filterString === ""){
                return true;
            }
            return report.type === $scope.typeFilter.filterString;
        };

        $scope.typeFilter = $scope.typeFilterOptions[0];

        $scope.createReport = function () {
            $scope.modalInstance = $modal.open({
                templateUrl: 'reportsModalView',
                controller: 'reportsModalCtrl',
                size: 'lg',
                backdrop: false
            });

            $scope.modalInstance.result.then(function (report) {
                report.status = 0;
                report.reporterId = $scope.userProfile.userId;
                report.processId = process.id;
                reportsService.postReport(report).then(function () {
                    reportsService.getReports(process.id).then(function (data) {
                        $scope.reports = data.data;
                        //Get new report for the ID
                        var newReport = undefined;
                        for(var i = 0 ; i < data.data.length ; i++) {
                            if(data.data[i].title === report.title && data.data[i].message === report.message){
                                newReport = data.data[i];
                            }
                        }
                        var news = {
                            actorId: $scope.userProfile.userId,
                            type: "report",
                            action: "created",
                            objectKey: newReport.id,
                            title: report.topic,
                            objectRef:report.processId
                        };
                        feedService.postNews(news);
                    });
                });

            });
        };

        $scope.showReport = function (report) {
            $scope.modalInstance = $modal.open({
                templateUrl: 'reportsDetailModalView',
                controller: 'reportsDetailModalCtrl',
                backdrop: false,
                size:'lg',
                resolve:{
                    report: function (){
                        return report;
                    },
                    user: function () {
                        return $scope.userProfile;
                    }
                }
            });

            $scope.modalInstance.result.then(function() {
                reportsService.getReports(process.id);
                var news = {
                    actorId: $scope.userProfile.userId,
                    type: "report",
                    action: "commented",
                    objectKey: report.id,
                    title: report.topic,
                    objectRef:report.processId
                };
                feedService.postNews(news);

            });
        };

        $scope.isFollowed = function (report) {
            if($scope.userProfile.followedReports !== undefined) {
                return $scope.userProfile.followedReports.indexOf(report.id.toString()) > -1;
            }
            return false;
        };

        $scope.follow = function (report) {
            var feed = {
                profileId:$scope.userProfile.id,
                objectKey:report.id,
                type:"report"
            } ;
            if(!$scope.isFollowed(report)){
                feedService.follow(feed).then(function () {
                    report.upvotes++;
                    reportsService.updateReport(report).then(function () {
                        reportsService.getReports(process.id);
                    });
                    initProfile()
                });
            }
            else {
                feedService.unFollow(feed).then(function () {
                    report.upvotes--;
                    reportsService.updateReport(report).then(function () {
                        reportsService.getReports(process.id);
                    });
                    initProfile();
                });
            }

        };

    }]);
});