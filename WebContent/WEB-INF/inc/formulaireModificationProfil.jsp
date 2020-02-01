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

	<form method="post" action="consultationCompte">
		<div class="form-row">
		  	<div class="form-group col-md-6">
		    	<label for="pseudo">Nom d'utilisateur</label>
		      	<input type="text" class="form-control" id="pseudo" name="pseudo" value="" placeholder="nouveau nom d'utilisateur">
		      	<span class="erreur">${form.erreurs['pseudo']}</span>
		    </div>
		    <div class="form-group col-md-6">
		      	<label for="datenaissance">Date de naissance&nbsp;<i class="fas fa-calendar-alt"></i></label>
	  			<input type="date" class="form-control" id="datenaissance" name="datenaissance" />
		    </div>
		</div>
		
		<div class="form-row">
		  	<div class="form-group col-md-6">
		    	<label for="nom">Nom</label>
		      	<input type="text" class="form-control" id="nom" name="nom" placeholder="nouveau nom">
	            <span class="erreur">${form.erreurs['nom']}</span>
		    </div>
		    <div class="form-group col-md-6">
		      	<label for="prenom">Prénom</label>
		      	<input type="text" class="form-control" id="prenom" name="prenom" placeholder="nouveau prénom">
		    	<span class="erreur">${form.erreurs['prenom']}</span>
		    </div>
		</div>
		  
		<div class="form-group">
		    <label for="email">Addresse e-mail&nbsp;<i class="fas fa-at"></i></label>
		    <input type="text" class="form-control" id="email" name="email" placeholder="nouvelle adresse e-mail - ex : toto@example.com">
		    <span class="erreur">${form.erreurs['email']}</span>
		</div>
		
		
		
		<div class="form-row">
		    <div class="form-group col-md-6">
		      	<label for="adresse">Adresse <i class="fas fa-map-marker-alt"></i> (facultative)</label>
		      	<input type="text" class="form-control" id="adresse" name="adresse" placeholder="nouveau numéro et/ou nouveau nom de la rue">
		      	<span class="erreur">${form.erreurs['adresse']}</span>
			</div>
			<div class="form-group col-md-4">
				<label for="cp">Code postal</label>
		     	<input type="text" class="form-control" id="cp" name="cp" placeholder="nouveau code postal"/>
		     	<span class="erreur">${form.erreurs['cp']}</span>
			</div>
			<div class="form-group col-md-2">
				<label for="ville">Ville</label>
		      	<input type="text" class="form-control" id="ville" name="ville" placeholder="nouvelle ville"/>
		    </div>
		</div>
		
		<div style="text-align:center;">
	  		<button type="button" class="btn btn-primary" id="btnModif" data-toggle="modal" data-target="#modalModificationProfil">Modifier votre compte <i class="fas fa-pen"></i></button>
	  	</div>
	  	
	  	<!-- Modal Modification Compte -->
		<div class="modal fade bd-example-modal-lg" id="modalModificationProfil" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
			<c:import url="/WEB-INF/inc/modalModificationProfil.jsp" />
		</div>
	</form>

</body>
</html>