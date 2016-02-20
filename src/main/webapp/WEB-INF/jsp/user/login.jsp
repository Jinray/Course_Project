<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>

    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/social-buttons-3.css"/>
    <%--<link type="text/css" data-themecolor="default" rel='stylesheet'--%>
          <%--href="${pageContext.request.contextPath}/static/css/main-default.css">--%>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/color-themes.js"></script>
    <script type="text/javascript"
            src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/cookie.js"></script>

</head>
<body>
<div class="mask-l"
     style="background-color: #fff; width: 100%; height: 100%; position: fixed; top: 0; left:0; z-index: 9999999;"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/postDirective.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/startPageController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/tagCloudController.js"></script>

<!--removed by integration-->
<div ng-app="myApp">
    <div ng-controller="startPageController" class="page-container">
        <div class="container">
            <div class="row">
                <div class="col-md-9 col-md-push-3">
                    <div infinite-scroll='extendList()' infinite-scroll-disabled='busy'>
                        <div ng-repeat="post in articles" ng-hide="$index > postListLen+3">
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
                                            <spring:message code="label.post.author"/>:
                                            <a href="${pageContext.request.contextPath}/user/userHomePage/{{post.userId}}" class="text-primary">{{post.lastName}}
                                                {{post.firstName}}</a>,
                                            <spring:message code="label.post.postCategory"/>:
                                            <a href="#" ng-click="getCategoryPosts(post.category)" class="text-primary">{{post.category}}</a>,
                                            <spring:message code="label.post.PostDate"/>: {{convertDate(post.date)}}
                                            <div style="float: right;">
                                                <a href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                                    class="text-primary">
                                                <i class="icon-comment"></i> {{post.comments.length}} <spring:message
                                                    code="label.post.comments"/></a></div>
                                        </div>

                                        <div>
                                            <a ng-repeat="tag in post.tags" href="#" class="tag label label-primary" style="margin-right: 4px;">
                                                {{post.tags[$index].text}}
                                            </a>
                                        </div>
                                        <hr>

                                        <div style="max-height: 400px; text-overflow:ellipsis; overflow:hidden;">
                                            <posttext-directive></posttext-directive>
                                        </div>
                                        <div class="article-info">
                                            <a href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                               class="text-primary"><spring:message
                                                    code="label.post.readMore"/>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-md-pull-9">
                    <div class="row sidebar">
                        <form ng-submit="getSearchResults()" class="navbar-form navbar-left" role="search">
                            <div class="form-group">
                                <input ng-model="search" type="text" class="form-control" placeholder="Search">
                            </div>
                            <button type="submit" class="btn btn-primary">
                                <i class="icon-search" style="font-size: 1.5em;"> </i>
                            </button>
                        </form>
                        <div class="col-md-12">
                            <div>
                                <h4 class="sidebar-block-header nav-tabs">
                                    <spring:message code="label.blog"/></h4>

                                <ul class="nav" style="margin-top: -10px">
                                    <li>
                                        <a ng-click="getAllPosts()" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.all.categories"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Art')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.art"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Medicine')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.medicine"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Transport')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.transport"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Finance')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.finance"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Sport')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.sport"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Music')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.music"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Business')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.business"/></a>
                                    </li>
                                    <li>
                                        <a ng-click="getCategoryPosts('Building')" href="#"
                                           style="padding: 10px 15px;" class="nav-tabs">
                                            <spring:message code="label.building"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <h4 class="sidebar-block-header nav-tabs">
                                <spring:message code="label.popular.postes"/>
                            </h4>
                            <div class="popular-container row">
                                <div ng-repeat="pop in popArticles" class="populars nav-tabs col-md-12  col-xs-12">
                                    <div class="col-md-3" style="padding: 0 0 0 0;">
                                        <a href="${pageContext.request.contextPath}/user/singlePost/{{pop.id}}"><img width="100%" ng-src="{{pop.image}}" alt=""/></a>
                                    </div>
                                    <div class="col-md-9">
                                        <div style="font-size: 0.92308em; line-height: 2;">
                                            <a href="${pageContext.request.contextPath}/user/singlePost/{{pop.id}}">{{pop.title}}</a>
                                        </div>
                                        <div style="font-size: 0.84615em; line-height: 1.63636; font-style: italic; font-weight: lighter;">
                                            {{convertDate(pop.date)}}
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <h4 class="sidebar-block-header nav-tabs">
                                <spring:message code="label.tags.cloud"/>
                            </h4>
                            <div ng-controller="tagsController">
                                <div ng-repeat="tag in tagsCloud">
                                    <a class="tag label label-primary" style="float: left;"
                                        ng-click="getTagsPosts(tag.text)" href=""
                                        ng-style="tagSize={'font-size': '{{tag.weight}}em'}">{{tag.text}}</a>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>