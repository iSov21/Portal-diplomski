<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

	<h1>Portal za studentsku praksu</h1>

	<div class="center">
		Dobro došli na stranice portala za studentsku praksu, možete 
		<a href="${pageContext.request.contextPath}/post/list">pregledati oglase</a> ili se 
		<a href="${pageContext.request.contextPath}/registration">registrirati</a> ili se
		<a href="${pageContext.request.contextPath}/login">prijaviti</a> za daljnu uporabu.
	</div>
	
	
	<div>

	</div>
	
<%@ include file="/WEB-INF/views/template/footer.jsp"%>