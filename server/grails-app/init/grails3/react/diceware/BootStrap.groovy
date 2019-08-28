package grails3.react.diceware

import diceware.Word
import grails.core.GrailsApplication
import grails.util.Environment
class BootStrap {
    GrailsApplication grailsApplication

    def init = { servletContext ->
        /*println "Init diceware app in environment: ${Environment.current.name}"

        println servletContext.getRealPath("/")
        log.error servletContext.getResourcePaths("/")
        println grailsApplication*/
        try {
// A line looks like: 11214	abyss
            if (Word.count == 0) { // populate DB once only kludge
                servletContext.getResourceAsStream('diceware.wordlist.asc.txt').text.eachLine { line ->
                    List fields = line.tokenize()
                    new Word(rollKey: fields[0], theWord: fields[1]).save()
                }
            }
        } catch (all) {
            all.printStackTrace()
        }
    }
    def destroy = {
    }
}
