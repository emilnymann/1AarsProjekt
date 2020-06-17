package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dk.frbsportgruppe1.frbsport.R;


public class PractitionerBookingFragment extends Fragment {
    Button setAvailableHoursButton;

    public PractitionerBookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_practitioner_booking, container, false);

        setAvailableHoursButton = rootView.findViewById(R.id.setAvailableHoursButton);
        setAvailableHoursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().replace(R.id.practitionerBookingConstraintLayout, new SetAvailableHoursFragment()).addToBackStack("PractitionerBookingFragment").commit();
            }
        });

        return rootView;
    }


}