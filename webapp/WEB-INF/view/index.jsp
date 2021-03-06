<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <title>Main Page</title>
    <spring:url value="/resources/img/student.png" var="studentimg"/>
    <spring:url value="/resources/img/star.png" var="starimg"/>
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
                <li class="nav-item mx-0 mx-lg-1">
                    <a class="nav-link py-3 px-0 px-lg-3 rounded js-scroll-trigger" href="/account/">Account</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- Header -->
<header class="masthead text-secondary text-center">
    <div class="container">
        <img class="img-fluid mb-5 d-block mx-auto" src="${studentimg}" alt="">
        <h1 class="text-uppercase mb-0">BSUIR FILE SHARING</h1>
        <hr class="star-light">
        <h2 class="font-weight-light mb-0">Dowload - Upload - Learn</h2>
    </div>
</header>



</body>

</html>