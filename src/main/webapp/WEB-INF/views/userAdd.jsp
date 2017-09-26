<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Dodaj novog korisnika</h1>
	
	<div class="container login-container">
	<form:form id="studentForm" method="post" action="add" modelAttribute="UserAccount" class="form">
			
		<div class="input-group" style="display:none;">
			<label class="input-group-addon" for="id"><i class="fa fa-user"></i></label> 
			<form:input id="id" name="id" path="id" type="text" placeholder="id" class="form-control"/><br>
		</div>
			
		<div class="input-group">
			<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
			<form:input id="username" name="username" path="username" type="text" placeholder="korisničko ime" class="form-control"/><br>
		</div>
		<form:errors path="username" cssClass="error" style="color: #FF0000"/>

		
		<div class="input-group">
			<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label> 
			<form:input id="password" name="password" path="password" type="password" placeholder="lozinka"/><br>
		</div>
		<form:errors path="password" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label class="input-group-addon" for="email"><i class="fa fa-envelope"></i></label> 
			<form:input id="email" name="email" path="email" type="email" placeholder="e-mail"/><br>
		</div>
		<form:errors path="email" cssClass="error" style="color: #FF0000"/>
		
		<div class="form-actions">
			<input type="submit" value="Spremi" class="btn btn-block btn-primary btn-default" />
		</div>
	</form:form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>