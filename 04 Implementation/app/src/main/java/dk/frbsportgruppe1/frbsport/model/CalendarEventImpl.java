package dk.frbsportgruppe1.frbsport.model;

import com.applandeo.materialcalendarview.EventDay;

import java.util.Calendar;

import dk.frbsportgruppe1.frbsport.R;

public class CalendarEventImpl implements CalendarEvent{
    private String title;
    private String type;
    private EventDay eventDay;

    public CalendarEventImpl(String title,String type,int startYear,int startMonth,int startDay){
        addEvent(title,type,startYear,startMonth,startDay);
    }

    public CalendarEventImpl(String title,String type,int startYear,int startMonth,int startDay,int startHour,int startMinutes){
        addEvent(title,type,startYear,startMonth,startDay,startHour,startMinutes);
    }

    public void addEvent(String title,String type,int startYear,int startMonth,int startDay){
        this.title=title;
        this.type=type;
        java.util.Calendar c= java.util.Calendar.getInstance();
        c.set(startYear,startMonth,startDay);
        if(type.contentEquals("Workout")){
            eventDay=new EventDay(c,R.color.colorPrimary);
        }else{
            eventDay=new EventDay(c,R.color.colorAccent);
        }
    }

    public void addEvent(String title,String type,int startYear,int startMonth,int startDay,int startHour,int startMinutes){
        this.title=title;
        this.type=type;
        java.util.Calendar c=java.util.Calendar.getInstance();
        c.set(startYear,startMonth,startDay,startHour,startMinutes);
        if(type.contentEquals("Workout")){
            eventDay=new EventDay(c,R.color.colorPrimary);
        }else{
            eventDay=new EventDay(c,R.color.colorAccent);
        }
    }

    public EventDay getEvent(){
        return eventDay;
    }

    public String getTitle(){
        return title;
    }

    public String getType(){
        return type;
    }
}