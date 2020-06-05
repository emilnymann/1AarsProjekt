package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.exceptions.FilterTypeIsNullException;

public class CalendarImpl extends Observable implements Calendar{
    private List<CalendarEvent> events=new ArrayList<>();
    private boolean isBookingOn=true;
    private boolean isWorkoutOn=true;

    public void addEvent(String title,String type,int startYear,int startMonth,int startDay){
        events.add(new CalendarEventImpl(title,type,startYear,startMonth,startDay));
    }

    public void addEvent(String title,String type,int startYear,int startMonth,int startDay,int startHour,int startMinutes){
        events.add(new CalendarEventImpl(title,type,startYear,startMonth,startDay,startHour,startMinutes));
    }

    public void filter(String type) throws FilterTypeIsNullException{
        if(type==null){
            throw new FilterTypeIsNullException("Ingen filter angivet");
        }else{
            if(type.contentEquals("Booking")){
                if(isBookingOn){
                    isBookingOn=false;
                    setChanged();
                    notifyObservers();
                }else{
                    isBookingOn=true;
                    setChanged();
                    notifyObservers();
                }
            }else if(type.contentEquals("Workout")){
                if(isWorkoutOn){
                    isWorkoutOn=false;
                    setChanged();
                    notifyObservers();
                }else{
                    isWorkoutOn=true;
                    setChanged();
                    notifyObservers();
                }
            }
        }
    }

    public List<CalendarEvent> getEvents(){
        return events;
    }

    public boolean getFilter(String filter) {
        if(filter.contentEquals("Booking")){
            return isBookingOn;
        }else if(filter.contentEquals("Workout")){
            return isWorkoutOn;
        }
        return false;
    }
}