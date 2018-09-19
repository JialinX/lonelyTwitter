package ca.ualberta.cs.lonelytwitter;
import java.util.Date;

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
