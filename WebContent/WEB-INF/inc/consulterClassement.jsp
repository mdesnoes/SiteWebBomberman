<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="css/myStyle.css">
</head>
<body>

	<nav>
		<div class="nav nav-tabs" id="nav-tab" role="tablist">
	    	<a class="nav-item nav-link active" id="nav-journalier-tab" data-toggle="tab" 
	    		href="#nav-journalier" role="tab" aria-controls="nav-journalier" aria-selected="true">Journalier</a>
	    	<a class="nav-item nav-link" id="nav-mensuel-tab" data-toggle="tab" 
	    		href="#nav-mensuel" role="tab" aria-controls="nav-mensuel" aria-selected="false">Mensuel</a>
	    	<a class="nav-item nav-link" id="nav-annuel-tab" data-toggle="tab" 
	    		href="#nav-annuel" role="tab" aria-controls="nav-annuel" aria-selected="false">Annuel</a>
		</div>
	</nav>
	
	
	<div class="tab-content" id="nav-tabContent">
	  	<div class="tab-pane fade show active" id="nav-journalier" role="tabpanel" aria-labelledby="nav-journalier-tab">
	  		<div class="container">
	  			<div class="row">
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=journalier&triePar=victoire"/>">Par victoire</a></div>
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=journalier&triePar=defaite"/>">Par defaite</a></div>
	  				<div class="col-md-6"><a href="<c:url value="/accueil?periode=journalier&triePar=ratio"/>">Par ratio victoire/defaite</a></div>
	  			</div>	
	  		</div>
	  	</div>
	  	<div class="tab-pane fade" id="nav-mensuel" role="tabpanel" aria-labelledby="nav-mensuel-tab">
	  		<div class="container">
	  			<div class="row">
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=mensuel&triePar=victoire"/>">Par victoire</a></div>
					<div class="col-md-3"><a href="<c:url value="/accueil?periode=mensuel&triePar=defaite"/>">Par defaite</a></div>
					<div class="col-md-5"><a href="<c:url value="/accueil?periode=mensuel&triePar=ratio"/>">Par ratio victoire/defaite</a></div>
	  			</div>	
	  		</div>
	  	</div>
	  	<div class="tab-pane fade" id="nav-annuel" role="tabpanel" aria-labelledby="nav-annuel-tab">
	  		<div class="container">
	  			<div class="row">
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=annuel&triePar=victoire"/>">Par victoire</a></div>
					<div class="col-md-3"><a href="<c:url value="/accueil?periode=annuel&triePar=defaite"/>">Par defaite</a></div>
					<div class="col-md-5"><a href="<c:url value="/accueil?periode=annuel&triePar=ratio"/>">Par ratio victoire/defaite</a></div>
	  			</div>	
	  		</div>
	  	</div>
	</div>
	
	<c:choose>
        <c:when test="${ empty mapClassement }">
        	<p class="erreur">Aucune partie enregistrée</p>
    	</c:when>
    	<c:otherwise>
			<table class="table table-striped">
		    	<thead class="thead-dark">
		        	<tr><th>Nom du joueur</th>
		        		<c:choose>
		        			<c:when test="${ triePar eq 'victoire' }">
		        				<th>Nombre de victoire</th>
		        			</c:when>
		        			<c:when test="${ triePar eq 'defaite' }">
		        				<th>Nombre de defaite</th>
		        			</c:when>
		        			<c:when test="${ triePar eq 'ratio' }">
		        				<th>Ratio victoire/defaite</th>
		        			</c:when>
		        		</c:choose>
		        	</tr>                   
				</thead>
		
		        <c:forEach items="${ mapClassement }" var="mapClassement" varStatus="boucle">
		        	<tr>
		            	<td><c:out value="${ mapClassement.key }"></c:out></td>
		             	<td><c:out value="${ mapClassement.value }"></c:out></td>
		    	   </tr>
		        </c:forEach>
		    </table>
		</c:otherwise>
	</c:choose>
</body>
</html>