<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/adminController.js"></script>

<div ng-app="myApp" ng-controller="adminController" style="height: 100%">

    <div class="col-md-12" style="height: 100%">
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title"><spring:message code="label.user.info"/></h4>
                </div>
                <div class="panel-body" style="margin-top: -15px">
                    <form class="form-horizontal" name="usersProfile">
                        <fieldset>
                            <div class="form-group" style="padding: 0 5px 0 5px;">
                                <label for="f" class="labelStyle" style="font-size: medium;"><spring:message
                                        code="label.user.firstName"/>:</label>
                                <input id="f" class="form-control" maxlength="40" required
                                       ng-model="users[selectedUser].firstName"/>

                                <label for="l" class="labelStyle" style="font-size: medium;"><spring:message
                                        code="label.user.lastName"/>:</label>
                                <input id="l" class="form-control" maxlength="40" required
                                       ng-model="users[selectedUser].lastName"/>

                                <label for="d" class="labelStyle" style="font-size: medium;"><spring:message
                                        code="label.user.datebirth"/>:</label>
                                <input id="d" type="date" max="2015-12-31" min="1950-12-31" class="inputStyle"
                                       ng-model="birthday"/>

                                <label for="c" class="labelStyle" style="font-size: medium;"><spring:message
                                        code="label.user.city"/>:</label>
                                <input id="c" class="form-control" maxlength="40" ng-model="users[selectedUser].city"/>

                                <label for="ed" class="labelStyle" style="font-size: medium;"><spring:message
                                        code="label.user.education"/>:</label>
                                <input id="ed" class="form-control" maxlength="40"
                                       ng-model="users[selectedUser].education"/>

                                <label for="i" class="labelStyle" style="font-size: medium;"><spring:message
                                        code="label.user.interests"/>:</label>
                                <input id="i" class="form-control" maxlength="40"
                                       ng-model="users[selectedUser].interests"/>

                                <label for="s" class="labelStyle" style="font-size: medium;"><spring:message
                                        code="label.user.skype"/>:</label>
                                <input id="s" class="form-control" maxlength="40" ng-model="users[selectedUser].skype"/>

                                <div style="margin-top: 10px">
                                    <button style="width: 49.5%" type="submit"
                                            class="btn btn-primary btn-primary:hover"
                                            ng-click="saveUserInfo()"
                                            ng-disabled="usersProfile.$invalid">
                                        <spring:message code="label.user.saveChanges"/>
                                    </button>
                                    <button style="width: 49.5%"
                                            class="btn btn-default btn-default:hover"
                                            ng-click="getAllUsers()">
                                        <spring:message code="label.user.Cancel"/>
                                    </button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-4" style="height: 100%;">
            <div class="panel panel-primary" style="height: 100%">
                <div class="panel-heading">
                    <h4 class="panel-title"><spring:message code="label.user.users"/></h4>
                </div>
                <div style="height: 820px;  overflow: auto; margin-bottom: 10px; padding: 0 2% 0 2%">
                    <div ng-click="setUserSelection($index)" ng-repeat="user in users" class="col-mg-12 nav nav-tabs"
                         style="padding: 10px 0 10px 0" ng-class="{selected: selectedUser == $index}">
                        <div class="col-md-2">
                            <img class="img-thumbnail" style="width: 100%;" ng-src="{{user.photo}}"/>
                        </div>
                        <div class="col-md-8">
                            <h4 class="text-primary"><a
                                    href="${pageContext.request.contextPath}/user/userHomePage/{{user.id}}">{{user.firstName}}
                                {{user.lastName}}</a></h4>
                            <h4>{{user.email}}</h4>
                            <h4 h4-show="user.role == 'ROLE_ADMIN'"><spring:message code="label.navigation.admin"/></h4>
                        </div>
                        <div ng-hide="user.role == 'ROLE_ADMIN'" class="col-md-2 pull-right"
                             style="text-align: center;">
                            <button ng-click="banOrUnBanUser()" class="btn btn-danger"><spring:message
                                    code="label.user.ban"/></button>

                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-5" style="height: 100%;">
            <div class="panel panel-primary" style="height: 100%">
                <div class="panel-heading">
                    <h4 class="panel-title"><spring:message code="label.user.articles"/></h4>
                </div>
                <div style="height: 820px;  overflow: auto; margin-bottom: 10px; padding: 0 1% 0 1%">
                    <div ng-repeat="post in users[selectedUser].posts" class="col-mg-12 nav nav-tabs"
                         style="padding: 10px 0 10px 0">
                        <div class="col-md-4">
                            <div ng-show="post.template == 0">
                                <img class="img-thumbnail" style="width: 100%" ng-src="{{post.image}}"/>
                            </div>

                            <div ng-show="post.template == 1">
                                <img class="img-thumbnail" style="width: 100%"
                                     src="${pageContext.request.contextPath}/static/images/YouTubelogo.jpg"/>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <h4><a style="display: inline-block;"
                                   href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}">
                                {{post.title}}
                            </a></h4>
                            <div class="article-info">
                                <spring:message code="label.post.postCategory"/>:
                                <a href="${pageContext.request.contextPath}/user/login/category={{post.category}}"
                                   class="text-primary">{{post.category}}</a>,
                                <spring:message code="label.post.PostDate"/>: {{convertDate(post.date)}}

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
