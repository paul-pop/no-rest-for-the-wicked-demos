import React, {Component} from 'react';
import Tweet from './Tweet';
import {Subscription} from 'react-apollo';
import gql from 'graphql-tag';
import {LinearProgress} from '@material-ui/core';

const TWEETS_SUBSCRIPTION = gql`
    subscription {
        tweets {
            id
            date
            body
            author {
                id
                name
                screenName
                avatarUrl
                followers
            }
        }
    }
`;

class TweetList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            tweets: JSON.parse(localStorage.getItem('tweets')) || []
        };
    }

    render() {
        return (
            <Subscription subscription={TWEETS_SUBSCRIPTION}>
                {({data, loading, error}) => {
                    if (loading && this.state.tweets.length === 0) {

                        return <div><LinearProgress />Waiting for the first tweet...</div>
                    }
                    if (error) {
                        return <div>Error :(</div>
                    }

                    // Only execute when done loading
                    if (!loading) {
                        this.state.tweets.unshift(data.tweets);
                        localStorage.setItem('tweets', JSON.stringify(this.state.tweets));
                    }
                    return (
                        <div>
                            <LinearProgress />Waiting for more tweets...
                            {this.state.tweets.map(tweet => <Tweet tweet={tweet}/>)}
                        </div>
                    )
                }}
            </Subscription>
        )
    }
}

export default TweetList;