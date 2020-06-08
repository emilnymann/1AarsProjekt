package dk.frbsportgruppe1.frbsport.viewmodel;

import android.util.Log;

import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Calendar;

public class CalendarViewModel extends Observable implements Observer {
    private static final String TAG = "Calendar";

    private ArrayList<EventDay> eventDayList;

    public CalendarViewModel(Observable calendar){
        eventDayList = new ArrayList<>();
        calendar.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg){
        Calendar calendar=(Calendar)arg;

        eventDayList=new ArrayList<>();

        for(int i=0;i<calendar.getEvents().size();i++){
            if(calendar.getEvents().get(i).getType().contentEquals("Booking") && calendar.getFilter("Booking")){
                eventDayList.add(multipleEventsOnOneDayCheck(i,calendar));
            }else if(calendar.getEvents().get(i).getType().contentEquals("Workout") && calendar.getFilter("Workout")){
                eventDayList.add(multipleEventsOnOneDayCheck(i,calendar));
            }
        }
        setChanged();
        notifyObservers(eventDayList);
    }

    private EventDay multipleEventsOnOneDayCheck(int index,Calendar calendar){
        //Not the optimal way to show two types of event on the same day, but it just.
        int eventCount=0;
        for(int i=0;i<calendar.getEvents().size();i++){
            java.util.Calendar c1=calendar.getEvents().get(index).getEvent().getCalendar();
            java.util.Calendar c2=calendar.getEvents().get(i).getEvent().getCalendar();
            if(calendar.getEvents().get(i).getType().contentEquals("Booking") && calendar.getFilter("Booking")){
                if (c1.get(java.util.Calendar.YEAR) == c2.get(java.util.Calendar.YEAR)
                        && c1.get(java.util.Calendar.MONTH) == c2.get(java.util.Calendar.MONTH)
                        && c1.get(java.util.Calendar.DAY_OF_MONTH) == c2.get(java.util.Calendar.DAY_OF_MONTH)) {
                    eventCount++;
                    Log.d(TAG, index + ":" + eventCount);
                }
            }else if(calendar.getEvents().get(i).getType().contentEquals("Workout") && calendar.getFilter("Workout")){
                if (c1.get(java.util.Calendar.YEAR) == c2.get(java.util.Calendar.YEAR)
                        && c1.get(java.util.Calendar.MONTH) == c2.get(java.util.Calendar.MONTH)
                        && c1.get(java.util.Calendar.DAY_OF_MONTH) == c2.get(java.util.Calendar.DAY_OF_MONTH)) {
                    eventCount++;
                    Log.d(TAG, index + ":" + eventCount);
                }
            }
        }

        if(eventCount<2){
            return calendar.getEvents().get(index).getEvent();
        }else{
            return new EventDay(calendar.getEvents().get(index).getEvent().getCalendar(),R.drawable.calendar_primary_to_accent_gradient);
        }
    }

    public ArrayList<EventDay> getEventDayList(){
        return eventDayList;
    }
}
