<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/postController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/tagCloudController.js"></script>

<div ng-app="myApp" ng-controller="postController" class="col-md-10" style="margin: 0 8% 0 8%">

    <div class=" col-md-7 ">
        <div class="panel panel-primary" style="border: 1px solid #bbb">
            <div class="panel-heading">
                <h4 class="panel-title"><spring:message code="label.post.edit.title"/></h4>
            </div>
            <div style="padding: 0 2% 0 2%">
                <h4><spring:message code="label.post.upload"/></h4>
                <div class="dropzone" file-dropzone="[image/png, image/jpeg, image/gif]"
                     file="postImage" file-name="imageFileName" data-max-file-size="3">
                    <h4>Drop Image Here</h4>
                </div>
                <div ng-show="isPictureExist" class="postImageContainer">
                    <img ng-src="{{postImage}}"/>
                </div>


                <%--<input v-model="input" type="file" id="uploader">--%>
                <hr>
                <h4><spring:message code="label.post.title"/></h4>

                <input maxlength="150" class="form-control" ng-model="title">
                <div>
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
                    <h4><spring:message code="label.post.tags"/></h4>

                    <div ng-controller="tagsController">
                        <tags-input ng-model="tags"
                                    display-property="text"
                                    placeholder="Add a tag"
                                    replace-spaces-with-dashes="false"
                                    min-length="0"
                                    max-length="10"
                                    >
                            <auto-complete source="loadTags($query)"
                                           load-on-focus="true"
                                           load-on-empty="true"
                                           max-results-to-show="32"
                                           template="my-tags-template"

                                           ></auto-complete>
                        </tags-input>
                        <script type="text/ng-template" id="my-tags-template">
                            <%--<div class="left-panel">--%>
                            <%--<img ng-src="http://mbenford.github.io/ngTagsInput/images/flags/{{data.flag}}" />--%>
                            <%--</div>--%>
                            <div class="right-panel">
                                <span ng-bind-html="$highlight($getDisplayText())"></span>
                                <span>({{data.weight}})</span>
                            </div>
                        </script>
                    </div>

                    <hr>
                    <h4><spring:message code="label.post.article"/></h4>
                    <div id="editor">
                        <textarea ng-model="text" id="editor1" rows="20"
                          style="width: 100%;"></textarea>
                        <script type="text/javascript">
                            CKEDITOR.replace('editor1');
                        </script>


                    </div>

                </div>
            </div>
        </div>    </div>
    <%--NAVIGATION--%>
    <div class="col-md-5">
            <div class="panel panel-primary" style="width: 100%;  border: 1px solid #bbb;  height: 100%;">
                <div class="panel-heading">
                    <h4 class="panel-title"><i class="icon-file"></i>
                        <spring:message code="label.post.list"/></h4>
                </div>
                <div style="height: 300px;  overflow: auto; border-bottom: 1px solid #bbb; margin-bottom: 10px; padding: 0 1% 0 1%">
                    <div ng-click="setPost($index); title = post.title" class="unSelected"
                         ng-repeat="post in posts track by $index" ng-class="{selected: currentIndex == $index}">
                        <i class="icon-pencil" style="font-size: 1.5em;"> </i><span style="font-size: 1.5em;">{{post.title}}</span>
                    </div>
                </div>
                <div style="margin:10px 1% 0 1%;">
                    <div style="margin: 10px 0 10px 0">
                        <div ng-show="isUploading" class="progress progress-striped active">
                            <div class="progress-bar" style="width: 100%"></div>
                        </div>
                        <a href="javascript:void(0);" style="width: 49.5%;" class="btn btn-primary"
                           ng-click="savePost();"><spring:message code="label.user.saveChanges"/></a>
                        <a href="javascript:void(0);" style="width: 49.5%;" class="btn btn-default"
                           ng-click="deletePost();"><spring:message code="label.user.deleteChanges"/></a>
                    </div>
                </div>
            </div>

    </div>
</div>


</body>
</html>
