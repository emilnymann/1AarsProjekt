package dk.frbsportgruppe1.frbsport.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;
import com.google.api.LogDescriptor;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.PatientImpl;
import dk.frbsportgruppe1.frbsport.model.PatientIndexImpl;
import dk.frbsportgruppe1.frbsport.model.Practitioner;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.repository.PatientRepository;
import dk.frbsportgruppe1.frbsport.repository.PatientRepositoryImpl;
import dk.frbsportgruppe1.frbsport.viewmodel.PatientIndexViewModel;

public class PatientIndexFragment extends Fragment implements Observer, PatientIndexAdapter.ItemClickListener {
    private static final String TAG = "PatientIndexView";

    private PatientIndexViewModel viewModel;
    private ArrayList<Patient> patients = new ArrayList<>();
    private PatientIndexAdapter patientIndexAdapter;

    RecyclerView recyclerViewPatients;

    public PatientIndexFragment() {}

    /**
     * Denne metode kaldes når dette fragment bliver lavet.
     * @param inflater man inflater den activity der skal vises med .inflate.
     * @param container er af typen ViewGroup, som er et view der indeholder andre views.
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_patient_index, container, false);
        recyclerViewPatients = rootView.findViewById(R.id.patientIndexRecyclerView);

        try {
            final Practitioner practitioner = (Practitioner) SessionManager.getInstance().getCurrentUser();
            final PatientIndexImpl patientIndex = new PatientIndexImpl(practitioner);

            viewModel = new PatientIndexViewModel(patientIndex);
            viewModel.addObserver(this);
            final PatientRepositoryImpl patientRepository = new PatientRepositoryImpl();
            patientRepository.populatePatientIndex(patientIndex);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    /**
     * update bliver kaldt når der ændres i klasserne der implementere Observable og kalder setChanged() og notifyObservers()
     * @param o
     * @param arg
     */

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Patient> patients = viewModel.getPatients();
        recyclerViewPatients.setLayoutManager(new LinearLayoutManager(getContext()));
        patientIndexAdapter = new PatientIndexAdapter(this.getContext(), patients);
        patientIndexAdapter.addItemClickListener(this);
        recyclerViewPatients.setAdapter(patientIndexAdapter);

    }

    /**
     * Denne metode implementeres fra Patientindexadapteren og håndterer hvad der sker når man trykker på knappen i PatientIndexAdapteren.
     * getParentFragmentManager tager context fra hele activitien og binder sig til denne, det gør at vi kan bruge tilbage knappen i bunden af appen for at komme til PatientIndex
     * @param position tager imod en position fra det objekt der er trykket på i recyclerview
     */
    @Override
    public void onItemClick(int position) {
        Patient patient = patientIndexAdapter.getPatients().get(position);
        getParentFragmentManager().beginTransaction().replace(R.id.patientIndexConstraintLayout, new MessageIndexFragment(patient)).addToBackStack("patientIndex").commit();
    }
}