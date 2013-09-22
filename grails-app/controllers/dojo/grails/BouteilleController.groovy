package dojo.grails

import org.springframework.dao.DataIntegrityViolationException

class BouteilleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [bouteilleInstanceList: Bouteille.list(params), bouteilleInstanceTotal: Bouteille.count()]
    }

    def create() {
        [bouteilleInstance: new Bouteille(params)]
    }

    def save() {
        def bouteilleInstance = new Bouteille(params)
        if (!bouteilleInstance.save(flush: true)) {
            render(view: "create", model: [bouteilleInstance: bouteilleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), bouteilleInstance.id])
        redirect(action: "show", id: bouteilleInstance.id)
    }

    def show(Long id) {
        def bouteilleInstance = Bouteille.get(id)
        if (!bouteilleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), id])
            redirect(action: "list")
            return
        }

        [bouteilleInstance: bouteilleInstance]
    }

    def edit(Long id) {
        def bouteilleInstance = Bouteille.get(id)
        if (!bouteilleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), id])
            redirect(action: "list")
            return
        }

        [bouteilleInstance: bouteilleInstance]
    }

    def update(Long id, Long version) {
        def bouteilleInstance = Bouteille.get(id)
        if (!bouteilleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (bouteilleInstance.version > version) {
                bouteilleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'bouteille.label', default: 'Bouteille')] as Object[],
                          "Another user has updated this Bouteille while you were editing")
                render(view: "edit", model: [bouteilleInstance: bouteilleInstance])
                return
            }
        }

        bouteilleInstance.properties = params

        if (!bouteilleInstance.save(flush: true)) {
            render(view: "edit", model: [bouteilleInstance: bouteilleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), bouteilleInstance.id])
        redirect(action: "show", id: bouteilleInstance.id)
    }

    def delete(Long id) {
        def bouteilleInstance = Bouteille.get(id)
        if (!bouteilleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), id])
            redirect(action: "list")
            return
        }

        try {
            bouteilleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'bouteille.label', default: 'Bouteille'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def displayPhoto() {
		if (!params.id) {
			response.sendError(404)
			return
		}

		def bouteille = Bouteille.get(params.id)
		if (bouteille.photo) {
			byte[] image = bouteille.photo
			response.setHeader('Cache-Control', 'no-cache')
			response.outputStream << image
			response.outputStream.flush()
		}
	}
}
