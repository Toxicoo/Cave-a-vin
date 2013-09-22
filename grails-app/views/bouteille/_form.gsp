<%@ page import="dojo.grails.Bouteille" %>



<div class="fieldcontain ${hasErrors(bean: bouteilleInstance, field: 'label', 'error')} required">
	<label for="label">
		<g:message code="bouteille.label.label" default="Label" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="label" required="" value="${bouteilleInstance?.label}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: bouteilleInstance, field: 'millesime', 'error')} required">
	<label for="millesime">
		<g:message code="bouteille.millesime.label" default="Millesime" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="millesime" type="number" min="1900" max="2013" value="${bouteilleInstance.millesime}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: bouteilleInstance, field: 'photo', 'error')} ">
	<label for="photo">
		<g:message code="bouteille.photo.label" default="Photo" />
		
	</label>
	<input type="file" id="photo" name="photo" />
</div>

<div class="fieldcontain ${hasErrors(bean: bouteilleInstance, field: 'cave', 'error')} required">
	<label for="cave">
		<g:message code="bouteille.cave.label" default="Cave" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="cave" name="cave.id" from="${dojo.grails.Cave.list()}" optionKey="id" required="" value="${bouteilleInstance?.cave?.id}" class="many-to-one"/>
</div>

