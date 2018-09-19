package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

public abstract class First {
    private Date date;

    First(){
        this.date = new Date();
        // set default date
    }
    First(Date date){
        this.date = date;
    }
    public Date getDate(){
        return this.date;
        //get the date
    }
    public void setDate(Date newDate){
        this.date = newDate;
        //set the date
    }
    public abstract String giveString();
        //give the mood
}
