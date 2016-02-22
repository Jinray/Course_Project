<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title><spring:message code="spring.social.mvc.normal.title"/></title>
    <link rel="stylesheet" type="text/css" href="/<spring:theme code='styleSheet'/>"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/css/ng-tags-input.bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/ng-tags-input.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vendor/jquery-2.0.3.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/angular-route.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/angular-resource.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/angular-sanitize.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/vendor/bootstrap.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/ckeditor/ckeditor.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/js/app/ng-infinite-scroll_min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/ng-tags-input.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/color-themes.js"></script>
    <script type="text/javascript"
            src="https://www.google.com/jsapi?autoload={'modules':[{'name':'visualization','version':'1','packages':['corechart']}]}"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/cookie.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/app/controller.js"></script>

    <sitemesh:write property="head"/>

</head>
<body>

<div class="page">
    <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only"><spring:message code="label.navigation.toggle.navigation.button"/></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span ><a class="navbar-brand"  href="${pageContext.request.contextPath}/login">iLearning</a></span>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <spring:message code="label.navigation.common.theme"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="?theme=dark"><spring:message code="label.navigation.common.dark"/></a></li>
                        <li><a href="?theme=default"><spring:message code="label.navigation.common.light"/></a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        <spring:message code="label.navigation.common.language"/>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="?lang=en"><img width="30px"
                                                    src="${pageContext.request.contextPath}/static/images/US.png">English</a>
                        </li>
                        <li><a href="?lang=ru"><img width="30px"
                                                    src="${pageContext.request.contextPath}/static/images/ru.png">Русский</a>
                        </li>
                    </ul>
                </li>
            </ul>


            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="isAnonymous()">
                    <li><a href="${pageContext.request.contextPath}/user/signIn"><spring:message
                            code="label.navigation.signIn.link"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/user/register"><spring:message
                            code="label.navigation.registration.link"/></a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/user/admin"><spring:message
                            code="label.navigation.admin"/></a></li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a href="${pageContext.request.contextPath}/">
                            <sec:authentication property="principal.socialSignInProvider" var="signInProvider"/>
                            <c:if test="${signInProvider == 'FACEBOOK'}">
                                <i class="icon-facebook-sign"></i>
                            </c:if>
                            <c:if test="${signInProvider == 'TWITTER'}">
                                <i class="icon-twitter-sign"></i>
                            </c:if>

                            <c:if test="${signInProvider == 'LINKEDIN'}">
                                <i class="icon-linkedin-sign"></i>
                            </c:if>
                            <c:if test="${empty signInProvider}">
                                <spring:message code="label.navigation.signed.in.as.text"/>
                            </c:if>
                            <sec:authentication property="principal.username"/></a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li>
                        <form action="${pageContext.request.contextPath}/logout" method="POST">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary navbar-btn">
                                <spring:message code="label.navigation.logout.link"/>
                            </button>
                        </form>
                    </li>
                </sec:authorize>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
    <div class="content">
        <div id="view-holder">
            <sitemesh:write property="body"/>
        </div>
    </div>

</div>
</body>
</html>
