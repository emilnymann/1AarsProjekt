package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Calendar;
import dk.frbsportgruppe1.frbsport.model.CalendarImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.FilterTypeIsNullException;
import dk.frbsportgruppe1.frbsport.repository.CalendarRepositoryImpl;
import dk.frbsportgruppe1.frbsport.viewmodel.CalendarViewModel;

public class CalendarFragment extends Fragment implements Observer{

    private View view;
    private CalendarView cv;
    private CalendarViewModel calendarViewModel;

    public CalendarFragment(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view=inflater.inflate(R.layout.fragment_calendar, container, false);
        cv=view.findViewById(R.id.calendarView);
        Chip chipWorkout=view.findViewById(R.id.chip2);
        Chip chipBooking=view.findViewById(R.id.chip3);
        CalendarImpl calendar=new CalendarImpl();
        calendarViewModel=new CalendarViewModel(calendar);
        calendarViewModel.addObserver(this);
        CalendarRepositoryImpl calendarRepository=new CalendarRepositoryImpl();
        calendarRepository.populateCalendar(calendar);

        chipWorkout.setOnClickListener(view->{
            try{
                calendar.filter("Workout");
            }catch(FilterTypeIsNullException e){
                e.printStackTrace();
            }
        });

        chipBooking.setOnClickListener(view->{
            try{
                calendar.filter("Booking");
            }catch(FilterTypeIsNullException e){
                e.printStackTrace();
            }
        });

        List<EventDay> eventDayList=new ArrayList<>();

        for(int i=0;i<calendar.getEvents().size();i++){
            eventDayList.add(calendar.getEvents().get(i).getEvent());
        }
        cv.setEvents(eventDayList);

        return view;
    }

    @Override
    public void update(Observable o,Object arg){
        cv.setEvents(calendarViewModel.getEventDayList());
    }
}