package ca.ualberta.cs.lonelytwitter;
import java.util.Date;

/**
 * the non-abstract class that is child of "First"
 * in this class, it can change the giveString to promote difference mood
 */
public class Second extends First {
    public Second() {
        super();
    }
    public Second(Date date){
        super(date);
    }
    @Override
    public String giveString(){
        return "mood - 1";
    }
}
