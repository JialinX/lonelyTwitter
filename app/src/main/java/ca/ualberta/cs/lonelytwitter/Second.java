package ca.ualberta.cs.lonelytwitter;
import java.util.Date;

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
