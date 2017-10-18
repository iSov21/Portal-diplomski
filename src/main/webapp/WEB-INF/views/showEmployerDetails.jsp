<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/template/header.jsp"%>


<div class="generic-container">
	<div class="container">
		
		<div class="panel-title"><span class="lead">${username}</span></div>
		
		<div class="well">
			<div class="input-group">
				<label for="contactEmail">Email za kontakt</label>
				<p>${employerDetails.contactEmail}</p>
			</div>
	
			<div class="input-group">
				<label for="address">Adresa</label>
				<p>${employerDetails.address}</p>		
			</div>
	
			
			<div class="input-group">
				<label for="city">Grad</label>
				<p>${employerDetails.city}</p>
			</div>
	
			
			<div class="input-group">
				<label for="webAddress">Web adresa: </label>
				<p>${employerDetails.webAddress}</p>
			</div>
	
		</div>
	</div>	
</div>
	

<%@ include file="/WEB-INF/views/template/footer.jsp"%>