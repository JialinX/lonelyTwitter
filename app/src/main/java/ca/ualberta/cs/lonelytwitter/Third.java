package ca.ualberta.cs.lonelytwitter;
import java.util.Date;
/**
 * the non-abstract class that is child of "First"
 * in this class, it can change the giveString to promote difference mood
 */
public class Third extends First {
    public Third() {
        super();
    }
    public Third(Date date){
        super(date);
    }
    @Override
    public String giveString(){
        return "mood - 2";
    }
}
