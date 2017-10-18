<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Uredi svoje podatke ${name}</h1>
		
	
	<div class="container login-container container-blog">
	<div>${msg}</div>
	<form:form id="detailsForm" method="post" action="${pageContext.request.contextPath}/user/studentDetails" modelAttribute="StudentDetails" class="form" enctype="multipart/form-data">
			
		<div class="input-group" style="display:none;">
			<label class="input-group-addon" for="userId"><i class="fa fa-user"></i></label> 
			<form:input id="userId" name="userId" path="userId" type="text" placeholder="id" class="form-control"/><br>
		</div>
		
		<div class="input-group">
			<label for="firstName">Ime:</label> 
			<form:input id="firstName" name="firstName" path="firstName" type="text" placeholder="Ime" class="form-control"/><br>
		</div>
		<form:errors path="firstName" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label for="lastName">Prezime:</label> 
			<form:input id="lastName" name="lastName" path="lastName" type="text" placeholder="Prezime" class="form-control"/><br>
		</div>
		<form:errors path="lastName" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label for="contactEmail">Email za kontakt</label>
			<form:input id="contactEmail" name="contactEmail" path="contactEmail" type="text" placeholder="email" class="form-control"/><br>
		</div>
		<form:errors path="contactEmail" cssClass="error" style="color: #FF0000"/>
		
		 <div class="input-group">
			<label for="cv2">Životopis:</label> 
			<input id="cv2" type="file" name="cv2"/>
		</div> 
		
		<div class="form-actions">
			<input type="submit" value="Spremi svoje podatke" class="btn btn-block btn-primary btn-default" />
		</div>
	</form:form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>