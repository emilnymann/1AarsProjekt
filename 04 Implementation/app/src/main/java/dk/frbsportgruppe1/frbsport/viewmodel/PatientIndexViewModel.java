package dk.frbsportgruppe1.frbsport.viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.PatientIndex;
import dk.frbsportgruppe1.frbsport.model.PatientIndexImpl;

public class PatientIndexViewModel extends Observable implements Observer {
    private static final String TAG = "PatientIndexViewModel";

    private ArrayList<Patient> patients;

    public PatientIndexViewModel (Observable patientIndex) {
        patients = new ArrayList<>();
        patientIndex.addObserver(this);
    }

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    //TODO: Sorter i forhold til nyeste besked
    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG, "update: ViewModel notified");
        PatientIndex patientIndex = (PatientIndexImpl) arg;
        patients = patientIndex.getPatients();
        Log.d(TAG, "update: viewmodel patients size: " + patients.size());
        setChanged();
        notifyObservers();

    }
}
