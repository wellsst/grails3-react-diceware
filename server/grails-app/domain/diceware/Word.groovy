package diceware

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import org.apache.commons.lang.WordUtils
import org.grails.gorm.graphql.entity.dsl.GraphQLMapping

class Word {

    // hmm cant get working under Grails 4...
    static graphql = GraphQLMapping.build {
        query('randomPhrases', Word) {
            /*argument('params', 'ParamsArgument') { //A custom argument
                accepts {
                    field('nrPhrases', Integer)
                    field('nrWords', Integer)
                }
            }*/
            argument('nrPhrases', Integer) {
                defaultValue 10
                nullable true
                description 'The number of phrases to generate'
            }
            argument('nrWords', Integer) {
                defaultValue 4
                nullable true
                description 'The number of words in each phrase to generate'
            }
            dataFetcher(new DataFetcher() {
                @Override
                Object get(DataFetchingEnvironment environment) {
                    List<String> phraseList = []
                    (1..environment.getArgument('nrPhrases')).each {
                        String phrase = ""
                        (1..environment.getArgument('nrWords')).each {
                            String rollKey = ""
                            (1..5).each { roll ->
                                rollKey += new Random().nextInt(6) + 1
                            }
                            Word word = Word.findByRollKey rollKey
                            phrase += WordUtils.capitalize(word.theWord)
                        }
                        phraseList << phrase
                    }
                    new Word(phraseList: phraseList)
                }
            })
        }
    }

    String rollKey
    String theWord

    List<String> phraseList = []

    static mapping = {
        theWord index: 'idx_theWord'
        theWord rollKey: 'idx_rollKey'
    }

    static constraints = {
        phraseList nullable: true
    }
}
