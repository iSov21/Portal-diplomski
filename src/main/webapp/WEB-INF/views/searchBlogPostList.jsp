<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>



	<h1>Ponuda poslova</h1>
	

	<div class="generic-container">
		<security:authorize access="hasRole('ROLE_POSLODAVAC')">
			<div><a class="btn btn-default" href="<c:url value='/post/add' />">Novi post</a></div>
	 	</security:authorize>

		<c:choose>
			<c:when test="${empty list}">
				<div class="container">
					<div class="panel-title"><span class="lead">Trenutno nema ponuđenih oglasa</span></div>
				</div>
			</c:when>
			
			<c:otherwise>
			<div class="container">
			<div class="panel-title"><span class="lead">Lista postova</span></div>
				<c:forEach items="${list}" var="post">
				<div class="well">
					<p style="display:none;"><c:out value="${post.id} "></c:out></p> 
					<img alt="img" src="data:image/jpg;charset=utf-8;base64,${post.logo}" width="140" height="120" class="pull-left"/>
					<h4><strong>${post.title}</strong></h4>     
					<p>${post.text}</p>
					<p><a href="${pageContext.request.contextPath}/post/showPost?id=${post.id}">Read more</a></p>
					<i class="fa fa-user"></i> by <a href="${pageContext.request.contextPath}/user/showEmployerDetails?username=${post.username}">${post.username}</a> 
				 	| <i class="fa fa-tags"></i> ${post.category.name}
					| <i class="fa fa-map-marker"></i><a href="#"> ${post.city}</a>
					| <i class="fa fa-calendar-o"></i><fmt:formatDate value="${post.created}" pattern="dd/MM/yyyy"/>
				</div>
				</c:forEach>
				
			<nav>		
				<ul class="pagination pagination-circle justify-content-center pg-teal">
				<c:choose>	
					<c:when test="${cSearch}">
						<c:url value="/post/searchByCategoryAndCity" var="prev">
							<c:param name="page" value="${page-1}"/>
							<c:param name="categoryId" value="${categoryId}"/>
							<c:param name="city" value="${city}"/>
						</c:url>
					</c:when>
					<c:otherwise>
	        			<c:url value="/post/searchByUser" var="prev">
							<c:param name="page" value="${page-1}"/>
							<c:param name="username" value="${username}"/>
						</c:url>
	   				</c:otherwise>
				</c:choose>
					<c:choose>
						<c:when test="${page > 1}">
							<li class="page-item"><a class="page-link" href="<c:out value="${prev}" />">«</a></li>
						</c:when>
						<c:otherwise>
				       		<li class="page-item disabled"><a class="page-link disabled" href="<c:out value="${prev}" />">«</a></li>
				    	</c:otherwise>
					</c:choose>
					   
					<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
						<c:choose>
							<c:when test="${page == i.index}">
								<li class="page-item active"><span class="page-link">${i.index}</span></li>
							</c:when>
							<c:otherwise>
								<c:choose>	
									<c:when test="${cSearch}">
										<c:url value="/post/searchByCategoryAndCity" var="url">
											<c:param name="page" value="${i.index}"/>
											<c:param name="categoryId" value="${categoryId}"/>
											<c:param name="city" value="${city}"/>
										</c:url>
									</c:when>
									<c:otherwise>
					        			<c:url value="/post/searchByUser" var="url">
							   				<c:param name="page" value="${i.index}"/>
							   				<c:param name="username" value="${username}"/>
							         	</c:url>
					   				</c:otherwise>
								</c:choose>
							
					     		<li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
					  		</c:otherwise>
						</c:choose>
			   		</c:forEach>
			    			
					<c:choose>	
						<c:when test="${cSearch}">
							<c:url value="/post/searchByCategoryAndCity" var="next">
								<c:param name="page" value="${page+1}"/>
								<c:param name="categoryId" value="${categoryId}"/>
								<c:param name="city" value="${city}"/>
							</c:url>
						</c:when>
						<c:otherwise>
		        			<c:url value="/post/searchByUser" var="next">
								<c:param name="page" value="${page+1}"/>
								<c:param name="username" value="${username}"/>
							</c:url>
		   				</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${page + 1 <= maxPages}">
							<li class="page-item"><a class="page-link" href='<c:out value="${next}" />'>»</a></li>
						</c:when>
						<c:otherwise>
				       		<li class="page-item disabled"><a class="page-link disabled" href='<c:out value="${next}" />'>»</a></li>
				    	</c:otherwise>
					</c:choose>
				</ul>
			</nav>		
				
				
					
			</div>
			</c:otherwise>
		</c:choose>
	</div>



<%@ include file="/WEB-INF/views/template/footer.jsp"%>