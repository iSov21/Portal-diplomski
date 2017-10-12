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
 	
 	<div class="container login-container">
	<form id="searchCityForm" method="post" action="searchCity" class="form">
		
		<label for="id">Pretraži po gradu:</label>
		
		<div class="input-group">
			<label class="input-group-addon" for="id"><i class="fa fa-search"></i></label>
			<input id="city" name="city" type="text" placeholder="Grad" class="form-control"/><br>	
 		</div>	
 		
		<div class="form-actions">
			<input type="submit" value="Pretraži" class="btn btn-block btn-primary btn-default" />
			
		</div>
	</form>
 	</div>
 	
 	<div class="container login-container">
	<form:form id="searchForm" method="post" action="searchCategoryAndCity" modelAttribute="Category" class="form">
		
		<label>Pretraži po kategoriji i gradu:</label>
		
		<div class="input-group">
			<form:select id="id" name="id" path="id">
				<form:option value="" />
   				<form:options items="${category}" itemLabel="name" itemValue="id"/>
			</form:select>
 			<label class="input-group-addon" for="id"><i class="fa fa-search"></i></label>
 		</div>	
 		
 		<div class="input-group">
			<input id="city" name="city" type="text" placeholder="Grad" class="form-control"/><br>	
 		</div>	
 		
		<div class="form-actions">
			<input type="submit" value="Pretraži" class="btn btn-block btn-primary btn-default" />
			
		</div>
	</form:form>
 	</div>
 	
 	<div class="container login-container">
	<form:form id="searchForm" method="post" action="searchByUser" modelAttribute="UserAccount" class="form">
		
		<label>Pretraži po korisniku:</label>	
 		
 		<div class="input-group">
			<form:input id="username" name="username" path="username" type="text" placeholder="Korisničko ime" class="form-control"/><br>	
 		</div>	
 		
		<div class="form-actions">
			<input type="submit" value="Pretraži" class="btn btn-block btn-primary btn-default" />
			
		</div>
	</form:form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>