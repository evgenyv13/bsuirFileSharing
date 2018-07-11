<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Registration</title>
    <spring:url value="/resources/img/favicon.ico" var="favicon"/>

    <spring:url value="/resources/css/bootstrap.css" var="bootstrap"/>
    <link href="${bootstrap}" rel="stylesheet" />

    <spring:url value="/resources/css/freelancer.css" var="freelancer"/>
    <link href="${freelancer}" rel="stylesheet" />

    <spring:url value="/resources/js/jquery.min.js" var="jquarysrc"/>
    <script src="${jquarysrc}"></script>

    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJs"/>
    <script src="${bootstrapJs}"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link rel="icon" href="${favicon}" type="image/x-icon">
</head>
<body id="page-top">

<!-- Navigation -->
<nav class="navbar navbar-expand-lg bg-secondary fixed-top text-uppercase" id="mainNav">
    <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="/">BSUIR FileSharing</a>
        <button class="navbar-toggler navbar-toggler-right text-uppercase bg-primary text-white rounded" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fa fa-bars"></i>
        </button>
    </div>
</nav>

<div class="row" style="padding-top: calc(4rem);">
    <div class="col-md-4"> </div>
    <div class="col-md-4">
        <div class="list-group" >
            <!-- login form -->
            <div class="container" style="padding-top: calc(7rem);">
                <div class="list-group" >
                    <span class="list-group-item active" style="background-color: #2c3e50; text-align: center"><h4>Регистрация BSUIR FILESHARING</h4></span>
                    <form:form method="POST" modelAttribute="userForm" class="form-signin">
                        <spring:bind path="username">
                            <div class="list-group-item">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                                autofocus="true"></form:input>
                                    <form:errors path="username"></form:errors>
                                </div>
                            </div>
                        </div>
                        </spring:bind>
                            <div class="list-group-item">
                                <spring:bind path="password">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                                        <form:errors path="password"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>

                            <div class="list-group-item">
                                <spring:bind path="passwordConfirm">
                                    <div class="form-group ${status.error ? 'has-error' : ''}">
                                        <form:input type="password" path="passwordConfirm" class="form-control"
                                                    placeholder="Confirm your password"></form:input>
                                        <form:errors path="passwordConfirm"></form:errors>
                                    </div>
                                </spring:bind>
                            </div>

                        <button class="btn btn-lg btn-primary btn-block" type="Подтвердить">Submit</button>
                    </form:form>
                </div>

            </div>

        </div>
    </div>
</div>


</body>

</html>