<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Uredi svoje podatke ${name}</h1>
		
	
	<div class="container login-container container-blog">
	<div>${msg}</div>
	<form:form id="detailsForm" method="post" action="${pageContext.request.contextPath}/user/employerDetails" modelAttribute="EmployerDetails" class="form">
			
		<div class="input-group" style="display:none;">
			<label class="input-group-addon" for="userId"><i class="fa fa-user"></i></label> 
			<form:input id="userId" name="userId" path="userId" type="text" placeholder="id" class="form-control"/><br>
		</div>
		
		<div class="input-group">
			<label for="contactEmail">Email za kontakt</label>
			<form:input id="contactEmail" name="contactEmail" path="contactEmail" type="text" placeholder="Email" class="form-control"/><br>
		</div>
		<form:errors path="contactEmail" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label for="address">Adresa</label>
			<form:input id="address" name="address" path="address" type="text" placeholder="Adresa" class="form-control"/><br>
		</div>
		<form:errors path="address" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label for="city">Grad</label>
			<form:input id="city" name="city" path="city" type="text" placeholder="Grad" class="form-control"/><br>
		</div>
		<form:errors path="city" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label for="webAddress">Web adresa</label>
			<form:input id="webAddress" name="webAddress" path="webAddress" type="text" placeholder="Web adresa" class="form-control"/><br>
		</div>
		<form:errors path="webAddress" cssClass="error" style="color: #FF0000"/>
		
		<div class="form-actions">
			<input type="submit" value="Spremi svoje podatke" class="btn btn-block btn-primary btn-default" />
		</div>
	</form:form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>