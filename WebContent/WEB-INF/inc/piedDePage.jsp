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

	<div class="container-fluid piedDePage">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-10">
				<span style="font-size: 13px;font-style: italic;"> Auteur : DESNOES Mathis, DAVIS Luca</span>
			</div>
			
			<div class="col-xs-12 col-sm-12 col-md-2">
				<a href="<c:url value="/listerUtilisateur" />">
				  	<button type="button" class="btn btn-primary btn-sm">Liste des joueurs&nbsp;<i class="fas fa-users"></i></button>
				</a>
			</div>
		</div>
	</div>

</body>
</html>