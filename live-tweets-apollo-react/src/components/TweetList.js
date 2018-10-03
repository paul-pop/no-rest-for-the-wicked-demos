import React, { Component } from 'react';
import Tweet from './Tweet';
import { Subscription } from 'react-apollo';
import gql from 'graphql-tag';

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
                profileImageURL
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
                {({ data, loading, error }) => {
                    if (loading && this.state.tweets.length === 0) {
                        return <div>Waiting for tweets...</div>
                    }
                    if (error) {
                        return <div>Error :(</div>
                    }

                    // Only execute when done loading
                    if (!loading) {
                        this.state.tweets.push(data.tweets);
                        localStorage.setItem('tweets', JSON.stringify(this.state.tweets));
                    }
                    return (
                        <div>{this.state.tweets.map(tweet => (
                            <Tweet
                                key={tweet.id}
                                tweet={tweet}
                                author={tweet.author}
                            />
                        ))}</div>
                    )
                }}
            </Subscription>
        )
    }
}

export default TweetList;