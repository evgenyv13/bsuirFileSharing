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


    <spring:url value="/resources/css/errpage.css" var="errpage"/>
    <link href="${errpage}" rel="stylesheet" />

    <spring:url value="/resources/js/jquery.min.js" var="jquarysrc"/>
    <script src="${jquarysrc}"></script>

    <spring:url value="/resources/js/bootstrap.js" var="bootstrapJs"/>
    <script src="${bootstrapJs}"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">
    <link rel="icon" href="${favicon}" type="image/x-icon">
</head>

<body>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2>
                    404 Not Found</h2>
                <div class="error-details">
                    Sorry, an error has occured, Requested page not found!
                </div>
                <div class="error-actions">
                    <a href="/data/" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                        Go to Files </a><a href="/account/" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Go to account </a>
                </div>
            </div>
        </div>
    </div>
</div>




</body>

</html>