<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Uredi post</h1>
	
	<div class="container login-container">
	<form:form id="postForm" method="post" action="edit" modelAttribute="Post" class="form" enctype="multipart/form-data">
		
		
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
			<label class="input-group-addon" for="category"><i class="fa fa-clone"></i></label> 
			<form:select id="category" name="category" path="category" items="${category}" itemLabel="name" itemValue="id"/><br>
		</div>
		
		<div class="input-group">
			<label class="input-group-addon" for="city"><i class="fa fa-user"></i></label>
			<form:input id="city" name="city" path="city" type="text" placeholder="grad" class="form-control"/><br>
		</div>
		<form:errors path="city" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label class="input-group-addon" for="title"><i class="fa fa-clone"></i></label> 
			<form:input id="title" name="title" path="title" type="text" placeholder="naslov"/><br>
		</div>
		<form:errors path="title" cssClass="error" style="color: #FF0000"/>
		
		<div class="input-group">
			<label class="input-group-addon" for="text"><i class="fa fa-align-center "></i></label> 
			<form:input id="text" name="text" path="text" type="text" placeholder="tekst" rows="5"/><br>
		</div>
		
		<div class="input-group">
			<label for="logo2">Logo:</label> 
			<input id="logo2" type="file" name="logo2"/>
		</div> 
		
		<div class="form-actions">
			<input type="submit" value="Spremi post" class="btn btn-block btn-primary btn-default" />
		</div>
	</form:form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>