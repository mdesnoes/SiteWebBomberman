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
    			<i class="fas fa-exclamation-triangle"></i>
       			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
         				<span aria-hidden="true">&times;</span>
       			</button>
      		</div>
      		
     			<div class="modal-body">
      			<p>Vous êtes sur le point de supprimer votre compte !</p>
      			<p><b>Voulez-vous continuer ?</b></p>
      		</div>
	      		
	      	<form method="post" action="suppressionCompte">
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
	        		<button type="submit" class="btn btn-primary">Supprimer mon compte</button>
	      		</div>
      		</form>
      		
    	</div>
  	</div>

</body>
</html>