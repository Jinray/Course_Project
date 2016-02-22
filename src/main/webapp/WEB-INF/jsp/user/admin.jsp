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
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/tagCloudController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/TrustController.js"></script>

<div ng-app="myApp" ng-controller="adminController" style="height: 100%">

    <div class="col-md-12" style="height: 100%">
        <div class="col-md-3" ng-show="!editMode">
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
        <div class="col-md-4" ng-show="!editMode" style="height: 100%;">
            <div class="panel panel-primary" style="height: 100%">
                <div class="panel-heading">
                    <h4 class="panel-title"><spring:message code="label.user.users"/></h4>
                </div>
                <div style="height: 650px;  overflow: auto; margin-bottom: 10px; padding: 0 2% 0 2%">
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
                            <h4 ng-show="user.role == 'ROLE_ADMIN'"><spring:message code="label.navigation.admin"/></h4>
                        </div>
                        <div ng-hide="user.role == 'ROLE_ADMIN'" class="col-md-2"
                             style="text-align: center;">
                            <button ng-show="!user.baned"
                                    ng-click="setUserSelection($index); banOrUnBanUser()"
                                    class="btn btn-danger pull-right"><span class="icon-eye-close">
                                <spring:message code="label.user.ban"/></button>
                            <button ng-show="user.baned"
                                    ng-click="setUserSelection($index); banOrUnBanUser()"
                                    class="btn btn-success  pull-right"><span class="icon-eye-open">
                                <spring:message code="label.user.unban"/></button>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-7" ng-show="editMode">
            <div  class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title" style="display: inline-block"><spring:message code="label.post.edit.title"/></h4>
                    <span class="icon-remove pull-right"
                          style="font-size: 1.5em"
                        ng-click="toggleEditModeOff();"></span>
                </div>
                <form class="form-horizontal" name="editingArticle">
                    <fieldset style="padding: 0 3% 0 3%">
                        <div class="form-group" ng-show="currentTemplate == 0">
                            <h4><spring:message code="label.post.upload"/></h4>
                            <div class="dropzone" file-dropzone="[image/png, image/jpeg, image/gif]"
                                 file="postImage" file-name="imageFileName" data-max-file-size="3">
                                <h4><spring:message code="label.post.dropzone"/></h4>
                            </div>
                            <div  class="postImageContainer">
                                <img ng-src="{{postImage}}"/>
                            </div>
                        </div>

                        <div class="form-group" ng-show="currentTemplate == 1">
                            <h4><spring:message code="label.post.youtube"/></h4>
                            <input maxlength="150" class="form-control" ng-model="video">
                            <div ng-show="isPictureExist" ng-controller="TrustController">
                                <div class="embed-responsive embed-responsive-16by9"
                                     style="margin: 15px 0 0 0 ">
                                    <iframe class="embed-responsive-item"
                                            ng-src="{{trustSrc(handleYouTube(video))}}"
                                            frameborder="0" allowfullscreen></iframe>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <h4><spring:message code="label.post.title"/></h4>
                            <input maxlength="150" class="form-control" id="inputTitle" ng-model="title"
                                   required>
                        </div>

                        <div class="form-group">
                            <h4><spring:message code="label.post.category"/></h4>
                            <select class="form-control" id="category" ng-model="category">
                                <option selected><spring:message code="label.art"/></option>
                                <option><spring:message code="label.medicine"/></option>
                                <option><spring:message code="label.transport"/></option>
                                <option><spring:message code="label.finance"/></option>
                                <option><spring:message code="label.sport"/></option>
                                <option><spring:message code="label.music"/></option>
                                <option><spring:message code="label.business"/></option>
                                <option><spring:message code="label.building"/></option>
                            </select>
                        </div>
                        <div class="form-group">
                            <h4><spring:message code="label.post.tags"/></h4>
                            <div ng-controller="tagsController">
                                <tags-input ng-model="tags"
                                            display-property="text"
                                            placeholder="Add a tag"
                                            replace-spaces-with-dashes="false"
                                            min-length="0"
                                            max-length="10"
                                            max-tags="10">
                                    <auto-complete source="loadTags($query)"
                                                   load-on-focus="true"
                                                   max-tags="10"
                                                   load-on-empty="true"
                                                   max-results-to-show="32"
                                                   template="my-tags-template">

                                    </auto-complete>
                                </tags-input>
                                <script type="text/ng-template" id="my-tags-template">
                                    <div class="right-panel">
                                        <span ng-bind-html="$highlight($getDisplayText())"></span>
                                        <span>({{data.weight}})</span>
                                    </div>
                                </script>
                            </div>
                        </div>
                        <hr>
                        <div class="form-group">
                            <h4><spring:message code="label.post.article"/></h4>
                            <div id="editor">
                            <textarea ng-model="text" id="editor1" rows="20" class="form-control"
                                      style="width: 100%;"></textarea>
                                <script type="text/javascript">
                                    CKEDITOR.replace('editor1');
                                </script>
                            </div>
                        </div>
                        <div class="form-group">
                            <div ng-if="isUpdating" class="progress progress-striped active">
                                <div class="progress-bar" style="width: 100%"></div>
                            </div>
                            <button style="margin:5px 0 5px 0" ng-click="saveChanges();"
                                    class="btn btn-primary btn-block" type="submit" ng-disabled="editingArticle.$invalid"
                            ><spring:message code="label.user.saveChanges"/></button>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>



        <div class="col-md-5" style="height: 100%;">
            <div class="panel panel-primary" style="height: 100%">
                <div class="panel-heading">
                    <h4 class="panel-title"><spring:message code="label.user.articles"/></h4>
                </div>
                <div style="height: 650px;  overflow: auto; margin-bottom: 10px; padding: 0 1% 0 1%">
                    <div ng-click="setUserArticle($index)" ng-repeat="post in users[selectedUser].posts" class="col-mg-12 nav nav-tabs"
                         style="padding: 10px 0 10px 0" ng-class="{selected: selectedArticle == $index}">
                        <div class="col-md-3">
                            <div ng-show="post.template == 0">
                                <img class="img-thumbnail" style="width: 100%" ng-src="{{post.image}}"/>
                            </div>

                            <div ng-show="post.template == 1">
                                <img class="img-thumbnail" style="width: 100%"
                                     src="${pageContext.request.contextPath}/static/images/YouTubelogo.jpg"/>
                            </div>
                        </div>
                        <div class="col-md-6" style="padding: 0 0 0 0;">
                            <h4><a style="display: inline-block;"
                                   href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}">
                                {{post.title}}
                            </a></h4>
                            <div class="article-info">
                                <spring:message code="label.post.postCategory"/>:
                                <a href="${pageContext.request.contextPath}/user/login/category={{post.category}}">
                                    {{post.category}}
                                </a>,
                                <spring:message code="label.post.PostDate"/>: {{convertDate(post.date)}}

                            </div>
                        </div>
                        <div class="col-md-3">
                            <button ng-click="toggleEditModeOn($index)" class="btn btn-success btn-block"><span class="icon-edit"></span>Edit</button>
                            <button ng-click="deletePostByAdmin(post.id)" class="btn btn-danger btn-block"><span class="icon-remove"></span>Delete</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
