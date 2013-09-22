<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main" />
	</head>
	<body>
		<h1>Bienvenue dans ${cave?.name} !</h1>
		
		<!-- Placeholder pour l'affichage des dernieres bouteilles ajoutees -->
		<div id="recents"></div>
	</body>
	
	<!-- Solution Grails remoteFunction -->
	<g:javascript>
		<g:remoteFunction controller="home" action="bouteillesRecentes" method="GET" update="recents" />
	</g:javascript>
	
</html>
