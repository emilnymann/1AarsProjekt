package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class PatientIndexFragment extends Fragment implements Observer {
    private static final String TAG = "PatientIndexView";

    private PatientIndexViewModel viewModel;

    RecyclerView recyclerViewPatients;

    public PatientIndexFragment() {

    }

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

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Patient> patients = viewModel.getPatients();

        PatientIndexAdapter patientIndexAdapter = new PatientIndexAdapter(patients);
        recyclerViewPatients.setAdapter(patientIndexAdapter);
        recyclerViewPatients.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}