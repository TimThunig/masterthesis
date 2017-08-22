/**
 * Created by Tim on 15.07.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.wiki', function(module) {


    module.controller('wikiModalCtrl', ['$scope', '$modalInstance', 'wikiPart', '$location', function($scope, $modalInstance, wikiPart, $location){

        $scope.adminUrl = $location.$$absUrl.replace("cockpit", "admin");
        $scope.adminUrl = $scope.adminUrl.split("process-definition")[0] + "admin/users/";
        $scope.wikiPart = angular.copy(wikiPart);
        $scope.newPart = angular.copy(wikiPart);
        $scope.wikiPart.history.push({body:wikiPart.body, revision:wikiPart.history.length});
        $scope.selectedRevision = wikiPart.history[wikiPart.history.length-1];
        $scope.changeRevision = function (history) {
            $scope.selectedRevision = history;
            $scope.newPart.body = angular.copy(history.body);
        };


        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.ok = function () {
            $modalInstance.close($scope.newPart);
        }

    }]);

});