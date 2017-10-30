<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>



	<h1>Administracija postova</h1>
	

	<div class="generic-container">
		<div>
     		<a class="btn btn-default" href="<c:url value='/post/add' />">Novi post</a>
     		<a id="editBtn" class="btn btn-default disabled" href="<c:url value='/post/edit' />">Uredi odabrani post</a>
     		<a id="deleteBtn" class="btn btn-default disabled" onClick="return confirm('Sigurno želite izbrisati post?')" href="<c:url value='/post/delete'/>">Obriši odabrani post</a>
    	</div>
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
	
	<nav>
		<ul class="pagination pagination-circle justify-content-center pg-teal">	
			<c:url value="/post/list" var="prev">
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
						<c:url value="/post/list" var="url">
					 		<c:param name="page" value="${i.index}"/>
					    </c:url>
					 	<li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			    
			<c:url value="/post/list" var="next">
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

//select row and save id and disable/enable buttons
$('#postTable tbody').on( 'click', 'tr', function (node) {
	
	var id = null;
	
	if ( $(this).hasClass('selected') ) {
        $(this).removeClass('selected');
        $('#deleteBtn').addClass('disabled');
        $('#editBtn').addClass('disabled');
    }
    else {
        $('tr.selected').removeClass('selected');
        $(this).addClass('selected');
        id = $('.selected').find( ":hidden" ).text();
        $('#editBtn').attr("href", "<c:url value='/post/edit?id="+id+"'/>").removeClass('disabled');
        $('#deleteBtn').attr("href", "<c:url value='/post/delete?id="+id+"'/>").removeClass('disabled');
    } 

	console.log(id);
} );

});
</script>

<%@ include file="/WEB-INF/views/template/footer.jsp"%>