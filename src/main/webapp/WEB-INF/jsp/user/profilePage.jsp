<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/profileController.js"></script>

<div ng-controller="TestCtrl">
    <div ng-app="myApp" ng-controller="profileController" class="col-md-8" style="margin:0 16.5% 0 16.5%">
        <div style="float: left;" class="col-md-6">
            <div style=" text-align: center;">
                <div>
                    <div file-dropzone="[image/png, image/jpeg, image/gif]" file="image"
                         file-name="ctrlBoundFileName"
                         data-max-file-size="3">
                        <img class="image" ng-src="{{image}}"/>
                    </div>
                </div>
            </div>

            <div ng-show="isUploading" class="progress progress-striped active">
                <div class="progress-bar" style="width: 100%"></div>
            </div>

            <hr>
            <button style="width: 49.5%" class="btn btn-primary btn-primary:hover" ng-click="saveProfile()">
                <spring:message code="label.user.saveChanges"/>
            </button>
            <button style="width: 49.5%" class="btn btn-default btn-default:hover"
                    ng-click="getProfile()">
                <spring:message code="label.user.Cancel"/>
            </button>
        </div>
        <div class="col-md-6" style="float: left;">
            <label for="f" class="labelStyle"><spring:message code="label.user.firstName"/>:</label>
            <input id="f" class="inputStyle" ng-model="profile.firstName"/>

            <label for="l" class="labelStyle"><spring:message code="label.user.lastName"/>:</label>
            <input id="l" class="inputStyle" ng-model="profile.lastName"/>

            <label for="d" class="labelStyle"><spring:message code="label.user.datebirth"/>:</label>
            <input id="d" type="date" class="inputStyle" ng-model="birthday"/>

            <label for="c" class="labelStyle"><spring:message code="label.user.city"/>:</label>
            <input id="c" class="inputStyle" ng-model="profile.city"/>

            <label for="ed" class="labelStyle"><spring:message code="label.user.education"/>:</label>
            <input id="ed" class="inputStyle" ng-model="profile.education"/>

            <label for="i" class="labelStyle"><spring:message code="label.user.interests"/>:</label>
            <input id="i" class="inputStyle" ng-model="profile.interests"/>

            <label for="s" class="labelStyle"><spring:message code="label.user.skype"/>:</label>
            <input id="s" class="inputStyle" ng-model="profile.skype"/>
        </div>


    </div>
</div>
</body>
</html>
