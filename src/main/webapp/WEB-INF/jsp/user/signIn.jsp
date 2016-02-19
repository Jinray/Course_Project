<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>

    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/social-buttons-3.css"/>
</head>

<sec:authorize access="isAnonymous()">
    <div style="width: 100%; height: 100%; text-align: center;">
        <div class="panel panel-default" style="width: 40%; margin: 0 30% 0 30%">
        <div class="panel-body">
            <h2><spring:message code="label.login.form.title"/></h2>
            <c:if test="${param.error eq 'bad_credentials'}">
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <spring:message code="text.login.page.login.failed.error"/>
                </div>
            </c:if>
            <form action="${pageContext.request.contextPath}/login/authenticate" method="POST" role="form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div id="form-group-email" class="form-group col-lg-12">
                        <label class="control-label" for="user-email"><spring:message code="label.user.email"/>:</label>
                        <input id="user-email" name="username" type="text" class="form-control"/>
                    </div>
                </div>

                <div class="row">
                    <div id="form-group-password" class="form-group col-lg-12">
                        <label class="control-label" for="user-password"><spring:message code="label.user.password"/>:</label>
                        <input id="user-password" name="password" type="password" class="form-control"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-12">
                        <button type="submit" class="btn btn-primary"><spring:message code="label.user.login.submit.button"/></button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="form-group col-lg-12">
                    <a href="${pageContext.request.contextPath}/user/register"><spring:message code="label.navigation.registration.link"/></a>
                </div>
            </div>

            <div class="row">
                <div style="width: 100%; padding: 0 5px 0 5px;">
                    <div class="custom-col-md-4" style="text-align: center;">
                        <a style="display: block; width: 100%;" href="${pageContext.request.contextPath}/auth/facebook">
                            <button class="btn btn-facebook" style="width: 100%;">
                                <i class="icon-facebook"></i> | <spring:message code="label.facebook.sign.in.button"/>
                            </button>
                        </a>
                    </div>
                    <div class="custom-col-md-4" style="text-align: center;">
                        <a style="display: block; width: 100%;" href="${pageContext.request.contextPath}/auth/twitter">
                            <button class="btn btn-twitter" style="width: 100%;">
                                <i class="icon-twitter"></i> | <spring:message code="label.twitter.sign.in.button"/>
                            </button>
                        </a>
                    </div>
                    <div class="custom-col-md-4" style="text-align: center;">
                        <a style="display: block; width: 100%;" href="${pageContext.request.contextPath}/auth/linkedin">
                            <button class="btn btn-linkedin" style="width: 100%;">
                                <i class="icon-linkedin"></i> | <spring:message code="label.vk.sign.in.button"/>
                            </button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <p><spring:message code="text.login.page.authenticated.user.help"/></p>
    </sec:authorize>
</body>
</html>