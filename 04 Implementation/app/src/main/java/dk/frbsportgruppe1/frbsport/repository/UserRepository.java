package dk.frbsportgruppe1.frbsport.repository;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.User;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.view.PatientMainActivity;

public class UserRepository extends Observable {

    FirebaseAuth auth;
    FirebaseFirestore db;
    SessionManager sm;

    public UserRepository(Observer observer) {
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        sm = SessionManager.getInstance();
        this.addObserver(observer);
    }

    public void signInSession(FirebaseUser user) {

        db.collection("users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    final User[] user = new User[1];
                    final DocumentSnapshot userRef = task.getResult();

                    // if the user is a patient
                    if (task.getResult().getString("type").equals("patient")) {
                        db.collection("users").document(task.getResult().getString("practitioner")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {

                                    DocumentSnapshot pracRef = task.getResult();
                                    Practicioner practicioner = new Practicioner(pracRef.getId(), pracRef.getString("name"), pracRef.getString("email"));

                                    try {

                                        Patient patient = new Patient(userRef.getId(), userRef.getString("name"), userRef.getString("email"), practicioner);
                                        user[0] = patient;
                                        sm.setCurrentUser(patient);
                                        setChanged();
                                        notifyObservers();

                                    } catch (PatientIsNullException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });
                    } else {
                        Practicioner practicioner = new Practicioner(userRef.getId(), userRef.getString("name"), userRef.getString("email"));
                        user[0] = practicioner;
                        sm.setCurrentUser(practicioner);
                        setChanged();
                        notifyObservers();
                    }
                }
            }
        });
    }
}
