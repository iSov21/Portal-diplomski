<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="/WEB-INF/views/template/header.jsp"%>


<h2>Submitted File</h2>
<table>
    <tr>
        <td>OriginalFileName:</td>
        <td>${file.originalFilename}</td>
    </tr>
    <tr>
        <td>Type:</td>
        <td>${file.contentType}</td>
    </tr>
</table>
	
	<p>${base}</p>
	<p>${encode}</p>
	<p>${decode}</p>
	<p>${image}</p>
	
	<p><img alt="img" src="data:image/jpg;charset=utf-8;base64,${base}"/></p>
	<p><img alt="img" src="data:image/jpg;charset=utf-8;base64,${encode}"/></p>
	<p><img alt="img" src="data:image/jpg;charset=utf-8;base64,${decode}"/></p>
	<p><img alt="img" src="data:image/jpg;charset=utf-8;base64,${image}"/></p>


	


<%@ include file="/WEB-INF/views/template/footer.jsp"%>