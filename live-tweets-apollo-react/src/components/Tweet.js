import React from 'react';
import {Avatar, Card, CardContent, Tooltip, Typography} from '@material-ui/core';
import {withStyles} from '@material-ui/core/styles';
import distanceInWordsToNow from 'date-fns/distance_in_words_to_now';
import format from 'date-fns/format';

const styles = theme => ({
    link: {
        color: theme.palette.text.primary,
        textDecoration: 'none',
        '&:hover': {
            textDecoration: 'underline'
        }
    },
    card: {
        margin: '0.5rem'
    },
    container: {
        display: 'flex',
        flexDirection: 'row'
    },
    avatar: {
        marginRight: '0.75rem'
    },
    followers: {
        fontSize: '0.5rem',
        marginLeft: '-6px',
        fontWeight: 'bold'
    },
    fullName: {
        color: theme.palette.text.primary,
        fontWeight: 'bold',
        marginRight: '0.5rem'
    },
    screenName: {
        color: theme.palette.text.secondary,
        marginRight: '0.5rem'
    },
    separator: {
        color: theme.palette.text.secondary,
        marginRight: '0.5rem'
    },
    date: {
        color: theme.palette.text.secondary,
        size: '10em'
    },
    typography: {
        marginTop: '0.25rem'
    }
});

const Tweet = ({classes, tweet}) => (
    <Card className={classes.card}>
        <CardContent className={classes.container}>
            <div className={classes.avatar}>
                <Avatar className={classes.avatar} alt={tweet.author.name} src={tweet.author.avatarUrl}/>
                <span className={classes.followers}>{tweet.author.followers} followers</span>
            </div>
            <div>
                <span className={classes.fullName}>
                    <a className={classes.link} href={'https://twitter.com/' + tweet.author.screenName}>
                        {tweet.author.name}
                    </a>
                </span>
                <span className={classes.screenName}>@{tweet.author.screenName}</span>
                <span className={classes.separator}>·</span>
                <Tooltip title={format(tweet.date, 'HH:mm - D MMM YYYY')}>
                    <span className={classes.date}>{distanceInWordsToNow(tweet.date) + ' ago'}</span>
                </Tooltip>
                <Typography className={classes.typography} component="p">
                    {tweet.body}
                </Typography>
            </div>
        </CardContent>
    </Card>
);

export default withStyles(styles, {withTheme: true})(Tweet);