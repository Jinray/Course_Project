<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/commentDirective.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/singlePostController.js"></script>

<div ng-app="myApp" ng-controller="singlePostController" class=" col-md-8"
     style="margin: 0 16.5% 40px 16.5%; border: 1px solid #fff;">
    <div class="page-container col-md-12">
        <div class="panel panel-default">
            <div class="article-header">
                <img ng-src="{{post.image}}" alt="">
            </div>
            <div class="article">

                <div class="article-body">
                    <div class="article-title article-title-1 article-title-font">
                        <a href="#">{{post.title}}</a>
                        <div style="float:right">
                            <sec:authorize access="isAuthenticated()">
                            <a href="javascript:void(0)" ng-click="removeRating()" style="text-decoration: none;">
                                </sec:authorize><span ng-class="{'dislike-active': !isPositive && isMarked}" class="icon-thumbs-down dislike" style="font-size: 1.5em"></span>
                            </a>
                            <label>{{scoreRating}}&nbsp</label>
                            <sec:authorize access="isAuthenticated()">
                            <a href="javascript:void(0)" ng-click="addRating()" style="text-decoration: none;">
                                </sec:authorize><span ng-class="{'like-active': isPositive && isMarked}" class="icon-thumbs-up like" style="font-size: 1.5em"></span></a>
                        </div>
                    </div>
                    <div class="article-info">
                        <spring:message code="label.post.author"/>:
                        <a href="#" class="text-primary">{{userName}}</a>,
                        <spring:message code="label.post.postCategory"/>:
                        <a href="#" class="text-primary">{{post.category}}</a>,
                        <spring:message code="label.post.PostDate"/>: {{convertDate(post.date)}}
                    </div>
                    <div id='markpost'>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="editor" class="panel panel-default col-md-12" style="padding-top: 20px">
        <sec:authorize access="isAuthenticated()">
            <div style="margin-bottom: 15px;">
                <textarea ng-model="text" id="editor1" rows="20" style="width: 100%;"></textarea>
                <button ng-click="saveComment()" style="margin: 0 0 5px 0"
                        class="btn btn-primary btn-primary:hover btn-block">
                    <spring:message code="label.post.publish"/>
                </button>
                <script type="text/javascript">
                    CKEDITOR.replace('editor1',
                            {
                                toolbar: [
                                    {name: 'document', items: ['Preview']},
                                    {name: 'insert', items: ['Smiley']},
                                    {name: 'basicstyles', items: ['Bold', 'Italic', 'Strike', '-', 'RemoveFormat']},
                                    {
                                        name: 'paragraph',
                                        items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote']
                                    },
                                    {name: 'links', items: ['Link', 'Unlink']}
                                ]
                            });
                </script>
            </div>
        </sec:authorize>

        <div ng-repeat="comment in comments" class="col-md-12"
             style="border: 1px solid #ccc; padding: 0 0 0 0; margin-bottom: 15px;">
            <div class="col-md-2" style="padding: 0 0 0 0;">
                <img class="img-thumbnail" width="100%" ng-src="{{comments[$index].user.photo}}"/>
            </div>
            <div class="col-md-10">
                <div style="border-bottom: 1px solid #ccc; padding: 10px 0 10px 0; font-size: 14px">
                    <span>
                        <a href="#" class="text-primary">{{comment.user.firstName}}
                            {{comment.user.lastName}}</a>
                        &nbsp&nbsp&nbsp&nbsp
                        <spring:message code="label.post.PostDate"/>: {{convertDate(comment.date)}}
                    </span>
                    <div style="float: right;">
                        <label> {{comment.likes.length}} </label>
                        <sec:authorize access="isAuthenticated()">
                        <a href="#" ng-click="changeLikes(comment.id)" class="text-primary"></sec:authorize>
                            <span class="icon-heart">
                        </span>
                        </a>

                    </div>
                </div>
                <div>
                    <comment-directive></comment-directive>
                </div>
            </div>
        </div>

    </div>

</div>
</body>
</html>