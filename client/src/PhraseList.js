import React, {Component, useState} from 'react';
import {Query} from 'react-apollo'
import {withApollo} from 'react-apollo'
import gql from 'graphql-tag'
import Phrase from "./Phrase";

const RANDOM_LIST_QUERY = gql`
query randomPhrasesQuery($nrPhrases: Int!, $nrWords: Int!) {
  randomPhrases(nrPhrases: $nrPhrases, nrWords: $nrWords) {
     phraseList     
  }
}
`

/* Example result:
{
  "data": {
    "randomPhrases": {
      "phraseList": [
        "SledBhoyXyzAnRu",
        "RushAphidLatheEarthGrime",
        "WetGhettoTableAntonBo",
        "CrushBernetCoalDavyZeta",
        "CoaxCwSnoopSitusBook",
        "TestyOboeGustyPaperBid"
      ]
    }
  }
}
 */
class PhraseList extends Component {

    state = {
        phraseList: [],
        nrPhrases: '10',
        nrWords: '3'
    }

    render() {
        return (
            <div>
                <div class="form-group">
                    <label htmlFor="nrPhrases">Nr Phrases: {this.state.nrPhrases}</label>
                    <input type="range" name="nrPhrases" placeholder="Nr Phrases" min="1" max="100"
                           class="form-control-range"
                           value={this.state.nrPhrases}
                           width='5'
                           onChange={e => this.setState({nrPhrases: e.target.value})}/>

                    <small id="nrPhrasesHelpInline" className="form-text text-muted">
                        Min 1 / Max 100.
                    </small>

                    <label htmlFor="nrWords">Nr Words/Phrase: {this.state.nrWords}</label>
                    <input type="range" name="nrWords" placeholder="Words/Phrase" min="1" max="20"
                           class="form-control-range"
                           value={this.state.nrWords}
                           width='5'
                           onChange={e => this.setState({nrWords: e.target.value})}/>
                    <small id="nrWordsHelpInline" className="form-text text-muted">
                        Min 1 / Max 20.
                    </small>

                    <button type="submit" class="btn btn-primary" onClick={() => this.updatePhraseList()}>Generate
                    </button>

                </div>
                {
                    this.state.phraseList.map(phrase => <Phrase key={phrase} text={phrase}/>)
                }
            </div>
        )
    }

    updatePhraseList = async () => {
        const {nrPhrases, nrWords} = this.state
        const result = await this.props.client.query({
            query: RANDOM_LIST_QUERY,
            variables: {nrPhrases, nrWords},
        })
        const phraseList = result.data.randomPhrases.phraseList
        this.setState({phraseList})
    }
}

/*<Query query={RANDOM_LIST_QUERY}>
                {({ loading, error, data }) => {
                    if (loading) return <div>Fetching</div>
                    if (error) return <div>Error</div>

                    const phraseList = data.randomPhrases.phraseList

                    return (
                       [phraseList.map(phrase => <Phrase key={phrase} text={phrase}/>)]
                    )
                }}
            </Query>*/

export default withApollo(PhraseList)