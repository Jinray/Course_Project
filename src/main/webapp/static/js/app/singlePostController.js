/**
 * Created by Alex on 18.02.2016.
 */
angular.module('myApp')
.controller('singlePostController',function($scope,$http,$location){

    $scope.post={};
    $scope.userName;
    $scope.scoreRating=0;
    $scope.userId;
    $scope.isMarked = false;
    $scope.isPositive = false;
    $scope.comments=[];
    $scope.commentPost={};
    var url=$location.absUrl().substring(0,$location.absUrl().indexOf('?'));
    if(url==="")
        url=$location.absUrl();
    url=url.split('\/');
    $scope.id=url[url.length-1];
    $scope.rating={};
    $scope.rating.postId=$scope.id;
    $scope.getSinglePost = function () {
        $http({
            method: 'POST',
            url: '/getSinglePost',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.id
        }).then(function successCallback(response) {
            $scope.post=response.data;
            var div=document.getElementById('markpost');
            div.innerHTML=$scope.post.text;
        }, function errorCallback(response) {
        });

    };
    $scope.getComments = function () {

        $http({
            method: 'POST',
            url: '/getComments',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.id
        }).then(function successCallback(response) {
            $scope.comments=response.data;
            console.log(response.data)
        }, function errorCallback(response) {
        });

    };
    $scope.getComments();
    $scope.getUserName = function () {

        $http({
            method: 'POST',
            url: '/getUserName',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.id
        }).then(function successCallback(response) {
            $scope.userName=response.data.name;
            $scope.userId=response.data.userId;
        }, function errorCallback(response) {
        });
    };


    $scope.saveComment= function () {

        $scope.commentPost.id=$scope.id;
        $scope.commentPost.text=CKEDITOR.instances.editor1.getData();
        if($scope.commentPost.text.length>5000) {
            alert("Too mush text")
            return;
        }
        $http({
            method: 'POST',
            url: '/saveComment',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.commentPost
        }).then(function successCallback(response) {
            $scope.comments[$scope.comments.length]=response.data;
            $scope.getComments();
        }, function errorCallback(response) {
        });
    }

    $scope.getSinglePost();
    $scope.getUserName();

    $scope.convertDate = function (date) {
        var _date = new Date(date);
        var tempDate = _date.toString().split(" ");
        return tempDate[1] + " " + tempDate[2] + " " + tempDate[3];
    };

    $scope.addRating= function () {
        $scope.rating.positive=true;
        $http({
            method: 'POST',
            url: '/changeRating',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.rating
        }).then(function successCallback(response) {
            $scope.scoreRating=response.data;
            $scope.getPersonalRating();
        }, function errorCallback(response) {
        });
    }
    $scope.getRating= function () {
        $http({
            method: 'POST',
            url: '/getRating',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.id
        }).then(function successCallback(response) {
            $scope.scoreRating=response.data;
        }, function errorCallback(response) {
        });
    }
    $scope.getRating();
    $scope.removeRating= function () {
        $scope.rating.positive=false;
        $http({
            method: 'POST',
            url: '/changeRating',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.rating
        }).then(function successCallback(response) {
            $scope.scoreRating=response.data;
            $scope.getPersonalRating();
        }, function errorCallback(response) {
        });
    }
    //find comment by id
    $scope.bicycle=function(commentId){
        for(var i=0;i<$scope.comments.length;i++){
            if($scope.comments[i].id==commentId){
                return i;
            }
        }
    }
    $scope.changeLikes= function (commentId) {
        $scope.rating.positive=false;
        $http({
            method: 'POST',
            url: '/changeLikes',
            headers: {
                'Content-Type': "application/json"
            },
            data: commentId
        }).then(function successCallback(response) {
            $scope.comments[$scope.bicycle(commentId)].likes=response.data;
        }, function errorCallback(response) {
        });
    }
    $scope.getPersonalRating= function () {
        $http({
            method: 'POST',
            url: '/getPersonalRating',
            headers: {
                'Content-Type': "application/json"
            },
            data: $scope.id
        }).then(function successCallback(response) {
            $scope.personalRating=response.data;
            if ($scope.personalRating == "") {
                $scope.isMarked = false;
                $scope.isPositive = false;
            }
            else {
                $scope.isMarked = true;
                $scope.isPositive = $scope.personalRating.positive;
            }
        }, function errorCallback(response) {
        });
    }
    $scope.getPersonalRating();

})
