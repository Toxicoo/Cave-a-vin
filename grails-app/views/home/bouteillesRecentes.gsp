<div>
	<g:if test="${bouteilles}">
		<div class="message">
			<h4><g:message code="bouteilles.recent.label" default="Dernières bouteilles ajoutées"/></h4>
			<g:each var="bouteille" in="${bouteilles}">
				<g:link controller="bouteille" action="show" id="${bouteille.id}">${bouteille.label}</g:link>
			</g:each>
		</div>
	</g:if>
</div>