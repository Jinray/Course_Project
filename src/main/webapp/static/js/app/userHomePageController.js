/**
 * Created by Alex on 19.02.2016.
 */
angular.module('myApp')
    .controller('userHomePageController', function ($scope, $http, $location) {

        var url = $location.absUrl().substring(0, $location.absUrl().indexOf('?'));
        if (url === "")
            url = $location.absUrl();
        url = url.split('\/');
        $scope.id = url[url.length - 1];
        $scope.profile={};
        $scope.posts = [];
        $scope.postListLen = 0;

        $scope.getUserHomePagePosts = function () {
            return $http({
                method: 'POST',
                url: '/getUserHomePagePosts',
                headers: {
                    'Content-Type': "application/json"
                },
                data: $scope.id
            }).then(function successCallback(response) {
                $scope.posts=response.data;
            }, function errorCallback(response) {
            });
        };
        $scope.getUserInfo = function () {
            return $http({
                method: 'POST',
                url: '/getUserInfo',
                headers: {
                    'Content-Type': "application/json"
                },
                data: $scope.id
            }).then(function successCallback(response) {
                $scope.profile=response.data;
            }, function errorCallback(response) {
            });
        };
        $scope.getUserHomePagePosts();
        $scope.getUserInfo();
        $scope.convertDate = function (date) {
            if (typeof date === 'undefined')
                return "";
            var _date = new Date(date);
            var tempDate = _date.toString().split(" ");
            return tempDate[1] + " " + tempDate[2] + " " + tempDate[3];
        };

        $scope.extendList = function () {
            for (var i = 0; i < 2 && $scope.posts.length > $scope.postListLen; i++) {
                $scope.postListLen++;
            }
        };



})
;
