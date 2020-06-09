package dk.frbsportgruppe1.frbsport.model;

import com.applandeo.materialcalendarview.EventDay;

public interface CalendarEvent{
    public void addEvent(String title,String type,int startYear,int startMonth,int startDay);
    public void addEvent(String title,String type,int startYear,int startMonth,int startDay,int startHour,int startMinutes);
    public EventDay getEvent();
    public String getTitle();
    public String getType();
}
