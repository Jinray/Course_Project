/**
 * Created by Alex on 13.02.2016.
 */
angular.module('myApp')
    .controller('tagsController', function ($scope, $http) {

        $scope.loadTags = function ($query) {
            return $http.get('/get-tags-list/', {cache: true}).then(function (response) {
                var postTags = response.data;
                return postTags.filter(function (tag) {
                    return tag.text.toLowerCase().indexOf($query.toLowerCase()) != -1;
                });
            });
        };


        $scope.getCloudTags=function(){
            $http({
                method: 'GET',
                url: '/getCloudTags'
            }).then(function successCallback(response) {
                $scope.tagsCloud = response.data;
            }, function errorCallback(response) {

            });
        };
        $scope.getCloudTags();
    });

