package dk.frbsportgruppe1.frbsport.model;

import java.util.List;

import dk.frbsportgruppe1.frbsport.model.exceptions.FilterTypeIsNullException;

public interface Calendar{
    public void addEvent(String title,String type,int startYear,int startMonth,int startDay);
    public void addEvent(String title,String type,int startYear,int startMonth,int startDay,int startHour,int startMinutes);
    public void filter(String type) throws FilterTypeIsNullException;
    public List<CalendarEvent> getEvents();
    public boolean getFilter(String filter);
}