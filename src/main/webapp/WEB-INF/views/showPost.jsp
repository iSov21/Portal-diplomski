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
			
			<security:authorize access="hasRole('ROLE_STUDENT')">
			<c:choose>
				<c:when test="${!submited}">
   					<a class="btn btn-default" href="<c:url value='/post/submit?id=${post.id}' />">Prijava</a>
   				</c:when>
   				<c:otherwise>
   					<div class="row" style="margin-left: 6px;">
   						Prijavili ste se na ovaj posao!
   					</div>
   				</c:otherwise>
   			</c:choose>
			</security:authorize>
			
			<security:authorize access="hasRole('ROLE_POSLODAVAC')">
				<c:if test="${employeeBtn}">
   					<a class="btn btn-default" href="<c:url value='/post/submitedList?id=${post.id}' />">Prijavljeni korisnici</a>
   					<a id="editBtn" class="btn btn-default" href="<c:url value='/post/edit?id=${post.id}' />">Uredi post</a>
     				<a id="deleteBtn" class="btn btn-default" onClick="return confirm('Sigurno želite izbrisati post?')" href="<c:url value='/post/delete?id=${post.id}'/>">Obriši post</a>	
   				</c:if>
			</security:authorize>
		
		</div>	
	</div>		
	</div>
	


	


<%@ include file="/WEB-INF/views/template/footer.jsp"%>