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

	<div class="modal-dialog modal-dialog-centered" role="document">
    	<div class="modal-content">
      		<div class="modal-header">
        		<h5 class="modal-title">Se connecter</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      		</div>
      		<form method="post" action="accueil">
	      		<div class="modal-body">
	      				<span class="erreur">${form.erreurs['donnee_incorrecte']}</span>
		        		<div class="form-group">
	            			<label for="pseudo" class="col-form-label">Nom d'utilisateur :</label>
	            			<input type="text" class="form-control" id="pseudo" name="pseudo" value="<c:out value="${utilisateur.pseudo}"/>" />
	            			<span class="erreur">${form.erreurs['pseudo']}</span>
	          			</div>
	         			<div class="form-group">
	            			<label for="password" class="col-form-label">Mot de passe :</label>
	            			<input type="password" class="form-control" id="password" name="password" value="" size="20" maxlength="20"/>
	            			<span class="erreur">${form.erreurs['password']}</span>
	          			</div>
	          			<div class="form-group">
	          				<label for="memoire">Se souvenir de moi&nbsp;</label>
                			<input type="checkbox" id="memoire" name="memoire" />
	          			</div>
	      		</div>
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
	        		<button type="submit" class="btn btn-primary">Connexion</button>
	      		</div>
      		</form>
    	</div>
  	</div>

</body>
</html>