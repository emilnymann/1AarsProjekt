package dk.frbsportgruppe1.frbsport.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.BookingExceptionRange;
import dk.frbsportgruppe1.frbsport.model.BookingRange;
import dk.frbsportgruppe1.frbsport.model.BookingRangeIndexImpl;
import dk.frbsportgruppe1.frbsport.model.Practitioner;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepository;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepositoryImpl;
import dk.frbsportgruppe1.frbsport.viewmodel.BookingCalenderViewModel;


public class SetAvailableHoursFragment extends Fragment implements Observer {
    private static final String TAG = "SetAvailableHoursFragment";
    RecyclerView recyclerViewAvailableHours;
    RecyclerView recyclerViewUnavailableHours;
    FloatingActionButton button;
    BookingCalenderViewModel bookingCalenderViewModel;




    public SetAvailableHoursFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_available_time, container, false);
        recyclerViewAvailableHours = rootView.findViewById(R.id.ledigeTiderRecyclerView);
        recyclerViewUnavailableHours = rootView.findViewById(R.id.undtagelserRecyclerView);
        button = rootView.findViewById(R.id.addTimeButton);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Opret")
                .setItems(R.array.add_time_choice_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                getParentFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.setAvailableHoursConstraintLayout, new AddAvailableHoursFragment())
                                        .addToBackStack("setAvailableHoursConstraintLayout")
                                        .commit();
                                break;
                            case 1:
                                // TODO her skal den skifte til opret undtagelse
                                break;
                            default:
                                // TODO wat?
                                break;
                        }
                    }
                });
        AlertDialog dialog = builder.create();

        Practitioner practitioner = (Practitioner) SessionManager.getInstance().getCurrentUser();
        BookingRangeIndexImpl bookingRangeIndex = new BookingRangeIndexImpl(practitioner);
        bookingCalenderViewModel = new BookingCalenderViewModel(bookingRangeIndex);
        bookingCalenderViewModel.addObserver(this);

        BookingRangeRepository bookingRangeRepository = new BookingRangeRepositoryImpl();
        bookingRangeRepository.populateBookingRangeIndex(bookingRangeIndex);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });


        return rootView;
    }

    @Override
    public void update(Observable o, Object arg) {
        recyclerViewAvailableHours.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewUnavailableHours.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<BookingRange> bookingRanges = bookingCalenderViewModel.getBookingRanges();
        ArrayList<BookingExceptionRange> bookingExceptionRanges = bookingCalenderViewModel.getBookingExceptionRanges();
        SetAvailableHoursAdapter setAvailableHoursAdapter = new SetAvailableHoursAdapter(bookingRanges);
        SetUnavailableHoursAdapter setUnavailableHoursAdapter = new SetUnavailableHoursAdapter(bookingExceptionRanges);
        Log.d(TAG, "update: bookingRanges size: " + bookingRanges.size());

        recyclerViewAvailableHours.setAdapter(setAvailableHoursAdapter);
        recyclerViewUnavailableHours.setAdapter(setUnavailableHoursAdapter);


    }
}