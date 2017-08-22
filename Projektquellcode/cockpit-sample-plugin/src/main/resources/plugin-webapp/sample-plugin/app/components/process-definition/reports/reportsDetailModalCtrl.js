/**
 * Created by Tim on 20.06.2017.
 */
'use strict'
ngDefine('cockpit.plugin.sample-plugin.reports', function(module) {


    module.controller('reportsDetailModalCtrl', ['$scope', '$modalInstance','report', 'user', 'reportsService', '$location', function($scope, $modalInstance, report, user, reportsService, $location){


        $scope.adminUrl = $location.$$absUrl.replace("cockpit", "admin");
        $scope.adminUrl = $scope.adminUrl.split("process-definition")[0] + "users/";
        $scope.newComment = "";
        $scope.report = angular.copy(report);

        $scope.write = function (com) {
            $scope.newComment = com;
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.addComment = function () {
            var comment = {
                reporterId: user.userId,
                reportId:report.id,
                message:$scope.newComment
            };
            reportsService.comment(comment).then(function () {
                $scope.newComment = "";
                comment.creationTime = new Date().toDateString();
                $scope.report.comments.push(comment);
            });
        };

        $scope.writeOut = function () {
            reportsService.updateReport(report);
            $modalInstance.close($scope.report);
        }

    }]);

});