package grails3.react.diceware

import diceware.Word
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        log.error "Init diceware app in environment: ${Environment.current.name}"

        log.error servletContext.getRealPath("/")
        //log.error servletContext.getResourcePaths("/")

        // A line looks like: 11214	abyss
        if (Word.count == 0) { // populate DB once only kludge
            servletContext.getResourceAsStream('diceware.wordlist.asc.txt').text.eachLine { line ->
                List fields = line.tokenize()
                new Word(rollKey: fields[0], theWord: fields[1]).save()
            }
        }
    }
    def destroy = {
    }
}
