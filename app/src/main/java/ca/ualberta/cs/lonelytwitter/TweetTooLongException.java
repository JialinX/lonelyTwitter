package ca.ualberta.cs.lonelytwitter;

/**
 * Error message if the input tweet's length os over 140 characters
 */
public class TweetTooLongException extends Exception{
    TweetTooLongException() {
        super("The message is too long! Please keep your tweets within 140 characters");
    }
}

