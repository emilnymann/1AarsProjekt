package dk.frbsportgruppe1.frbsport.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Practitioner;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepository;
import dk.frbsportgruppe1.frbsport.repository.BookingRangeRepositoryImpl;
import dk.frbsportgruppe1.frbsport.view.SetAvailableHoursFragment;

public class AddUnavailableHoursFragment extends Fragment implements Observer {

    private static final String TAG = "AddUnavailableHoursFrag";

    TextInputEditText startDateInput;
    TextInputEditText startTimeInput;
    TextInputEditText endDateInput;
    TextInputEditText endTimeInput;
    Button confirmButton;

    public AddUnavailableHoursFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_unavailable_hours, container, false);

        startDateInput = view.findViewById(R.id.AddUnavailableHoursStartDateEditText);
        startTimeInput = view.findViewById(R.id.AddUnavailableHoursStartTimeEditText);
        endDateInput = view.findViewById(R.id.AddUnavailableHoursEndDateEditText);
        endTimeInput = view.findViewById(R.id.AddUnavailableHoursEndTimeEditText);
        confirmButton = view.findViewById(R.id.AddUnavailableHoursConfirmButton);

        BookingRangeRepository bookingRangeRepository = new BookingRangeRepositoryImpl(this);

        startDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        LocalDate localDate = LocalDate.of(year, month + 1, dayOfMonth);
                        startDateInput.setText(localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    }
                }, 2020, 1, 1);
                dialog.show();
            }
        });

        startTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        LocalTime localTime = LocalTime.of(hourOfDay, minute);
                        startTimeInput.setText(localTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                    }
                }, 12, 0, true);
                dialog.show();
            }
        });

        endDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        LocalDate localDate = LocalDate.of(year, month + 1, dayOfMonth);
                        endDateInput.setText(localDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    }
                }, 2020, 1, 1);
                dialog.show();
            }
        });

        endTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        LocalTime localTime = LocalTime.of(hourOfDay, minute);
                        endTimeInput.setText(localTime.format(DateTimeFormatter.ofPattern("HH:mm")));
                    }
                }, 12, 0, true);
                dialog.show();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDateTimeString = startDateInput.getText().toString() + " " + startTimeInput.getText().toString();
                LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                String endDateTimeString = endDateInput.getText().toString() + " " + endTimeInput.getText().toString();
                LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                Practitioner practitioner = (Practitioner) SessionManager.getInstance().getCurrentUser();

                bookingRangeRepository.createBookingRangeException(startDateTime, endDateTime, practitioner);
            }
        });

        return view;
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