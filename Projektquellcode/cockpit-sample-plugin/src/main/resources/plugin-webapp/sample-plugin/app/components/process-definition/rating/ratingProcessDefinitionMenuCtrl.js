/**
 * Created by Tim on 30.07.2017.
 */
'use strict'
ngDefine('cockpit.plugin.sample-plugin.rating', function(module) {


    module.controller('ratingProcessDefinitionMenuCtrl', ['$scope', '$modal', 'userProfileService', 'socialProcessService', function($scope, $modal, userProfileService, socialProcessService){


        var process = $scope.$parent.processDefinition;

        $scope.openRating  = function () {
            $scope.modalInstance = $modal.open({
                templateUrl: 'ratingModalView',
                controller: 'ratingModalCtrl',
                controllerAs:'ctrl',
                size:'md',
                resolve:{
                    process: function () {
                        return process;
                    }
                }
            });

            $scope.modalInstance.result.then(function() {


            }, function() {
            });
        };

    }]);

});