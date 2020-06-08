package dk.frbsportgruppe1.frbsport.viewmodel;

import android.util.Log;

import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Calendar;
import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.MessageIndexImpl;
import dk.frbsportgruppe1.frbsport.model.SortMessages;

public class CalendarViewModel extends Observable implements Observer {
    private static final String TAG = "MessageIndexViewModel";

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
                eventDayList.add(calendar.getEvents().get(i).getEvent());
            }else if(calendar.getEvents().get(i).getType().contentEquals("Workout") && calendar.getFilter("Workout")){
                eventDayList.add(calendar.getEvents().get(i).getEvent());
            }
        }
        setChanged();
        notifyObservers(eventDayList);
    }

    public ArrayList<EventDay> getEventDayList(){
        return eventDayList;
    }
}
