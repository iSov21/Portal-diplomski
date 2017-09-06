<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

	<h1>Register Page</h1>

	<div class="login-container">
	<div class="login-card">
	<div class="login-form">
	<form:form id="registerForm" method="post" commandName="UserAccount">
		<div class="input-group">
			<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
			<form:input id="username" name="username" path="username" type="text" placeholder="korisničko ime" class="form-control"/><br>
		</div>
		
		<div class="input-group">
			<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
			<form:password id="password" name="password" path="password" placeholder="lozinka" class="form-control"/><br>
		</div>
		
		<div class="form-actions">
			<input type="submit" value="Registracija" class="btn btn-block btn-primary btn-default" />
		</div>

	</form:form>
	</div>
	</div>
	</div>


<%@ include file="/WEB-INF/views/template/footer.jsp"%>