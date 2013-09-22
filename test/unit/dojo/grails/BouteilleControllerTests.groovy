package dojo.grails



import org.junit.*
import grails.test.mixin.*

@TestFor(BouteilleController)
@Mock([Bouteille])
class BouteilleControllerTests {

    def populateValidParams(params) {
        assert params != null
		User user = new User()
		User fakeUser = new User(username: 'fake', password: 'fake')
		Cave cave = new Cave(name: "Ma cave", user: fakeUser)
        params["label"] = 'Ma Bouteille'
		params["millesime"] = 2013
		params["cave"] = cave
    }

    void testIndex() {
        controller.index()
        assert "/bouteille/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.bouteilleInstanceList.size() == 0
        assert model.bouteilleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.bouteilleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.bouteilleInstance != null
        assert view == '/bouteille/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/bouteille/show/1'
        assert controller.flash.message != null
        assert Bouteille.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/bouteille/list'

        populateValidParams(params)
        def bouteille = new Bouteille(params)

        assert bouteille.save() != null

        params.id = bouteille.id

        def model = controller.show()

        assert model.bouteilleInstance == bouteille
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/bouteille/list'

        populateValidParams(params)
        def bouteille = new Bouteille(params)

        assert bouteille.save() != null

        params.id = bouteille.id

        def model = controller.edit()

        assert model.bouteilleInstance == bouteille
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/bouteille/list'

        response.reset()

        populateValidParams(params)
        def bouteille = new Bouteille(params)

        assert bouteille.save() != null

        // test invalid parameters in update
        params.id = bouteille.id
        //TODO: add invalid values to params object
		params.millesime = "XXXXXX"

        controller.update()

        assert view == "/bouteille/edit"
        assert model.bouteilleInstance != null

        bouteille.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/bouteille/show/$bouteille.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        bouteille.clearErrors()

        populateValidParams(params)
        params.id = bouteille.id
        params.version = -1
        controller.update()

        assert view == "/bouteille/edit"
        assert model.bouteilleInstance != null
        assert model.bouteilleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/bouteille/list'

        response.reset()

        populateValidParams(params)
        def bouteille = new Bouteille(params)

        assert bouteille.save() != null
        assert Bouteille.count() == 1

        params.id = bouteille.id

        controller.delete()

        assert Bouteille.count() == 0
        assert Bouteille.get(bouteille.id) == null
        assert response.redirectedUrl == '/bouteille/list'
    }
}
