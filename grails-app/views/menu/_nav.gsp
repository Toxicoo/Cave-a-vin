<sec:ifLoggedIn>
	<div id="nav">
		<ul>
			<li>Bienvenue, <sec:username />
			</li>
			<li><g:link controller="logout" action="index">Se déconnecter</g:link></li>
		</ul>

		<div class="homePagePanel">
			<div class="panelTop"></div>

			<div class="panelBody">
				<h1>Menu</h1>
				<ul>
					<li><g:link controller="home" action="index">Accueil</g:link></li>
					<li><g:link controller="bouteille">Bouteilles</g:link></li>
				</ul>
			</div>

			<div class="panelBtm"></div>
		</div>

	</div>
</sec:ifLoggedIn>