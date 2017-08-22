/**
 * Created by Tim on 31.07.2017.
 */
'use strict'
ngDefine('cockpit.plugin.sample-plugin.rating', function(module) {


    module.controller('ratingModalCtrl', ['$scope', '$modalInstance', '$http', 'process', 'userProfileService', 'socialProcessService',
        function($scope, $modalInstance, $http, process, userProfileService, socialProcessService){

        var vm = this;
        $scope.unselectedDevs = [];
        $scope.canChangeOwner = false;


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
        var init = function () {
            socialProcessService.getSocialProcess(process.id).then(function (data) {
                $scope.process = data.data[0];
                //Get user to add for developer/owner
                $http.get("http://localhost:8080/camunda/api/engine/engine/default/user").then(function(data) {
                    $scope.users = data.data;
                    $scope.unselectedDevs = []
                    //Get list of possible new developers
                    var breakIteration = false;
                    for(var i = 0 ; i < $scope.users.length ; i++){
                        breakIteration = false;
                        for(var j = 0 ; j < $scope.process.developer.length ; j++){
                            if($scope.users[i].id === $scope.process.developer[j].userId){
                                //When the user was in the dev list remember that
                               breakIteration = true;
                            }
                        }
                        //Skip for existing users
                        if(breakIteration) {
                            continue;
                        }
                        $scope.unselectedDevs.push($scope.users[i]);
                    }

                });
            });
        };
        init();
        //Get user profile from cache
        $scope.userProfile ={};
        if(!angular.equals(userProfileService.dataObject,{})) {
            $scope.userProfile = userProfileService.dataObject;
        }
        //If no profile in cache, load a new one
        else {
            userProfileService.getUserProfile($scope.$root.authentication.name).then(function(data) {
                $scope.userProfile = data.data[0];
            });
        }
        vm.newTag = "";


        $scope.isReadonly = false;

        $scope.changeOwner = function (owner) {
            $scope.process.ownerId = owner.id;
            socialProcessService.updateSocialInformation($scope.process);
            $scope.canChangeOwner = false;
        };

        $scope.addDev = function (dev) {
            var newDev = {
                userId:dev.id,
                socialInfoId:$scope.process.id,
                email:dev.email
            };
            socialProcessService.addDeveloper(newDev).then(function () {
                init();
            });
            $scope.canAddDev = false;
        };

        $scope.deleteDev = function (dev) {
            socialProcessService.deleteDev(dev).then(function () {
               init();
            });
        };

        $scope.rate = function () {
            socialProcessService.rateForProcess($scope.process, $scope.rating, $scope.userProfile).then(function () {
                $scope.isReadonly = true;
                init();
            });

        };

        $scope.addTag = function () {
            socialProcessService.addTagToProcess($scope.process, vm.newTag).then(function () {
                $scope.canAddTag = false;
                init();
            });

        };

        $scope.deleteTag = function (tag) {
            socialProcessService.deleteTagFromProcess($scope.process, tag).then(function () {
                init();
            });

        };

        $scope.ok = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }]);

});