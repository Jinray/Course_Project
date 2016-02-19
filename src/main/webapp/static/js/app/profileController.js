/**
 * Created by Alex on 18.02.2016.
 */
angular.module('myApp')
    .controller('profileController', function ($scope, $http) {

        $scope.isUploading = false;

        $scope.getProfile = function () {
            $http({
                method: 'GET',
                url: '/getprofile'
            }).then(function successCallback(response) {
                $scope.profile = response.data;
                $scope.image = $scope.profile.photo;
                if ($scope.profile.dateofBirth != null)
                    $scope.birthday = new Date($scope.profile.dateofBirth);
            }, function errorCallback(response) {
            });
        };
        $scope.getProfile();

        $scope.saveProfile = function () {
            $scope.isUploading = true;
            $scope.profile.dateofBirth = $scope.birthday;
            $scope.saveAvatar();
            $http({
                method: 'POST',
                url: '/saveprofile',
                headers: {
                    'Content-Type': undefined
                },
                data: $scope.profile

            }).then(function successCallback(response) {

            }, function errorCallback(response) {

            });
        }

        $scope.saveAvatar = function () {
            $http({
                method: 'POST',
                url: '/saveavatar',
                headers: {
                    'Content-Type': undefined
                },
                data: $scope.image
            }).then(function successCallback(response) {
                $scope.isUploading = false;
            }, function errorCallback(response) {
                $scope.isUploading = false;

            });
        }
    })
