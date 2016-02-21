angular.module('myApp')
    .controller('TrustController', function ($scope, $sce) {
        $scope.trustSrc = function (src) {
            return $sce.trustAsResourceUrl(src);
        };
    });
