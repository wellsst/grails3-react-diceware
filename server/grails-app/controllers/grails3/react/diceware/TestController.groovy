package grails3.react.diceware


import grails.rest.*
import grails.converters.*

class TestController {
	static responseFormats = ['json', 'xml']
	
    def index() {
        render("Hi there")
    }
}
