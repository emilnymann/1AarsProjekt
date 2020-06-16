package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.CalendarImpl;
import dk.frbsportgruppe1.frbsport.model.Workoutplan;
import dk.frbsportgruppe1.frbsport.model.WorkoutplanImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.FilterTypeIsNullException;
import dk.frbsportgruppe1.frbsport.repository.CalendarRepositoryImpl;
import dk.frbsportgruppe1.frbsport.viewmodel.CalendarViewModel;

public class CalendarFragment extends Fragment implements Observer{

    private static final String TAG = "fragment";
    private View fragmentView;
    private CalendarView cv;
    private CalendarViewModel calendarViewModel;
    private RecyclerView rw;
    private TextView textView;
    private WorkoutplanAdapter workoutplanAdapter;

    public CalendarFragment(){
        // Required empty public constructor
    }
    /*
    Opretter viewet til kalenderen og fylder det ud med behandlinger,
    træningspas og træningsprogrammer. Vi visualiserer behandlinger og træningspas med chips,
    som kan vælges til eller fra.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //TODO: fix implementering så vi ser kalenderen uden at trykke på chips
        fragmentView =inflater.inflate(R.layout.fragment_calendar, container, false);
        cv= fragmentView.findViewById(R.id.calendarView);
        Chip chipWorkout= fragmentView.findViewById(R.id.chip2);
        Chip chipBooking= fragmentView.findViewById(R.id.chip3);
        textView = fragmentView.findViewById(R.id.workoutplanHeadline);
        rw = fragmentView.findViewById(R.id.workoutplanRecyclerView);

        CalendarImpl calendar=new CalendarImpl();
        calendarViewModel=new CalendarViewModel(calendar);
        calendarViewModel.addObserver(this);
        CalendarRepositoryImpl calendarRepository=new CalendarRepositoryImpl();
        calendarRepository.populateCalendar(calendar);
        // TODO: opret forbindelse til API i stedet for dummy data
        ArrayList<Workoutplan> workoutplans = new ArrayList<>();
        workoutplans.add(new WorkoutplanImpl("Rygøvelser"));
        workoutplans.add(new WorkoutplanImpl("Hofter og knæ"));
        workoutplans.add(new WorkoutplanImpl("Knæ og tå"));
        Log.d(TAG, "onCreateView: " + workoutplans.size());
        workoutplanAdapter = new WorkoutplanAdapter(this.getContext(), workoutplans);
        rw.setAdapter(workoutplanAdapter);
        rw.setLayoutManager(new LinearLayoutManager(getContext()));

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

        new Handler().postDelayed(() -> {
            try {
                calendar.filter("Workout");
                calendar.filter("Workout");
            } catch (FilterTypeIsNullException e){
                e.printStackTrace();
            }
        },5);

        return fragmentView;
    }

    @Override
    public void update(Observable o,Object arg){
        cv.setEvents(calendarViewModel.getEventDayList());

    }
}