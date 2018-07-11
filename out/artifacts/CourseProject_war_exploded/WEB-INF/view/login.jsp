<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Вход BSUIR FILESHARING</title>


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
                    <span class="list-group-item active" style="background-color: #2c3e50; text-align: center"><h4>Вход</h4></span>

                    <form method="POST" action="${contextPath}/login" class="form-signin">
                            <div class="form-group ${error != null ? 'has-error' : ''}">
                                <div class="list-group-item">
                                   ${message}
                                    <input name="username" type="text" class="form-control" placeholder="Username"
                                           autofocus="true"/>
                                </div>
                                <div class="list-group-item">
                                    <input name="password" type="password" class="form-control" placeholder="Password"/>
                                   ${error}
                                </div>

                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="list-group-item">
                                    <button class="btn btn-lg btn-success btn-block" type="submit">Войти</button>
                                    <p class="text-center"><a href="${contextPath}/registration">Создать аккаунт</a></p>
                                </div>
                            </div>

                    </form>
                </div>

            </div>

        </div>
    </div>
</div>


</body>

</html>