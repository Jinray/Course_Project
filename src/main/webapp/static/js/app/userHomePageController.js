/**
 * Created by Alex on 19.02.2016.
 */
angular.module('myApp')
    .controller('userHomePageController', function($scope, $http,$location) {
        var url=$location.absUrl().substring(0,$location.absUrl().indexOf('?'));
        if(url==="")
            url=$location.absUrl();
        url=url.split('\/');
        $scope.id=url[url.length-1];

    });
