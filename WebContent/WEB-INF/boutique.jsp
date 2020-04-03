<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Boutique</title>
	
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="css/myStyle.css">
	<link rel="stylesheet" href="icons/all.css">
	
</head>
<body style="background-image: url(img/plateau_bomberman.png); background-size: contain;">

	<c:import url="inc/retourAccueil.jsp" />

	<div class="container bandeauListeUtilisateur">
		<br />
		<h2>Boutique</h2>
		<br />
		<c:choose>
            <c:when test="${ empty sessionScope.listeObjets }">
                <p class="erreur">Aucun objet dans la boutique</p>
            </c:when>
            <c:otherwise>
	            <table class="table table-striped">
	            	<thead class="thead-dark">
	                	<tr><th>Objet</th><th>Prix</th></tr>                   
					</thead>
					
	                <c:forEach items="${ sessionScope.listeObjets }" var="mapUtilisateurs" varStatus="boucle">
		                <tr>
		                    <td><c:out value="${ mapUtilisateurs.value.objet }"></c:out></td>
		                    <td><c:out value="${ mapUtilisateurs.value.prix }"></c:out></td>
		                </tr>
	                </c:forEach>
	            </table>
            </c:otherwise>
        </c:choose>
	</div>
	

	
	<c:import url="inc/piedDePage.jsp" />

</body>
</html>