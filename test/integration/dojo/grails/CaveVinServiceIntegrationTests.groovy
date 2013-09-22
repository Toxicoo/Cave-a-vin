package dojo.grails

import static org.junit.Assert.*
import org.junit.*

class CaveVinServiceIntegrationTests {

	// Injection des services
	def springSecurityService
	CaveVinService caveVinService
	
	// Donnees de test attendues
	static final expectedList =  ['bouteille6', 'bouteille5', 'bouteille4', 'bouteille3', 'bouteille2']
	static final expectedNumber = 5
	
    @Before
    void setUp() {
        
		def user = new User(username: 'test', password: 'test').save(flush: true)

		// Force l'authentification du user 'test'
		springSecurityService.reauthenticate 'test'
		
		def cave = new Cave(name: "Ma cave", user: user)
		int index = 1
		def bouteille
		
		6.times {
			bouteille = new Bouteille(label: 'bouteille' + index, millesime: 1950)
			cave.addToBouteilles(bouteille)
			// On force la sauvegarde à chaque iteration pour garantir l'ordre de creation pour notre test
			cave.save(flush: true)
			index++
		}
		
    }

    @Test
    void "Recherche des bouteilles recentes par dynamic finders"() {
        def bouteilles = caveVinService.findBouteillesRecentesByDynamicFinder()
		assertEquals bouteilles.size, expectedNumber
		assertEquals bouteilles*.label, expectedList
    }
	
	@Test
	void "Recherche des bouteilles recentes par HQL"() {
		def bouteilles = caveVinService.findBouteillesRecentesByHQL()
		assertEquals bouteilles.size, expectedNumber
		assertEquals bouteilles*.label, expectedList
	}
	
	@Test
	void "Recherche des bouteilles recentes par Criteria"() {
		def bouteilles = caveVinService.findBouteillesRecentesByCriteria()
		assertEquals bouteilles.size, expectedNumber
		assertEquals bouteilles*.label, expectedList
	}
	
	@After
	void tearDown() {
		// Tear down logic here
	}
}
