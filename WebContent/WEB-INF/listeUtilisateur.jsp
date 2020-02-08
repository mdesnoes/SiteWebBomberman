<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/myStyle.css">
	<link rel="stylesheet" href="icons/all.css">
	
</head>
<body style="background-image: url(img/plateau_bomberman.png); background-size: contain;">

	<c:import url="inc/retourAccueil.jsp" />

	<div class="container bandeauListeUtilisateur">
		<br />
		<h2>Liste des joueurs</h2>
		<br />
		<c:choose>
            <c:when test="${ empty sessionScope.listeUtilisateurs }">
                <p class="erreur">Aucun joueur enregistré</p>
            </c:when>
            <c:otherwise>
	            <table class="table table-striped">
	            	<thead class="thead-dark">
	                	<tr><th>Pseudo</th><th>Date inscription</th><th>Nombre de victoires</th><th>Ratio de victoires</th><th>Classement</th></tr>                   
					</thead>
					
	                <c:forEach items="${ sessionScope.listeUtilisateurs }" var="mapUtilisateurs" varStatus="boucle">
		                <tr>
		                    <td><c:out value="${ mapUtilisateurs.value.pseudo }"></c:out></td>
		                    <td><c:out value="${ mapUtilisateurs.value.dateInscription }"></c:out></td>
		                    <td></td>
		                    <td></td>
		                    <td></td>
		                </tr>
	                </c:forEach>
	            </table>
            </c:otherwise>
        </c:choose>
	</div>
	

	
	<c:import url="inc/piedDePage.jsp" />

</body>
</html>