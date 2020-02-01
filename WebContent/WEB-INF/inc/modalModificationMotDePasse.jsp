<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        		<h5 class="modal-title">Modifier mon mot de passe</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      		</div>
      		<form method="post" action="consultationCompte">
	      		<div class="modal-body">
		        		<div class="form-group">
	            			<label for="mdpActuel" class="col-form-label">Mot de passe actuel :</label>
	            			<input type="password" class="form-control" id="mdpActuel" name="mdpActuel" />
	            			<span class="erreur">${form.erreurs['mdpActuel']}</span>
	          			</div>
	         			<div class="form-group">
	            			<label for="newMdp" class="col-form-label">Nouveau mot de passe :</label>
	            			<input type="password" class="form-control" id="newMdp" name="newMdp" value="" size="20" maxlength="20"/>
	            			<span class="erreur">${form.erreurs['newMdp']}</span>
	          			</div>
	          			<div class="form-group">
	            			<label for="confirmerNewMdp" class="col-form-label">Confirmer nouveau mot de passe :</label>
	            			<input type="password" class="form-control" id="confirmerNewMdp" name="confirmerNewMdp" value="" size="20" maxlength="20"/>
	            			<span class="erreur">${form.erreurs['confirmerNewMdp']}</span>
	          			</div>
	      		</div>
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
	        		<button type="submit" class="btn btn-primary">Modifier</button>
	      		</div>
      		</form>
    	</div>
  	</div>

</body>
</html>