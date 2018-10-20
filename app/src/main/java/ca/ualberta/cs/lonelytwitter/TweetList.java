package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

public class TweetList {

    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void addTweet(Tweet tweet){

        for (int i = 0 ; i < tweets.size(); i++){
            if (tweet.message.equals(tweets.get(i).message)){
                throw new IllegalArgumentException("Duplicate");
            }
        }
        tweets.add(tweet);
    }

    public boolean hasTweet(Tweet tweet){

        for (int i = 0 ; i < tweets.size(); i++){
            if (tweet.message.equals(tweets.get(i).message)){
                return true;
            }
        }
        return false;
        //return tweets.contains(tweet);
    }

    public Tweet getTweet(int index){
        //return new NormalTweet("some new tweet");
        return tweets.get(index);
    }

    public void delete (Tweet tweet){
        tweets.remove(tweet);
    }

    public int getCount(){
        return tweets.size();
    }

}
