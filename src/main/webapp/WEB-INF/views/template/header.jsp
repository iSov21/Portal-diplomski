<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Portal</title>
	
	<!-- Bootstrap core CSS -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" type="text/css" rel="stylesheet">
    
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/custom.css" />">
	<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css" />"> 
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/mdb.min.css" />">
    
    <!-- JQuery -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-3.2.1.min.js" />"></script>
	
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>



<body>

<header>
	<nav class="navbar navbar-fixed-top rgba-teal-slight" >
<!-- 	<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-pink scrolling-navbar"> -->
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/post/blogPosts">Oglasi</a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/post/search">Pretraži oglase</a>
		
		<security:authorize access="hasRole('ROLE_ADMIN')">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/user/list">Administracija korisnika</a>		
			<a class="navbar-brand" href="${pageContext.request.contextPath}/post/list">Administracija oglasa</a>
		</security:authorize>
		
		
		<security:authorize access="hasRole('ROLE_STUDENT')">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/post/submitedJobs">Moje prijave</a>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/user/studentDetails">Detalji</a>
		</security:authorize>
		
		<security:authorize access="hasRole('ROLE_POSLODAVAC')">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/post/postsByUser">Moji oglasi</a>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/user/employerDetails">Detalji</a>
		</security:authorize>
		
		<security:authorize access="!isAuthenticated()">
			<a class="navbar-brand" href="${pageContext.request.contextPath}/registration">Registracija</a>
		</security:authorize>
		<div class="pull-right">
			<security:authorize access="!isAuthenticated()">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/login">Prijava</a>
			</security:authorize>
			<security:authorize access="isAuthenticated()"> 
				<div class="navbar-brand"> Bok <security:authentication var="logged" property="principal.username" />${logged}! </div>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/logout">Odjava</a>
			</security:authorize>
		</div>
	</nav>
</header>

