
<%@ page import="dojo.grails.Bouteille" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'bouteille.label', default: 'Bouteille')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="list-bouteille" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="label" title="${message(code: 'bouteille.label.label', default: 'Label')}" />
					
						<g:sortableColumn property="millesime" title="${message(code: 'bouteille.millesime.label', default: 'Millesime')}" />
					
						<g:sortableColumn property="photo" title="${message(code: 'bouteille.photo.label', default: 'Photo')}" />
					
						<th><g:message code="bouteille.cave.label" default="Cave" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${bouteilleInstanceList}" status="i" var="bouteilleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${bouteilleInstance.id}">${fieldValue(bean: bouteilleInstance, field: "label")}</g:link></td>
					
						<td>${fieldValue(bean: bouteilleInstance, field: "millesime")}</td>
					
						<td><img class="photo-mini" src='<g:createLink action="displayPhoto" id="${bouteilleInstance.id}"/>' /></td>
						<td>${fieldValue(bean: bouteilleInstance, field: "cave")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${bouteilleInstanceTotal}" />
			</div>
		</div>
		
		<fieldset class="buttons">
			<g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
		</fieldset>
		
	</body>
</html>
