angular.module('myApp')
    .controller('adminController', function($scope, $http) {
        $scope.users = [];
        $scope.selectedUser = 0;
        $scope.birthday;



        $scope.getAllUsers = function(){
            $http({
                method: 'GET',
                url: '/getAllUsers'
            }).then(function successCallback(response) {
                console.log(response.data);
                $scope.users = response.data;

            }, function errorCallback(response) {
            });
        };
        $scope.getAllUsers();

        $scope.setUserSelection = function (index) {
            $scope.selectedUser = index;
            if ($scope.users[$scope.selectedUser].dateofBirth !== null) {
                $scope.birthday = new Date($scope.users[$scope.selectedUser].dateofBirth);
            }else{
                $scope.birthday=null
            }
        };

        $scope.saveUserInfo=function(){
            $scope.users[$scope.selectedUser].dateofBirth=$scope.birthday;
            $http({
                method: 'POST',
                url: '/saveprofile',
                headers: {
                    'Content-Type': undefined
                },
                data: $scope.users[$scope.selectedUser]
            }).then(function successCallback(response) {
            }, function errorCallback(response) {
            });
        }


        $scope.banOrUnBanUser=function(){
            $http({
                method: 'POST',
                url: '/banOrUnBanUser',
                headers: {
                    'Content-Type': "application/json"
                },
                data: $scope.users[$scope.selectedUser].id
            }).then(function successCallback(response) {
                $scope.getAllUsers();
            }, function errorCallback(response) {
            });
        }
        $scope.convertDate = function (date) {
            if (typeof date === 'undefined')
                return "";
            var _date = new Date(date);
            var tempDate = _date.toString().split(" ");
            return tempDate[1] + " " + tempDate[2] + " " + tempDate[3];
        };

    });

