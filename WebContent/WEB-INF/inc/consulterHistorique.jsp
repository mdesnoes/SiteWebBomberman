<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
        <c:when test="${ empty listeParties }">
        	<p class="erreur">Aucune partie enregistrée</p>
    	</c:when>
    	<c:otherwise>
			<table class="table table-striped">
		    	<thead class="thead-dark">
		        	<tr><th>Id de la partie</th><th>Date de debut</th><th>Date de fin</th><th>Vainqueur</th></tr>                   
				</thead>
		
		        <c:forEach items="${ listeParties }" var="mapParties" varStatus="boucle">
		        	<tr>
		            	<td><c:out value="${ mapParties.value.id }"></c:out></td>
		             	<td><c:out value="${ mapParties.value.dateDebut }"></c:out></td>
		             	<td><c:out value="${ mapParties.value.dateFin }"></c:out></td>
		             	<td><c:out value="${ mapParties.value.vainqueur }"></c:out></td>
		    	   </tr>
		        </c:forEach>
		    </table>
		</c:otherwise>
	</c:choose>

</body>
</html>