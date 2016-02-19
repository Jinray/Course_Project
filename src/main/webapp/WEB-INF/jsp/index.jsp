<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/social-buttons-3.css"/>
    <%--<link type="text/css" data-themecolor="default" rel='stylesheet'--%>
          <%--href="${pageContext.request.contextPath}/static/css/main-default.css">--%>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">


</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/postDirective.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/profileController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/postController.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/tagCloudController.js"></script>

<div class="mask-l"
     style="background-color: #fff; width: 100%; height: 100%; position: fixed; top: 0; left:0; z-index: 100;"></div>
<!--removed by integration-->
<div ng-controller="TestCtrl">
    <div ng-app="myApp" ng-controller="profileController">
        <div ng-controller="postController" class="l-inner-page-container">
            <div class="col-md-9" style="margin: 0 12.5% 0 12.5%">
                <div class="row">
                    <div class="col-md-9 col-md-push-3" >
                        <div class="navbar navbar-default" style="margin-bottom: 15px;;">
                            <div class="collapse navbar-collapse navbar-ex1-collapse">
                                <ul class="nav navbar-nav">
                                    <li>
                                        <a href="#" ng-click="showTemplate()" aria-expanded="false">
                                            <spring:message code="label.user.templates"/></a>
                                    </li>
                                    <li>
                                        <a href="${pageContext.request.contextPath}/user/templates">
                                            <spring:message code="label.user.edit.posts"/></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div ng-show="showField">
                            <div class="panel panel-primary" style="border: 1px solid #bbb">
                                <div style="padding: 0 2% 0 2%">
                                    <h4><spring:message code="label.post.upload"/></h4>
                                    <div class="dropzone" file-dropzone="[image/png, image/jpeg, image/gif]"
                                         file="postImage" file-name="imageFileName" data-max-file-size="3">
                                        <h4>Drop Image Here</h4>
                                    </div>
                                    <div class="postImageContainer">
                                        <img ng-src="{{postImage}}"/>
                                    </div>


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

                                        <hr>
                                        <h4><spring:message code="label.post.article"/></h4>
                                        <div id="editor">
                                            <textarea ng-model="text" id="editor1" rows="20"
                                                      style="width: 100%;"></textarea>
                                            <script type="text/javascript">
                                                CKEDITOR.replace('editor1');
                                            </script>
                                        </div>
                                        <a href="javascript:void(0);" style="margin:20px 0 20px 0"
                                           class="btn btn-primary btn-block"
                                           ng-click="newPost();"><spring:message code="label.post.publish"/></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div infinite-scroll='extendList()' infinite-scroll-disabled='busy'>
                            <div ng-repeat="post in posts" ng-hide="$index > postListLen+1">
                                <div class="b-blog-listing__block">
                                    <div class="b-blog-listing__block-top">
                                        <div class=" view view-sixth">
                                            <img data-retina="" ng-src="{{post.image}}" alt="">
                                        </div>
                                    </div>
                                    <div class="b-infoblock-with-icon b-blog-listing__infoblock">
                                        <a href="#"
                                           class="b-infoblock-with-icon__icon f-infoblock-with-icon__icon fade-in-animate hidden-xs">
                                            <i class="fa fa-pencil"></i>
                                        </a>
                                        <div class="b-infoblock-with-icon__info f-infoblock-with-icon__info">
                                            <a href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                               class="f-infoblock-with-icon__info_title b-infoblock-with-icon__info_title f-primary-l b-title-b-hr f-title-b-hr">
                                                {{post.title}}
                                            </a>
                                            <div class="f-infoblock-with-icon__info_text b-infoblock-with-icon__info_text f-primary-b b-blog-listing__pretitle">
                                                <spring:message code="label.post.author"/>:
                                                <a href="#" class="f-more">{{profile.lastName}}
                                                    {{profile.firstName}}</a>,
                                                <spring:message code="label.post.postCategory"/>:
                                                <a href="#" class="f-more">{{post.category}}</a>,
                                                <spring:message code="label.post.PostDate"/>: {{convertDate(post.date)}}
                                                <div style="float: right;"><a
                                                        href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                                        class="f-more f-primary-b">
                                                    <i class="fa fa-comment"></i> {{post.comments.length}} <spring:message
                                                        code="label.post.comments"/></a></div>
                                            </div>

                                            <div>
                                                <a ng-repeat="tag in post.tags" href="#" class="label label-primary" style="margin-right: 4px;">
                                                    {{post.tags[$index].text}}
                                                </a>
                                            </div>
                                            <hr>

                                            <div style="max-height: 400px; text-overflow:ellipsis; overflow:hidden;">
                                                <posttext-directive></posttext-directive>
                                            </div>
                                            <div class="f-infoblock-with-icon__info_text b-infoblock-with-icon__info_text">
                                                <a href="${pageContext.request.contextPath}/user/singlePost/{{post.id}}"
                                                   hides" class="f-more f-primary-b"><spring:message
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
                        <div style="width: 100%; text-align: center">
                            <img alt="Bootstrap Image Preview" ng-src="{{profile.photo}}"
                                 class="img-thumbnail">
                        </div>
                        <h5><spring:message code="label.user.firstName"/>: {{profile.firstName}}</h5>
                        <h5><spring:message code="label.user.lastName"/>: {{profile.lastName}}</h5>
                        <h5><spring:message code="label.user.email"/>: {{profile.email}}</h5>
                        <h5><spring:message code="label.user.datebirth"/>: {{convertDate(birthday)}} </h5>
                        <h5><spring:message code="label.user.city"/>: {{profile.city}}</h5>
                        <h5><spring:message code="label.user.education"/>: {{profile.education}}</h5>
                        <h5><spring:message code="label.user.interests"/>: {{profile.interests}}</h5>
                        <h5><spring:message code="label.user.skype"/>: {{profile.skype}}</h5>

                        <a href="${pageContext.request.contextPath}/user/profilePage" class="btn btn-primary btn-block">
                            <spring:message code="label.user.edit"/>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>