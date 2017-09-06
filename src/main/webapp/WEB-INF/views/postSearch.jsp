<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Pretraživanje postova</h1>
	
	<div class="container login-container">
	<form:form id="searchForm" method="post" action="search" modelAttribute="Category" class="form">
		
		<label for="id">Pretraži po kategoriji:</label>
		
		<div class="input-group">
			<form:select id="id" name="id" path="id" items="${category}" itemLabel="name" itemValue="id"/><br>
<!--  		<div class="input-group-btn">
 			<button type="submit" class="input-group-addon"><i class="fa fa-search"></i></button> 
 			</div> -->
 			<label class="input-group-addon" for="id"><i class="fa fa-search"></i></label>
 		</div>		
		
		<div class="form-actions">
			<input type="submit" value="Pretraži" class="btn btn-block btn-primary btn-default" />
			
		</div>
	</form:form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>