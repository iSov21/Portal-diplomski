<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

	<h1>Welcome</h1>

	<div>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/registration">Registracija</a>
	</div>
	
	
	<div>
		<security:authorize access="hasRole('ROLE_USER')">
   		 This text is only visible to a user
    	<br/>
		</security:authorize>
	</div>
	<div>
		<security:authorize access="hasRole('ROLE_ADMIN')">
   		 This text is only visible to an admin
    	<br/>
		</security:authorize>
	</div>
	
<%@ include file="/WEB-INF/views/template/footer.jsp"%>