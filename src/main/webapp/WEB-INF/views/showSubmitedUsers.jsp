<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>



	<h1>Ponuda poslova</h1>
	

	<div class="generic-container">

	<div class="panel-title"><span class="lead"></span>${error}</div>

	<div class="container">
		<div class="well">
			<p style="display:none;"><c:out value="${post.id} "></c:out></p> 
			<h4><strong>${post.title}</strong></h4>
			<p><i class="fa fa-user"></i> by <a href="#">${post.username}</a> 
		 	| <i class="fa fa-tags"></i> ${post.category.name}
			| <i class="fa fa-map-marker"></i><a href="#">${post.city}</a>
			| <i class="fa fa-calendar-o"></i><fmt:formatDate value="${post.created}" pattern="dd/MM/yyyy"/></p>
			<div class="row" style="margin-left: 6px;">
			<img alt="img" src="data:image/jpg;charset=utf-8;base64,${post.logo}" width="120" height="100" class="pull-left"/>			  
			<p>${post.text}</p>
			</div>		
		</div>	
	</div>		
	</div>
	
	<div class="generic-container">
	<div class="panel-title"><span class="lead">Lista prijavljenih korisnika</span></div>
	<table class="table table-hover table-bordered table-sm" id="userTable">
		<thead>
			<tr>
				<!-- <th style="display:none;">ID</th> -->
				<th>Ime</th>
				<th>Prezime</th>
				<th>Kontakt email</th>
				<th>Link na cv</th>	            
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${detailsList}" var="user">
			<tr>
				<td style="display:none;"><c:out value="${user.userId} "></c:out></td>               
				<td><c:out value="${user.firstName} "></c:out></td>
				<td><c:out value="${user.lastName} "></c:out></td> 
				<td><c:out value="${user.contactEmail} "></c:out></td>   
				<td><a class="btn btn-default" href="<c:url value='/post/download?id=${user.userId}' />">Skini file</a></td> 
			</tr>                 
			</c:forEach>
		</tbody>
	</table>
	</div>

	


<%@ include file="/WEB-INF/views/template/footer.jsp"%>