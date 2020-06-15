package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndex;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndexImpl;
import dk.frbsportgruppe1.frbsport.model.Practitioner;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepository;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepositoryImpl;
import dk.frbsportgruppe1.frbsport.viewmodel.BookingCalenderViewModel;


public class SetAvailableHoursFragment extends Fragment implements Observer {
    RecyclerView recyclerViewAvailableHours;
    RecyclerView recyclerViewUnavailableHours;
    BookingCalenderViewModel bookingCalenderViewModel;


    public SetAvailableHoursFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_available_time, container, false);
        recyclerViewAvailableHours = rootView.findViewById(R.id.ledigeTiderRecyclerView);
        recyclerViewUnavailableHours = rootView.findViewById(R.id.undtagelserRecyclerView);


        Practitioner practitioner = (Practitioner) SessionManager.getInstance().getCurrentUser();
        BookingRangeIndexImpl bookingRangeIndex = new BookingRangeIndexImpl();
        bookingRangeIndex.setPractitioner(practitioner);
        bookingCalenderViewModel = new BookingCalenderViewModel(bookingRangeIndex);

        BookingRangeRepository bookingRangeRepository = new BookingRangeRepositoryImpl();
        bookingRangeRepository.populateBookingRangeIndex(bookingRangeIndex);

        return rootView;
    }

    @Override
    public void update(Observable o, Object arg) {
        SetAvailableHoursAdapter setAvailableHoursAdapter= new SetAvailableHoursAdapter();
        SetUnavailableHoursAdapter setUnavailableHoursAdapter = new SetUnavailableHoursAdapter();

        recyclerViewAvailableHours.setAdapter(setAvailableHoursAdapter);
        recyclerViewUnavailableHours.setAdapter(setUnavailableHoursAdapter);


    }
}