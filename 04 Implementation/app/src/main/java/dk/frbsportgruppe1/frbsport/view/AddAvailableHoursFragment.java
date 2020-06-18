package dk.frbsportgruppe1.frbsport.view;

import android.app.TimePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.se.omapi.Session;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.commons.lang3.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Practitioner;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepository;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepositoryImpl;

public class AddAvailableHoursFragment extends Fragment implements Observer {
    private static final String TAG = "AddAvailableHoursFragment";
    TimePickerDialog startTimePickerDialog;
    TimePickerDialog endTimePickerDialog;
    TextInputLayout startTimeInputLayout;
    TextInputLayout endTimeInputLayout;
    TextInputEditText startTimeEditText;
    TextInputEditText endTimeEditText;
    Spinner weekdaySpinner;
    Button button;


    public AddAvailableHoursFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_available_time, container, false);

        startTimeInputLayout = rootView.findViewById(R.id.startTimeInputLayout);
        endTimeInputLayout = rootView.findViewById(R.id.endTimeInputLayout);
        startTimeEditText = rootView.findViewById(R.id.startTimeEditText);
        endTimeEditText = rootView.findViewById(R.id.endTimeEditText);
        weekdaySpinner = rootView.findViewById(R.id.weekdaySpinner);
        button = rootView.findViewById(R.id.addAvailableTimeAcceptButton);

        ArrayList<String> daysOfWeekList = new ArrayList<>();
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            daysOfWeekList.add(StringUtils.capitalize(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, daysOfWeekList);
        weekdaySpinner.setAdapter(adapter);

        BookingRangeRepository bookingRangeRepository = new BookingRangeRepositoryImpl(this);

        startTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTimeEditText.setText(LocalTime.of(hourOfDay, minute).format(DateTimeFormatter.ofPattern("HH:mm")));
                    }
                }, 10, 10, true);
                startTimePickerDialog.show();
            }
        });

        endTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endTimeEditText.setText(LocalTime.of(hourOfDay, minute).format(DateTimeFormatter.ofPattern("HH:mm")));
                    }
                }, 10, 10, true);
                startTimePickerDialog.show();
            }
        });

        // TODO: disable knap når alle felter ikke er udfyldt
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedDay = weekdaySpinner.getSelectedItemPosition() + 1;
                LocalTime startTime = LocalTime.parse(startTimeEditText.getText().toString(), DateTimeFormatter.ISO_LOCAL_TIME);
                LocalTime endTime = LocalTime.parse(endTimeEditText.getText().toString(), DateTimeFormatter.ISO_LOCAL_TIME);
                Practitioner practitioner = (Practitioner) SessionManager.getInstance().getCurrentUser();
                bookingRangeRepository.createBookingRange(selectedDay, startTime, endTime, practitioner);
            }
        });
        
        return rootView;
    }

    @Override
    public void update(Observable o, Object arg) {

        getParentFragmentManager().popBackStack("setAvailableHoursConstraintLayout", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.setAvailableHoursConstraintLayout, new SetAvailableHoursFragment())
                .commit();

    }
}