import grails.util.Environment
import dojo.grails.Cave
import dojo.grails.Role
import dojo.grails.User
import dojo.grails.UserRole

class BootStrap {

	
    def init = { servletContext ->
		if (Environment.current == Environment.DEVELOPMENT) {
			def roleUser = new Role(authority: 'ROLE_USER').save()
			def user = new User(username: 'dojo', password: 'dojo', enabled: true).save()
			UserRole.create user, roleUser, true
			
			// Simplification du jeu de données
			Cave cave = new Cave(id: 1L, name: "Ma Cave perso", user: user).save(flush: true)
		}
    }
    def destroy = {
    }
}
