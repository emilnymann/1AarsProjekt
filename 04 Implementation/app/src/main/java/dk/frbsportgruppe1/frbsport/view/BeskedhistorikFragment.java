package dk.frbsportgruppe1.frbsport.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dk.frbsportgruppe1.frbsport.R;

public class BeskedhistorikFragment extends Fragment {


    public BeskedhistorikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beskedhistorik, container, false);
    }

}
