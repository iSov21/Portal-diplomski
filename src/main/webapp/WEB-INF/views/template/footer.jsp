

<footer>
	<div>
		<security:authorize access="isAuthenticated()"> 
			<a href="${pageContext.request.contextPath}/logout">Odjava</a>
		</security:authorize>
	</div>
</footer>


</body>

</html>