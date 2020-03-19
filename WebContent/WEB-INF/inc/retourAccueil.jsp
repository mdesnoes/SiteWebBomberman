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

	<div class="container-fluid enTete">
		<div class="row">
			<div class="col-md-3" style="font-size:20px; margin-top:8px;">
				<i class="fas fa-hand-point-left"></i> &nbsp;&nbsp;
				<a href="<c:url value="/accueil?periode=${ periode }&triePar=${ triePar }" />">Retour vers la page d'accueil</a>
			</div>
			<div class="col-md-8" style="margin-top:8px;font-size:20px;">
				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${ form.resultat }</p>
			</div>
			<div class="col-md-1">
				<img alt="" src="img/logoUA.png" id="imgLogo">
			</div>
		</div>
	</div>

</body>
</html>