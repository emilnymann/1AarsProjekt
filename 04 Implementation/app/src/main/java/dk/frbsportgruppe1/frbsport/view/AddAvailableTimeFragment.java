package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import org.apache.commons.lang3.StringUtils;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import dk.frbsportgruppe1.frbsport.R;

public class AddAvailableTimeFragment extends Fragment {
    AutoCompleteTextView autoCompleteTextView;
    Button button;


    public AddAvailableTimeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_available_time, container, false);
        button = rootView.findViewById(R.id.addAvailableTimeAcceptButton);
        ArrayList<String> daysOfWeekList = new ArrayList<>();
        
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            daysOfWeekList.add(StringUtils.capitalize(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, daysOfWeekList);

        autoCompleteTextView = rootView.findViewById(R.id.addAvailableTimeAutoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        
        return rootView;
    }
}