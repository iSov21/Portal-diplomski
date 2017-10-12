<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>



	<h1>Poslovi na koje ste se prijavili</h1>
	

	<div class="generic-container">
	<div class="panel-title"><span class="lead">Lista postova</span></div>
	<table class="table table-hover table-bordered table-sm" id="postTable">
		<thead>
			<tr>
				<!-- <th style="display:none;">ID</th> -->
				<th>Korisničko ime</th>
				<th>Kategorija</th>
				<th>Grad</th>
				<th>Kreiran</th>
				<th>Naslov</th>
				<th>Tekst</th>
				<th>Slika</th>		            
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="post">
			<tr>
				<td style="display:none;"><c:out value="${post.id} "></c:out></td>               
				<td><c:out value="${post.username} "></c:out></td>
				<td><c:out value="${post.category.name} "></c:out></td>
				<td><c:out value="${post.city} "></c:out></td>
				<td><fmt:formatDate value="${post.created}" pattern="dd/MM/yyyy"/></td>
				<%-- <td><c:out value="${name.created} "></c:out></td> --%>
				<td><c:out value="${post.title} "></c:out></td>    
				<td><c:out value="${post.text} "></c:out></td>
				<td><img alt="img" src="data:image/jpg;charset=utf-8;base64,${post.logo}" width="50" height="50"/></td>
			</tr>                     
			</c:forEach>
		</tbody>
	</table>
	
	</div>
	

<%@ include file="/WEB-INF/views/template/footer.jsp"%>