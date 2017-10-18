<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


	<h1>Pretraživanje oglasa</h1>
 	
 	<div class="container login-container">
	<form id="searchForm" method="get" action="searchByCategoryAndCity" class="form">
		
		<label>Pretraži po kategoriji i gradu:</label>
		
		<div class="input-group">
			<select id="categoryId" name="categoryId">
				<option value=""/>
				<c:forEach var="categ" items="${category}">
   					<option value="${categ.id}">${categ.name}</option>
   				</c:forEach>
			</select>
 			<label class="input-group-addon" for="id"><i class="fa fa-search"></i></label>
 		</div>	
 		
 		<div class="input-group">
			<input id="city" name="city" type="text" placeholder="Grad" class="form-control"/><br>	
 		</div>	
 		
		<div class="form-actions">
			<input type="submit" value="Pretraži" class="btn btn-block btn-primary btn-default" />
			
		</div>
	</form>
 	</div>
 	
 	<div class="container login-container">
	<form id="searchForm" method="get" action="searchByUser" class="form">
		
		<label>Pretraži po korisniku:</label>	
 		
 		<div class="input-group">
			<input id="username" name="username" type="text" placeholder="Korisničko ime" class="form-control"/><br>	
 		</div>	
 		
		<div class="form-actions">
			<input type="submit" value="Pretraži" class="btn btn-block btn-primary btn-default" />
			
		</div>
	</form>
 	</div>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>