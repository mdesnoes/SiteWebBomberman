<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<script type="text/javascript" src="js/myJavaScript.js"></script>

</head>
<body>

	<div class="modal-dialog modal-lg">
    	<div class="modal-content">
    		<div class="modal-header">
        		<h5 class="modal-title">Merci de confirmer les modifications </h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      		</div>
      		<div class="modal-body">
	       		<div class="form-group" id="divModification">
          			
        		</div>
	      	</div>
      		<div class="modal-footer">
        		<button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
   				<button type="submit" class="btn btn-primary">Confirmer</button>
      		</div>
		</div>
  	</div>

</body>
</html>