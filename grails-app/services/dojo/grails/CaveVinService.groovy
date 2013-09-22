package dojo.grails

import grails.plugins.springsecurity.SpringSecurityService;

import org.springframework.transaction.annotation.Transactional;

class CaveVinService {

	static transactional = false
	
	// Injection du service Spring Security
	SpringSecurityService springSecurityService
	
	final static int LIMIT = 5
	
    def findBouteillesRecentesByDynamicFinder() {
		def current = springSecurityService.getCurrentUser()
		return Bouteille.findAllByCave(current.cave, [max: LIMIT, sort: "id", order: "desc"])
	}
	
	def findBouteillesRecentesByHQL() {
		def current = springSecurityService.getCurrentUser()
		return Bouteille.executeQuery("FROM Bouteille b where b.cave = :cave order by b.id desc",
                     [cave: current.cave, max: LIMIT])
	}
	
    def findBouteillesRecentesByCriteria() {
		def current = springSecurityService.getCurrentUser()
		def criteria = Bouteille.createCriteria()
		criteria.list {
			cave {
				eq('id', current.cave?.id)
			}
			maxResults LIMIT
			order 'id','desc'
		}
	}
}
