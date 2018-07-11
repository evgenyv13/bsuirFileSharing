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

    <spring:url value="/resources/css/errpage.css" var="errpage"/>
    <link href="${errpage}" rel="stylesheet" />

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



<!-- Header -->
<div class="container" style="padding-top: calc(7rem);">
    <div class="container">
        <%--files--%>
        <div class="container">
            <div class="row">
                <ul class="nav nav-tabs">
                    <c:choose>
                        <c:when test="${(not empty speciality) &&(not empty semester) &&(not empty  subjectId) &&(not empty  professor) &&(not empty   lrNum) &&(not empty variant)}">
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/"> Специальность: ${speciality} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/"> Семестр: ${semester} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/"> Предмет: ${subjectId} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/${professor}/"> Преподаватель: ${professor} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/${professor}/${lrNum}/"> Номер работы: ${lrNum} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/${professor}/${lrNum}/${variant}"> Вариант: ${variant} </a>
                            </li>
                        </c:when>
                        <c:when test="${(not empty speciality) &&(not empty semester) &&(not empty  subjectId) &&(not empty  professor) &&(not empty   lrNum)}">

                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/"> Специальность: ${speciality} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/"> Семестр: ${semester} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/"> Предмет: ${subjectId} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/${professor}/"> Преподаватель: ${professor} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/${professor}/${lrNum}/"> Номер работы: ${lrNum} </a>
                            </li>

                        </c:when>
                        <c:when test="${(not empty speciality) &&(not empty semester) &&(not empty  subjectId) &&(not empty  professor)}">

                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/"> Специальность: ${speciality} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/"> Семестр: ${semester} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/"> Предмет: ${subjectId} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/${professor}/"> Преподаватель: ${professor} </a>
                            </li>

                        </c:when>
                        <c:when test="${(not empty speciality) &&(not empty semester) && (not empty  subjectId) }">

                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/"> Специальность: ${speciality} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/"> Семестр: ${semester} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/${subjectId}/"> Предмет: ${subjectId} </a>
                            </li>

                        </c:when>
                        <c:when test="${(not empty speciality) &&(not empty semester)}">
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/"> Специальность: ${speciality} </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/${semester}/"> Семестр: ${semester} </a>
                            </li>
                        </c:when>
                        <c:when test="${(not empty speciality)}">
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/${speciality}/"> Специальность: ${speciality}</a>
                            </li>
                        </c:when>
                        <c:when test="${(empty speciality)}">
                            <li class="nav-item">
                                <a class="nav-link active" href="/data/">Выбирите специальность</a>
                            </li>
                        </c:when>
                    </c:choose>
                </ul>
            </div>

            <div class="row" style="padding-top: calc(4rem);">
                <div class="col-md-8">
                    <div class="list-group" >
                        <a href="../" class="list-group-item active" style="background-color: #2c3e50;">
                            ↩ ${nowTimeFolder}
                        </a>
                        <c:forEach items="${fileList}" var="file">
                            <a href="${file}/" class="list-group-item list-group-item-action">${file}</a>

                            <c:choose>
                                <c:when test="${file.getBtnToDelate() eq true}">
                                   <%-- <a href="/remove/${file.getId() }" >Delete</a>--%>
                                    <a href="/remove/${file.id}/" class="btn-outline-light">Удалить</a>
                                </c:when>
                            </c:choose>

                        </c:forEach>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="list-group" >
                        <span class="list-group-item active" style="background-color: #2c3e50;">Папка</span>
                        <form method="POST" enctype="multipart/form-data">
                            <div class="list-group-item">
                                <input class="span2" id="appendedInputButton" size="16" type="text" name="dirName"> Создать папку
                            </div>
                            <div class="list-group-item">
                                <div class="input-append">
                                    <input type="file" value="Выберите файл" name="file"><br />
                                </div>
                            </div>
                            <div class="list-group-item">
                                <div class="input-append">
                                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="Выполнить действие" data-toggle="tooltip" data-placement="top" title="Создание папки, загрузка файла, или создание папки и загрузка файла в неё">
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>
        </div>

    </div>
</div>


</body>

</html>