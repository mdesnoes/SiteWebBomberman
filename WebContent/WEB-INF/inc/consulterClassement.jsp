<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	  	<div class="tab-pane fade show active" id="nav-journalier" role="tabpanel" aria-labelledby="nav-journalier-tab">...</div>
	  	<div class="tab-pane fade" id="nav-mensuel" role="tabpanel" aria-labelledby="nav-mensuel-tab">...</div>
	  	<div class="tab-pane fade" id="nav-annuel" role="tabpanel" aria-labelledby="nav-annuel-tab">...</div>
	</div>
</body>
</html>