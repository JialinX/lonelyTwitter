package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;


public class TweetListTest extends ActivityInstrumentationTestCase2<LonelyTwitterActivity>{

    public TweetListTest(){
        super(LonelyTwitterActivity.class);
    }

    //JUnit test method for adding tweet into TweetList
    //@Test(expected = IllegalArgumentException.class)
    public void testAddTweet(){
        //assertTrue(Boolean.FALSE);
        TweetList tweetList = new TweetList();
        Tweet tweet1 = new NormalTweet("added tweet");
        //Tweet tweet2 = new NormalTweet("added tweet");
        tweetList.addTweet(tweet1);
        //tweetList.addTweet(tweet2);
        //assertEquals(1 ,tweetList.getCount());
        assertTrue(tweetList.hasTweet(tweet1));
    }

    // JUnit test method for checking whether a tweet is in the TweetList
    public void testHasTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("Hello");

        assertFalse(tweetList.hasTweet(tweet));
        tweetList.addTweet(tweet);
        assertTrue(tweetList.hasTweet(tweet));

        //assertTrue(Boolean.FALSE);
    }

    public void testGetTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("get tweet check");

        tweetList.addTweet(tweet);
        Tweet retrievedTweet = tweetList.getTweet(0);

        assertEquals(retrievedTweet.getMessage(), tweet.getMessage());
        assertEquals(retrievedTweet.getDate(), tweet.getDate());
    }

    public void testDeleteTweet(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("one for delete");

        tweetList.addTweet(tweet);
        tweetList.delete(tweet);

        assertFalse(tweetList.hasTweet(tweet));
    }

    public void testGetCount(){
        TweetList tweetList = new TweetList();
        Tweet tweet = new NormalTweet("get count");

        tweetList.addTweet(tweet);
        tweetList.getCount();

        assertEquals(1 ,tweetList.getCount());
    }
}
