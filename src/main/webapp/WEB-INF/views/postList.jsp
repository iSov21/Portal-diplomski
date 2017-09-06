<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>



	<h1>Administracija postova</h1>
<%-- 	Hello <security:authentication property="principal.username" />! --%>
	

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
				<th>Kreiran</th>
				<th>Naslov</th>
				<th>Tekst</th>		            
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="name">
			<tr>
				<td style="display:none;"><c:out value="${name.id} "></c:out></td>               
				<td><c:out value="${name.username} "></c:out></td>
				<td><c:out value="${name.category.name} "></c:out></td>
				<td><fmt:formatDate value="${name.created}" pattern="dd/MM/yyyy"/></td>
				<%-- <td><c:out value="${name.created} "></c:out></td> --%>
				<td><c:out value="${name.title} "></c:out></td>    
				<td><c:out value="${name.text} "></c:out></td>
			</tr>                     
			</c:forEach>
		</tbody>
	</table>
	
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