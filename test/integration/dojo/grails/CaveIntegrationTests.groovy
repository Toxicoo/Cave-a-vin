package dojo.grails

import static org.junit.Assert.*
import org.junit.*

class CaveIntegrationTests {

    @Before
    void setUp() {
        // Setup logic here
		new User(username: 'test', password: 'test').save(flush: true)
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void "Creation nominale d'une Cave avec liste de bouteilles"() {
		// On recupere le user de test
		def user = User.findByUsername('test')
		assertNotNull user
		
        def cave = new Cave(name: "Ma cave", user: user)
		def bouteille1 = new Bouteille(label: "Ma bouteille 1", millesime: 2012)
		def bouteille2 = new Bouteille(label: "Ma bouteille 2", millesime: 2012)
		cave.addToBouteilles(bouteille1);
		cave.addToBouteilles(bouteille2);
		cave.save(failOnError: true, flush: true)
		
		assertNotNull cave
		assertNotNull cave.name
		def found = Cave.findByName("Ma cave")
		assertEquals 'Ma cave', found.name
		assertEquals found.bouteilles*.millesime, [2012, 2012]
    }
}
