package ca.ualberta.cs.lonelytwitter;
/**
 * the basic class tweet that record each single tweet post
 */

import java.util.ArrayList;
import java.util.Date;

import ca.ualberta.cs.lonelytwitter.First;

public abstract class Tweet implements Tweetable {
    private Date date;
    private String message;
    private static final Integer MAX_CHARS = 140;

                ///add a way for each tweet to have a list of moods.
    private Second sec;
    private Third thi;
    ArrayList<First> List = new ArrayList<First>();
                ///

    Tweet() {
        this.date = new Date();
        this.message = "I am default message";
    }
    Tweet(String message) {
        this.date = new Date();
        this.message = message; // Tweet
        sec = new Second();
        String mood1 = sec.giveString();
        thi = new Third();
        String mood2 = thi.giveString();
    }

    public Date getDate() {return this.date;}
    public String getMessage() {return this. message;}
    public void setMessage(String message) throws TweetTooLongException {
        if (message.length() <= this.MAX_CHARS) {
            this.message = message;
        }else {
            throw new TweetTooLongException();
        }
    }
    public abstract Boolean isImportant();
}
