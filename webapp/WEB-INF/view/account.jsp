<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Main Page</title>
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
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="/data/">Data</a>
                </li>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <form id="logoutForm" method="POST" action="${contextPath}/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <li class="nav-item mx-0 mx-lg-1">
                        <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="/account/"> ${pageContext.request.userPrincipal.name} </a>
                    </li>
                    <li class="nav-item mx-0 mx-lg-1">
                        <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" onclick="document.forms['logoutForm'].submit()" href="/logout">Выход</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="container" style="padding-top: calc(7rem);">
    <div class="row" style="padding-top: calc(4rem);">
        <div class="col-md-8">
            <div class="list-group">
                <a href="/account/" class="list-group-item active" style="background-color: #2c3e50;"> Your Files </a>
                <c:if test="${!empty listFiles}">
                        <c:forEach items="${listFiles}" var="file">
                            <a href="/data/${file.file_url}/" class="list-group-item list-group-item-action">${file.file_name}</a>
                            <a href="/remove/${file.id}/" class="btn-outline-light">Удалить</a>
                        </c:forEach>
                </c:if>
            </div>
        </div>

        <div class="col-md-4">

                <div class="list-group" >
                    <span class="list-group-item active" style="background-color: #2c3e50;">Account ID ${uId}</span>
                    <form method="POST" enctype="multipart/form-data">
                        <div class="list-group-item">
                            User name: ${username}
                        </div>
                        <div class="list-group-item">
                            Uploaded files: ${uploadedFiles}
                        </div>
                        <span class="list-group-item active" style="background-color: #2c3e50;">Change Password</span>
                        <div class="list-group-item">
                            <input size="16" type="password"  class="form-control" placeholder="Old Password" name="oldPass">
                            <c:if test="${oldPassmsg != null}">
                                ${oldPassmsg}
                            </c:if>
                        </div>
                        <div class="list-group-item">
                            <input size="16" type="password"  class="form-control" placeholder="New Password" name="newPass">
                            <c:if test="${newPassmmsg != null}">
                                ${newPassmmsg}
                            </c:if>
                        </div>
                        <div class="list-group-item">
                            <input size="16" type="password"  class="form-control" placeholder="New Password Confirm" name="newPassConfirm">
                            <c:if test="${newPassConfirmmsg != null}">
                                ${newPassConfirmmsg}
                            </c:if>
                        </div>
                        <div class="list-group-item">
                            <input type="submit" class="btn btn-primary btn-lg btn-block" value="Сменить пароль" data-toggle="tooltip" data-placement="top" title="Смена пароля">
                        </div>
                    </form>
                </div>

        </div>
    </div>
</div>

</body>