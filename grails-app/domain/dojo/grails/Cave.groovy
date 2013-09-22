package dojo.grails

class Cave {

	String name
	
	static belongsTo = [user: User]
	static hasMany = [bouteilles: Bouteille]
		
    static mapping = {
        bouteilles cascade: 'all-delete-orphan'
    }
	
    static constraints = {
		name blank: false, unique: true, maxSize: 15
    }
}
