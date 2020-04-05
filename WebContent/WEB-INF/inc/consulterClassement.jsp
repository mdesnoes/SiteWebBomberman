<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<nav>
		<div class="nav nav-tabs" id="nav-tab" role="tablist">
	    	<a class="${ sessionScope.periode eq 'journalier' ? 'nav-item nav-link active' : 'nav-item nav-link' }"
	    		id="nav-journalier-tab" data-toggle="tab" 
	    		href="#nav-journalier" role="tab" aria-controls="nav-journalier">Journalier</a>
	    	<a class="${ sessionScope.periode eq 'mensuel' ? 'nav-item nav-link active' : 'nav-item nav-link' }"
	    		id="nav-mensuel-tab" data-toggle="tab" 
	    		href="#nav-mensuel" role="tab" aria-controls="nav-mensuel">Mensuel</a>
	    	<a class="${ sessionScope.periode eq 'annuel' ? 'nav-item nav-link active' : 'nav-item nav-link' }"
	    		id="nav-annuel-tab" data-toggle="tab" 
	    		href="#nav-annuel" role="tab" aria-controls="nav-annuel">Annuel</a>
		</div>
	</nav>
	
	
	<div class="tab-content" id="nav-tabContent">
	  	<div class="tab-pane fade" id="nav-journalier" role="tabpanel" aria-labelledby="nav-journalier-tab">
	  		<div class="container">
	  			<div class="row" style="text-align:center;">
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=journalier&triePar=victoire"/>">Par victoire</a></div>
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=journalier&triePar=defaite"/>">Par defaite</a></div>
	  				<div class="col-md-6"><a href="<c:url value="/accueil?periode=journalier&triePar=ratio"/>">Par ratio victoire/defaite</a></div>
	  			</div>
	  		</div>
	  	</div>
	  	<div class="tab-pane fade" id="nav-mensuel" role="tabpanel" aria-labelledby="nav-mensuel-tab">
	  		<div class="container">
	  			<div class="row" style="text-align:center;">
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=mensuel&triePar=victoire"/>">Par victoire</a></div>
					<div class="col-md-3"><a href="<c:url value="/accueil?periode=mensuel&triePar=defaite"/>">Par defaite</a></div>
					<div class="col-md-5"><a href="<c:url value="/accueil?periode=mensuel&triePar=ratio"/>">Par ratio victoire/defaite</a></div>
	  			</div>
	  		</div>
	  	</div>
	  	<div class="tab-pane fade" id="nav-annuel" role="tabpanel" aria-labelledby="nav-annuel-tab">
	  		<div class="container">
	  			<div class="row" style="text-align:center;">
	  				<div class="col-md-3"><a href="<c:url value="/accueil?periode=annuel&triePar=victoire"/>">Par victoire</a></div>
					<div class="col-md-3"><a href="<c:url value="/accueil?periode=annuel&triePar=defaite"/>">Par defaite</a></div>
					<div class="col-md-5"><a href="<c:url value="/accueil?periode=annuel&triePar=ratio"/>">Par ratio victoire/defaite</a></div>
	  			</div>	
	  		</div>
	  	</div>
	</div>
	
	<c:choose>
        <c:when test="${ empty sessionScope.mapClassement }">
        	<p class="erreur" style="text-align:center;">Pas de classement</p>
    	</c:when>
    	<c:otherwise>
			<table class="table table-striped">
		    	<thead class="thead-dark">
		        	<tr>
		        		<th>Placement</th>
		        		<th>Pseudo du joueur</th>
		        		<c:choose>
		        			<c:when test="${ sessionScope.triePar eq 'victoire' }">
		        				<th>Nombre de victoire</th>
		        			</c:when>
		        			<c:when test="${ sessionScope.triePar eq 'defaite' }">
		        				<th>Nombre de defaite</th>
		        			</c:when>
		        			<c:when test="${ sessionScope.triePar eq 'ratio' }">
		        				<th>Ratio victoire/defaite</th>
		        			</c:when>
		        		</c:choose>
		        	</tr>
				</thead>
		
		        <c:forEach items="${ sessionScope.mapClassement }" var="mapClassement" varStatus="boucle">
		        	<tr>
			        	<c:choose>
			        		<c:when test="${ boucle.index + 1 eq 1 }">
			        				<td><c:out value="${ boucle.index + 1 }"/>&nbsp;<i class="fas fa-trophy" style="color:#DCBD0E"></i></td>
			        		</c:when>
			        		<c:when test="${ boucle.index + 1 eq 2 }">
			        				<td><c:out value="${ boucle.index + 1 }"/>&nbsp;<i class="fas fa-medal" style="color:#BCBCBB"></i></td>
			        		</c:when>
			        		<c:when test="${ boucle.index + 1 eq 3 }">
			        				<td><c:out value="${ boucle.index + 1 }"/>&nbsp;<i class="fas fa-award" style="color:#8b4513"></i></td>
			        		</c:when>
			        		<c:otherwise>
			        			<td><c:out value="${ boucle.index + 1 }"/></td>
			        		</c:otherwise>
			        	</c:choose>
		            	<td><c:out value="${ mapClassement.key }"/></td>
		             	<td><fmt:formatNumber type="${ sessionScope.triePar eq 'ratio' ? 'percent' : 'number' }" value="${ mapClassement.value }"/></td>
		    	   </tr>
		        </c:forEach>
		    </table>
		</c:otherwise>
	</c:choose>
</body>
</html>