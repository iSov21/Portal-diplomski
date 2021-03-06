<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

	<h1>Administracija korisnika</h1>
	
	
	<div class="generic-container">
		<div>
     		<a class="btn btn-default" href="<c:url value='/user/add' />">Dodaj novog korisnika</a>
     		<a id="editBtn" class="btn btn-default disabled" href="<c:url value='/user/edit' />">Uredi odabranog korisnika</a>
     		<a id="deleteBtn" class="btn btn-default disabled" onClick="return confirm('Sigurno želite izbrisati korisnika?')" href="<c:url value='/user/delete'/>">Obriši odabranog korisnika</a> 		
    		<a id="roleBtn" class="btn btn-default disabled" href="<c:url value='/user/addRola' />">Dodaj ulogu korisniku</a>	
    	</div>
	<div class="panel-title"><span class="lead">Lista korisnika</span></div>
	<table class="table table-hover table-bordered table-sm" id="userTable">
		<thead>
			<tr>
				<!-- <th style="display:none;">ID</th> -->
				<th>Korisničko ime</th>
				<!--  <th>Lozinka</th> -->
				<th>E-mail</th>
				<th>Rola</th>	            
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="name">
			<tr>
				<td style="display:none;"><c:out value="${name.id} "></c:out></td>               
				<td><c:out value="${name.username} "></c:out></td>
				<!-- <td><c:out value="${name.password} "></c:out></td> -->
				<td><c:out value="${name.email} "></c:out></td>   
				<td><c:out value="${name.role} "></c:out></td>
			</tr>                     
			</c:forEach>
		</tbody>
	</table>
	
	<nav>
		<ul class="pagination pagination-circle justify-content-center pg-teal">	
			<c:url value="/user/list" var="prev">
				<c:param name="page" value="${page-1}"/>
			</c:url>
			<c:choose>
				<c:when test="${page > 1}">
					<li class="page-item"><a class="page-link" href="<c:out value="${prev}" />">«</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled"><a class="page-link" href="<c:out value="${prev}" />">«</a></li>
				</c:otherwise>
			</c:choose>
					   
			<c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
				<c:choose>
					<c:when test="${page == i.index}">
						<li class="page-item active"><span class="page-link">${i.index}</span></li>
					</c:when>
					<c:otherwise>
						<c:url value="/user/list" var="url">
					 		<c:param name="page" value="${i.index}"/>
					    </c:url>
					 	<li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			    
			<c:url value="/user/list" var="next">
				<c:param name="page" value="${page + 1}"/>
			</c:url>
			<c:choose>
				<c:when test="${page + 1 <= maxPages}">
					<li class="page-item"><a class="page-link" href='<c:out value="${next}" />'>»</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item disabled"><a class="page-link" href='<c:out value="${next}" />'>»</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>	
	
	</div>
	
	
<script>
$( document ).ready(function() {

//select row and save oid 
$('#userTable tbody').on( 'click', 'tr', function (node) {
	
	var id = null;
	
	if ( $(this).hasClass('selected') ) {
        $(this).removeClass('selected');
        $('#deleteBtn').addClass('disabled');
        $('#editBtn').addClass('disabled');
        $('#roleBtn').addClass('disabled');
    }
    else {
        $('tr.selected').removeClass('selected');
        $(this).addClass('selected');
        id = $('.selected').find( ":hidden" ).text();
        $('#editBtn').attr("href", "<c:url value='/user/edit?id="+id+"'/>").removeClass('disabled');
        $('#deleteBtn').attr("href", "<c:url value='/user/delete?id="+id+"'/>").removeClass('disabled');
        $('#roleBtn').attr("href", "<c:url value='/user/addRole?userId="+id+"'/>").removeClass('disabled');
    } 

	console.log(id);
} );

});
</script>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>