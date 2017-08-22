/**
 * Created by Tim on 24.07.2017.
 */
ngDefine('cockpit.plugin.sample-plugin.wiki', function(module) {


    module.controller('wikiCreationModalCtrl', ['$scope', '$modalInstance', 'isAbstract', function($scope, $modalInstance, isAhstract){

        $scope.isAbstract = isAhstract;

        $scope.part = {
            title:$scope.isAbstract ? "Abstract" : "",
            body:""
        };


        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.ok = function () {
            $modalInstance.close($scope.part);
        }

    }]);

});