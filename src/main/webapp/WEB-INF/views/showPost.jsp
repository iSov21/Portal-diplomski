<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>



	<h1>Ponuda poslova</h1>
	

	<div class="generic-container">

	<div class="panel-title"><span class="lead"></span>${error}</div>

	<div class="container">
		<div class="well">
			<p style="display:none;"><c:out value="${post.id} "></c:out></p> 
			<i class="fa fa-user"></i> by <a href="#">${post.username}</a> 
		 	| <i class="fa fa-tags"></i> ${post.category.name}
			| <i class="fa fa-map-marker"></i><a href="#">${post.city}</a>
			| <i class="fa fa-calendar-o"></i><fmt:formatDate value="${post.created}" pattern="dd/MM/yyyy"/>
			<h4><strong>${post.title}</strong></h4>     
			<p>${post.text}</p>
			<security:authorize access="hasRole('ROLE_STUDENT')">
   					<a class="btn btn-default" href="<c:url value='/post/sign?id=${post.id}' />">Prijava</a>
			</security:authorize>
		
		</div>	
	</div>		
	</div>
	
	<div class="generic-container">
	<div class="panel-title"><span class="lead">Lista prijavljenih korisnika</span></div>
	<table class="table table-hover table-bordered table-sm" id="userTable">
		<thead>
			<tr>
				<!-- <th style="display:none;">ID</th> -->
				<th>Korisničko ime</th>
				<th>Lozinka</th>
				<th>E-mail</th>
				<th>Rola</th>	            
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
			<tr>
				<td style="display:none;"><c:out value="${user.id} "></c:out></td>               
				<td><c:out value="${user.username} "></c:out></td>
				<td><c:out value="${user.password} "></c:out></td> 
				<td><c:out value="${user.email} "></c:out></td>   
				<td><c:out value="${user.role} "></c:out></td>
			</tr>                     
			</c:forEach>
		</tbody>
	</table>
	</div>
	


<%@ include file="/WEB-INF/views/template/footer.jsp"%>