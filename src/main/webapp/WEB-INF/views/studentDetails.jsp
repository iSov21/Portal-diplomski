<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Uredi svoje podatke ${name}</h1>
		
	
	<div class="container login-container container-blog">
	<div>${msg}</div>
	<form:form id="detailsForm" method="post" action="studentDetails" modelAttribute="StudentDetails" class="form" enctype="multipart/form-data">
			
		<div class="input-group" style="display:none;">
			<label class="input-group-addon" for="userId"><i class="fa fa-user"></i></label> 
			<form:input id="userId" name="userId" path="userId" type="text" placeholder="id" class="form-control"/><br>
		</div>
			
		<div class="input-group">
			<label for="contactEmail">Email za kontakt</label>
			<form:input id="contactEmail" name="contactEmail" path="contactEmail" type="text" placeholder="email" class="form-control"/><br>
		</div>
		<form:errors path="contactEmail" cssClass="error" style="color: #FF0000"/>

		
		<div class="input-group">
			<label for="cv">Cv:</label> 
			<form:textarea id="cv" name="cv" path="cv" type="text" placeholder="cv"/><br>
		</div>
		<form:errors path="cv" cssClass="error" style="color: #FF0000"/>
		
		 <div class="input-group">
			<label for="file">File:</label> 
			<input id="file" type="file" name="file"/>
		</div> 
		
		<div class="form-actions">
			<input type="submit" value="Uredi" class="btn btn-block btn-primary btn-default" />
		</div>
	</form:form>
 	</div>
 	
 	<div class="container login-container container-blog">
 	<form:form id="detailsForm" method="post" action="fileUpload" class="form" enctype="multipart/form-data">
		 <div class="input-group">
			<label for="file">File:</label> 
			<input id="file" type="file" name="file" />
		</div>
		
		<div class="form-actions">
			<input type="submit" value="File" class="btn btn-block btn-primary btn-default" />
		</div>
	</form:form>
	</div>
<%@ include file="/WEB-INF/views/template/footer.jsp"%>