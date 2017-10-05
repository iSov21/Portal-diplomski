<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>



	<h1>Paginacija postova</h1>
	

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
			</tr>
		</thead>

		<tbody></tbody> 
	</table>

</div>

	<nav>
	<ul class="pagination pagination-circle justify-content-center pg-teal">	
		<c:url value="/post/pagList" var="prev">
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
		    		<c:url value="/post/pagList" var="url">
		   				<c:param name="page" value="${i.index}"/>
		         	</c:url>
		     		<li class="page-item"><a class="page-link" href='<c:out value="${url}" />'>${i.index}</a></li>
		  		</c:otherwise>
			</c:choose>
   		</c:forEach>
    
		<c:url value="/post/pagList" var="next">
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
	
	

<script>

$( document ).ready(function() {	
	
	$.ajax({  
        type : "get",   
        url : "${pageContext.request.contextPath}/post/pagList2?page=2", 
        dataType:'json',        
        success : function(data) 
        {  
        	console.log(data);
        	$('#postTable tbody').empty();
          	$.each( data,function( key,value ) {
          		console.log(value.created);
                var htmlrow = "<tr>"+
    				"<td style=\"display:none;\""+value.id+"</td>"+               
    				"<td>"+value.username+"</td>"+
    				"<td>"+value.category.name+"</td>"+
    				"<td>"+value.city+"</td>"+
    				"<td>"+value.created+"</td>"+
    				"<td>"+value.title+"</td>"+    
    				"<td>"+value.text+"</td>"+ 
    			"</tr>"; 
                      
                $('#postTable tbody').append(htmlrow);
            }); 
            
        }, 
        error : function(e) {  
         	alert('Error: ' + e); 
        }  
   }); 	



	
//select row and save id and disable/enable buttons$('#postTable tbody').on( 'click', 'tr', function (node) {
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
});
 
});
</script>




<%@ include file="/WEB-INF/views/template/footer.jsp"%>