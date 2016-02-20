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

        $scope.getCloudTags = function(){
            $http({
                method: 'GET',
                url: '/getCloudTags'
            }).then(function successCallback(response) {
                $scope.tagsCloud = response.data;
                $scope.computeSize();
            }, function errorCallback(response) {

            });
        };
        $scope.getCloudTags();

        $scope.computeSize = function () {
            var max = $scope.tagsCloud[0].weight;
            var min = $scope.tagsCloud[0].weight;

            for (var i in $scope.tagsCloud) {
                if ($scope.tagsCloud[i].weight > max){
                    max = $scope.tagsCloud[i].weight;
                }
                if ($scope.tagsCloud[i].weight < min){
                    min = $scope.tagsCloud[i].weight;
                }
            }

            var minSize = 1;
            var maxSize = 3;

            for (var j in $scope.tagsCloud)
            {
                var tag = $scope.tagsCloud[j];
                var size = tag.weight == min ? minSize
                    : (tag.weight / max) * (maxSize - minSize) + minSize;

                $scope.tagsCloud[j].weight = size;
            }
            console.log($scope.tagsCloud);
        }
    });

