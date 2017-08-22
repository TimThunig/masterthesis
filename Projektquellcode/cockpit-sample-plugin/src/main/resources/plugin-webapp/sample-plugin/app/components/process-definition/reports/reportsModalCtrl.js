/**
 * Created by Tim on 20.06.2017.
 */
'use strict'
ngDefine('cockpit.plugin.sample-plugin.reports', function(module) {


    module.controller('reportsModalCtrl', ['$scope', '$modalInstance', function($scope, $modalInstance){

        $scope.report = {
            comments:[]
        };
        $scope.reportTypes = [
            {name:'Bug',id:1},
            {name:'Comment',id:2},
            {name:'Feature Request',id:3}
        ];
        $scope.selectedType = $scope.reportTypes[1];

        $scope.changeType = function (type) {
            $scope.selectedType = type;
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.ok = function () {
            $scope.report.type = $scope.selectedType.name;
            $modalInstance.close($scope.report);
        }

    }]);

});