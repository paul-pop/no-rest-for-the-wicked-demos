import React, { Component } from 'react';

class Tweet extends Component {
    render() {
        return (
            <div>
                <div>
                    {this.props.tweet.date}
                    {this.props.tweet.body}
                    {this.props.author.name}
                    {this.props.author.screenName}
                    {this.props.author.profileImageURL}
                    {this.props.author.followers}
                </div>
            </div>
        )
    }
}

export default Tweet;