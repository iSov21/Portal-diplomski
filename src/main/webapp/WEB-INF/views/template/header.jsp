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
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Portal za studentsku praksu</title>
	
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
	<nav class="navbar navbar-default rgba-teal-slight">
	
	 	<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		              <span class="sr-only">Toggle navigation</span>
		              <span class="icon-bar"></span>
		              <span class="icon-bar"></span>
		              <span class="icon-bar"></span>
		     </button>
		     <a class="navbar-brand" href="${pageContext.request.contextPath}/">Portal za studentsku praksu</a>
	     </div>
	
		<div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
		
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/post/list">Oglasi</a></li>
				<li><a href="${pageContext.request.contextPath}/post/search">Pretraži oglase</a></li>
				
				<security:authorize access="hasRole('ROLE_ADMIN')">
					<li><a href="${pageContext.request.contextPath}/user/list">Administracija korisnika</a></li>	
					<li><a href="${pageContext.request.contextPath}/post/listAdmin">Administracija oglasa</a></li>
				</security:authorize>
				
				
				<security:authorize access="hasRole('ROLE_STUDENT')">
					<li><a href="${pageContext.request.contextPath}/post/submitedJobs">Moje prijave</a></li>
					<li><a href="${pageContext.request.contextPath}/user/studentDetails">Detalji</a></li>
				</security:authorize>
				
				<security:authorize access="hasRole('ROLE_POSLODAVAC')">
					<li><a href="${pageContext.request.contextPath}/post/postsByUser">Moji oglasi</a></li>
					<li><a href="${pageContext.request.contextPath}/user/employerDetails">Detalji</a></li>
					<li><a href="${pageContext.request.contextPath}/post/add">Napravi novi oglas</a></li>
				</security:authorize>
			</ul>
			
			
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="!isAuthenticated()">
					<li><a href="${pageContext.request.contextPath}/registration">Registracija</a></li>
					<li><a href="${pageContext.request.contextPath}/login">Prijava</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()"> 
						<li><div class="hello"><span class="fa fa-user icon-size"></span>
						Bok <security:authentication var="logged" property="principal.username" />${logged}!</div></li>
						<li><a href="${pageContext.request.contextPath}/logout">Odjava</a></li>
				</security:authorize>
			</ul>
			

		</div> 


	</nav>
</header>

