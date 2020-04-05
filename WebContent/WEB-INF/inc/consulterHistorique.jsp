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

	<div class="col-xs-12 col-sm-12 col-md-12">
		<div class="custom-control custom-switch">
			<input type="checkbox" class="custom-control-input" id="switch" data-target="#formRecherche" 
				aria-expanded="false" aria-controls="formRecherche" data-toggle="collapse">
			<label class="custom-control-label" for="switch">Afficher/Masquer les champs de recherche</label>
		</div>
	</div>
			
	<section id="formRecherche" class="collapse">
   		<form method="post" action="accueil">  			
			<div class="row">
			    <div class="col-xs-12 col-sm-12 col-md-3">
			      <input type="text" class="form-control form-control-sm" id="recherche_id" name="recherche_id" placeholder="Par identifiant">
			    </div>
				<div class="col-xs-12 col-sm-12 col-md-3">
			      <input type="text" class="form-control form-control-sm" name="recherche_date_debut" placeholder="Par date de debut">
			    </div>
			    <div class="col-xs-12 col-sm-12 col-md-3">
			      <input type="text" class="form-control form-control-sm" name="recherche_vainqueur" placeholder="Par vainqueur">
			    </div>
			    <div class="col-xs-12 col-sm-12 col-md-3">
					<button type="submit" class="btn btn-secondary btn-sm">Rechercher&nbsp;<i class="fas fa-search"></i></button>
			    </div>
			</div>				
		</form>
	</section>
	
	
	<c:choose>
        <c:when test="${ empty listeParties }">
        	<p class="erreur" style="text-align:center;">Aucune partie enregistrée</p>
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

	<script>
		$('#formRecherche').collapse({
			show: true
		})
	</script>
</body>
</html>