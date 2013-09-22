
<%@ page import="dojo.grails.Bouteille" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bouteille.label', default: 'Bouteille')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-bouteille" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-bouteille" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list bouteille">
			
				<g:if test="${bouteilleInstance?.label}">
				<li class="fieldcontain">
					<span id="label-label" class="property-label"><g:message code="bouteille.label.label" default="Label" /></span>
					
						<span class="property-value" aria-labelledby="label-label"><g:fieldValue bean="${bouteilleInstance}" field="label"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bouteilleInstance?.millesime}">
				<li class="fieldcontain">
					<span id="millesime-label" class="property-label"><g:message code="bouteille.millesime.label" default="Millesime" /></span>
					
						<span class="property-value" aria-labelledby="millesime-label"><g:fieldValue bean="${bouteilleInstance}" field="millesime"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bouteilleInstance?.photo}">
				<li class="fieldcontain">
					<span id="photo-label" class="property-label"><g:message code="bouteille.photo.label" default="Photo" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${bouteilleInstance?.cave}">
				<li class="fieldcontain">
					<span id="cave-label" class="property-label"><g:message code="bouteille.cave.label" default="Cave" /></span>
					
						<span class="property-value" aria-labelledby="cave-label"><g:link controller="cave" action="show" id="${bouteilleInstance?.cave?.id}">${bouteilleInstance?.cave?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${bouteilleInstance?.id}" />
					<g:link class="edit" action="edit" id="${bouteilleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
