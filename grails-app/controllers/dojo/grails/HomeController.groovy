package dojo.grails

class HomeController {

	CaveVinService caveVinService
	
    def index() { 
		Cave cave = Cave.get(1L)
		[cave: cave]
	}
	
	def bouteillesRecentes() {
		[bouteilles: caveVinService.findBouteillesRecentesByCriteria()]
	}
}