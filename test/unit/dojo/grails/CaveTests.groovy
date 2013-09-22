package dojo.grails



import grails.test.mixin.*

import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Cave)
class CaveTests {

    void testCaveConstraints() {
       def cave = new Cave(name: "cave")

	   // Permet de mocker la methode validate
	   mockForConstraintsTests(Cave, [cave])
	   
	   // Test sans user attache a l'instance de cave
	   assertFalse cave.validate()
	   
	   // Test nullable
	   def testCave = new Cave()
	   assertFalse testCave.validate()
       assertEquals "nullable", testCave.errors["name"]

	   // Test blank
	   testCave = new Cave(name: "")
	   assertFalse testCave.validate()
	   assertEquals "blank", testCave.errors["name"]
	   
	   // Test maxSize
	   testCave = new Cave(name: "Mon nom de cave beaucoup trop long")
	   assertFalse testCave.validate()
	   assertEquals "maxSize", testCave.errors["name"]
	   
	   // Test unique
	   testCave = new Cave(name: "cave")
	   assertFalse testCave.validate()
	   assertEquals "unique", testCave.errors["name"]
	   
	   // Test ok
	   User fakeUser = new User(username: 'fake', password: 'fake')
	   testCave = new Cave(name: "Ma cave", user: fakeUser)
	   assertTrue testCave.validate()
	   assertEquals 'fake', testCave?.user?.username
    }
}
