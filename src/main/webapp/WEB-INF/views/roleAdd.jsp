<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Dodaj rolu</h1>
	
	<div class="container login-container">
	<form:form id="roleForm" method="post" action="${pageContext.request.contextPath}/user/addRole?userId=${userId}" modelAttribute="Role" class="form">

		<div class="input-group">
			<label class="input-group-addon" for="id"><i class="fa fa-clone"></i></label> 
			<form:select id="id" name="id" path="id" items="${roles}" itemLabel="name" itemValue="id"/><br>
		</div>
		
		<div class="form-actions">
			<input type="submit" value="Dodaj rolu" class="btn btn-block btn-primary btn-default" />
		</div>
	</form:form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>