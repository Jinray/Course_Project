angular.module('myApp')
    .controller('adminController', function($scope, $http) {
        $scope.users = [];
        $scope.selectedUser = 0;
        $scope.birthday;
        $scope.selectedArticle = 0;
        $scope.editMode = false;
        $scope.isUpdating = false;
        $scope.currentTemplate = 0;
        $scope.text;
        $scope.video;
        $scope.isPictureExist = false;


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
        };


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
        };

        $scope.setUserArticle = function (index) {
            $scope.selectedArticle = index;

            $scope.postImage=" ";
            $scope.title = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].title;
            $scope.currentTemplate = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].template;
            $scope.text = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].text;
            if ($scope.users[$scope.selectedUser].posts[$scope.selectedArticle].image !== null) {
                $scope.postImage = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].image;
                $scope.isPictureExist = true;
                if ($scope.currentTemplate == 1) {
                    $scope.video = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].image
                }
            }
            else {
                $scope.isPictureExist = false;
            }
            CKEDITOR.instances.editor1.setData($scope.text, function () {
                this.checkDirty();  // true
            });
            $scope.category = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].category;
            $scope.date = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].date;
            $scope.tags = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle].tags;
            $("#textbox").trigger("change");
        };

        $scope.convertDate = function (date) {
            if (typeof date === 'undefined')
                return "";
            var _date = new Date(date);
            var tempDate = _date.toString().split(" ");
            return tempDate[1] + " " + tempDate[2] + " " + tempDate[3];
        };

        $scope.deletePostByAdmin=function(postId){
            $http({
                method: 'POST',
                url: '/deletePostByAdmin',
                headers: {
                    'Content-Type': "application/json"
                },
                data: postId
            }).then(function successCallback(response) {
                $scope.getAllUsers();
            }, function errorCallback(response) {
            });
        };

        $scope.toggleEditModeOn = function (index) {
            $scope.editMode = true;
            $scope.setUserArticle(index);
        };

        $scope.toggleEditModeOff = function () {
            $scope.editMode = false;
        };

        $scope.saveChanges = function () {
            $scope.isUpdating = true;
            var post = $scope.users[$scope.selectedUser].posts[$scope.selectedArticle];
            post.title = $scope.title;
            post.text = CKEDITOR.instances.editor1.getData();
            if (post.text.length > 30000) {
                alert("Too mush text")
                $scope.isUpdating = false;
                return;
            }
            post.category = $scope.category;
            post.tags = $scope.tags;
            var isUnique = true;
            for (var i = 0; i < $scope.users[$scope.selectedUser].posts.length; i++) {
                if ($scope.title == $scope.users[$scope.selectedUser].posts[i].title && $scope.title != post.title) {
                    isUnique = false;
                }
            }
            if ($scope.title != "" && isUnique)
                post.title = $scope.title;
            if($scope.currentTemplate==0) {
                $scope.saveImage()
                    .then(function (response) {
                        post.image = response.data.data;
                    }).then(function () {
                }, function () {
                    console.log("empty image")
                }).then(function () {
                    return $http({
                        method: 'POST',
                        url: '/savePostByAdmin',
                        headers: {'Content-Type': undefined},
                        data: post
                    }).then(function successCallback(response) {
                        $scope.isUpdating = false;
                        $scope.toggleEditModeOff();
                    }, function errorCallback(response) {
                        $scope.isUpdating = false;
                        $scope.toggleEditModeOff();
                    });

                })
            }else{
                if (typeof $scope.video === 'undefined' || $scope.video === "") {
                    alert("Please insert video URL");
                    $scope.isUpdating = false;
                    return;
                }
                post.image = $scope.handleYouTube($scope.video);
                $http({
                    method: 'POST',
                    url: '/savePostByAdmin',
                    headers: {'Content-Type': undefined},
                    data: post
                }).then(function successCallback(response) {
                    $scope.isUpdating = false;
                    $scope.toggleEditModeOff();
                }, function errorCallback(response) {
                    $scope.isUpdating = false;
                    $scope.toggleEditModeOff();
                });
            }
        };
        $scope.saveImage = function () {
            return $http({
                method: 'POST',
                url: '/saveimage',
                headers: {
                    'Content-Type': "application/json"
                },
                data: $scope.postImage

            });
        };

        $scope.handleYouTube = function (video) {
            if (typeof video !== 'undefined') {
                $scope.readyYouTube = video.replace('https://www.youtube.com/watch?v=','');
                $scope.readyYouTube = $scope.readyYouTube.replace('https://www.youtube.com/embed/','');
                $scope.readyYouTube.replace('&index=18&', '?');
                $scope.readyYouTube.replace('&', '?');
                $scope.readyYouTube = "https://www.youtube.com/embed/" + $scope.readyYouTube;
                return $scope.readyYouTube;
            }
            return "";
        };
    });

