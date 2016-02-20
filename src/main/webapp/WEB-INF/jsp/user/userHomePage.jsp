<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Title</title>
</head>

<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/userHomePageController.js"></script>

<div>
    <div ng-app="myApp" ng-controller="userHomePageController">
        <div class="col-md-9" style="margin: 0 12.5% 0 12.5%">
            <div class="row">
                <div class="col-md-9 col-md-push-3">
                    <div infinite-scroll='extendList()' infinite-scroll-disabled='busy'>
                        <div ng-repeat="post in posts" ng-hide="$index > postListLen+4">
                            <div class="panel panel-default">
                                <div class="article-header">
                                    <img ng-src="{{post.image}}" alt="">
                                </div>
                                <div class="article">
                                    <div class="article-body">
                                        <a href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                           class="article-title article-title-1 article-title-font">
                                            {{post.title}}
                                        </a>
                                        <div class="article-info">

                                            <spring:message code="label.post.postCategory"/>:
                                            <a href="${pageContext.request.contextPath}/user/login/category={{post.category}}"
                                               class="text-primary">{{post.category}}</a>,
                                            <spring:message code="label.post.PostDate"/>: {{convertDate(post.date)}}
                                            <div style="float: right;"><a
                                                    href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                                    class="text-primary">
                                                <i class="icon-comment"></i> {{post.comments.length}}
                                                <spring:message
                                                        code="label.post.comments"/></a></div>
                                        </div>

                                        <div>
                                            <a ng-repeat="tag in post.tags"
                                               href="${pageContext.request.contextPath}/user/login/tags={{post.tags[$index].text}}"
                                               class="tag label label-primary" style="margin-right: 4px;">
                                                {{post.tags[$index].text}}
                                            </a>
                                        </div>
                                        <hr>

                                        <div style="max-height: 400px; text-overflow:ellipsis; overflow:hidden;">
                                            <posttext-directive></posttext-directive>
                                        </div>
                                        <div class="article-info">
                                            <a href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                               class="text-primary"><spring:message code="label.post.readMore"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="col-md-3 col-md-pull-9">
                    <div style="width: 100%; text-align: center">
                        <img alt="Avatar" ng-src="{{profile.photo}}"
                             class="img-thumbnail">
                    </div>
                    <h5><spring:message code="label.user.firstName"/>: {{profile.firstName}}</h5>
                    <h5><spring:message code="label.user.lastName"/>: {{profile.lastName}}</h5>
                    <h5><spring:message code="label.user.email"/>: {{profile.email}}</h5>
                    <h5><spring:message code="label.user.datebirth"/>: {{convertDate(birthday)}}</h5>
                    <h5><spring:message code="label.user.city"/>: {{profile.city}}</h5>
                    <h5><spring:message code="label.user.education"/>: {{profile.education}}</h5>
                    <h5><spring:message code="label.user.interests"/>: {{profile.interests}}</h5>
                    <h5><spring:message code="label.user.skype"/>: {{profile.skype}}</h5>

                    <div class="col-md-12 sidebar" style="margin-top: 20px;">
                        <h4 class="sidebar-block-header nav-tabs">
                            <spring:message code="label.user.achievements"/></h4>

                        <div class="col-md-6" style="float: left; padding: 0 0 0 0; margin: 5px 0 0 0;" ng-repeat="achiva in achievements">
                            <img data-toggle="tooltip" title="{{achiva.description}}" width="100%" ng-src="{{achiva.value}}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
</body>
</html>
