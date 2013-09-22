package dojo.grails

class Bouteille {

	String label
	Integer millesime
	byte[] photo
	
	static belongsTo = [cave: Cave]
	
    static constraints = {
		label blank: false, unique: true
		millesime min: 1900, max: 2013
		photo nullable: true, maxSize: 65536
    }
}
