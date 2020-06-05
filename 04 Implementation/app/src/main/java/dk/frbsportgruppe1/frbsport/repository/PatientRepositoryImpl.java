package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.PatientImpl;
import dk.frbsportgruppe1.frbsport.model.PatientIndex;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class PatientRepositoryImpl implements PatientRepository {
    private static final String TAG = "PatientRepository";

    private final FirebaseFirestore db;

    public PatientRepositoryImpl() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void populatePatientIndex(PatientIndex patientIndex) {
        String practitionerId = patientIndex.getPractitioner().getId();
        Log.d(TAG, "populatePatientIndex: patient id: " + practitionerId);

        db.collection("users")
                .whereEqualTo("type", "patient")
                .whereEqualTo("practitioner", practitionerId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            try {
                                PatientImpl patient = new PatientImpl(queryDocumentSnapshot.getId(), queryDocumentSnapshot.get("name").toString(), queryDocumentSnapshot.get("email").toString(), patientIndex.getPractitioner());
                                patientIndex.addPatient(patient);
                            } catch (PatientIsNullException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
