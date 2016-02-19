/**
 * Created by Alex on 18.02.2016.
 */
angular.module('myApp')
.controller('startPageController', function($scope, $http) {
    $scope.articles = [];
    $scope.popArticles=[];
    $scope.postListLen = 0;
    $scope.search="";
    $scope.getAllPosts = function () {
        $http({
            method: 'GET',
            url: '/getAllPosts'
        }).then(function successCallback(response) {
            $scope.articles = response.data;
        }, function errorCallback(response) {

        });
    };
    $scope.getPopularPosts = function () {
        $http({
            method: 'GET',
            url: '/getPopularPosts'
        }).then(function successCallback(response) {
            $scope.popArticles = response.data;
        }, function errorCallback(response) {

        });
    };
    $scope.getPopularPosts();
    $scope.getAllPosts();
    $scope.convertDate = function (date) {
        if (typeof date === 'undefined')
            return "";
        var _date = new Date(date);
        var tempDate = _date.toString().split(" ");
        return tempDate[1] + " " + tempDate[2] + " " + tempDate[3];
    };
    $scope.extendList = function () {
        for (var i = 0; i < 2 && $scope.articles.length > $scope.postListLen; i++) {
            $scope.postListLen++;
        }
    };
    $scope.getCategoryPosts = function (category) {

        $http({
            method: 'POST',
            url: '/getCategoryPosts',
            headers: {
                'Content-Type': "application/json"
            },
            data: category
        }).then(function successCallback(response) {
            $scope.articles=response.data;
        }, function errorCallback(response) {
        });
    };
    $scope.getSearchResults = function () {
        $http({
            method: 'POST',
            url: '/search',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.search
        }).then(function successCallback(response) {
            $scope.articles=response.data;
            console.log($scope.articles)
        }, function errorCallback(response) {
        });
    };
});
