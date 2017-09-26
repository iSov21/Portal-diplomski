<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Registracija</h1>
	
	<div class="container login-container">
	<form:form id="registrationForm" method="post" action="registration" modelAttribute="userDto">

		<div class="input-group">
			<label class="input-group-addon" for="firstName"><i class="fa fa-user"></i></label>
			<form:input id="firstName" name="firstName" path="firstName" type="text" placeholder="Ime" class="form-control"/><br>
		</div>
		
		<div class="input-group">
			<label class="input-group-addon" for="lastName"><i class="fa fa-user"></i></label>
			<form:input id="lastName" name="lastName" path="lastName" type="text" placeholder="Prezime" class="form-control"/><br>
		</div>
		
		<div class="input-group">
			<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
			<form:input id="password" name="password" path="password" type="password" placeholder="lozinka"/><br>
		</div>
		
		<div class="input-group">
			<label class="input-group-addon" for="email"><i class="fa fa-envelope"></i></label> 
			<form:input id="email" name="email" path="email" type="email" placeholder="e-mail"/><br>
		</div>
		
		<div class="form-actions">
			<input type="submit" value="Registracija" class="btn btn-block btn-primary btn-default" />
		</div>

	</form:form>
	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>