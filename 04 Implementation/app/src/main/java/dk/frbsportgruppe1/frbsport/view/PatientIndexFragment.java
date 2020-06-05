package dk.frbsportgruppe1.frbsport.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.card.MaterialCardView;

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

public class PatientIndexFragment extends Fragment implements Observer, PatientIndexAdapter.OnItemClickListener {
    private static final String TAG = "PatientIndexView";

    private PatientIndexViewModel viewModel;
    private ArrayList<Patient> patients = new ArrayList<>();

    RecyclerView recyclerViewPatients;
    MaterialCardView materialCardView;

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
        recyclerViewPatients.setLayoutManager(new LinearLayoutManager(getContext()));
        PatientIndexAdapter patientIndexAdapter = new PatientIndexAdapter(patients, this);
        recyclerViewPatients.setAdapter(patientIndexAdapter);

    }

    @Override
    public void onItemClick(int position) {
        Patient patient = patients.get(position);
        System.out.println(patient.getEmail());
        Log.d(TAG, "onItemClick: clicked");
    }
}