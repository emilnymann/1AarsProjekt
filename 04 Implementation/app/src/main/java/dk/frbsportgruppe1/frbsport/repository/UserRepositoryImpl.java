package dk.frbsportgruppe1.frbsport.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.PatientImpl;
import dk.frbsportgruppe1.frbsport.model.PractitionerImpl;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.UserImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class UserRepositoryImpl extends Observable implements UserRepository {

    FirebaseAuth auth;
    FirebaseFirestore db;
    SessionManager sm;

    public UserRepositoryImpl(Observer observer) {
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

                    final UserImpl[] userImpl = new UserImpl[1];
                    final DocumentSnapshot userRef = task.getResult();

                    // if the user is a patient
                    if (task.getResult().getString("type").equals("patient")) {
                        db.collection("users").document(task.getResult().getString("practitioner")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {

                                    DocumentSnapshot pracRef = task.getResult();
                                    PractitionerImpl practicionerImpl = new PractitionerImpl(pracRef.getId(), pracRef.getString("name"), pracRef.getString("email"));

                                    try {

                                        PatientImpl patientImpl = new PatientImpl(userRef.getId(), userRef.getString("name"), userRef.getString("email"), practicionerImpl);
                                        userImpl[0] = patientImpl;
                                        sm.setCurrentUser(patientImpl);
                                        setChanged();
                                        notifyObservers();

                                    } catch (PatientIsNullException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        });
                    } else {
                        PractitionerImpl practicionerImpl = new PractitionerImpl(userRef.getId(), userRef.getString("name"), userRef.getString("email"));
                        userImpl[0] = practicionerImpl;
                        sm.setCurrentUser(practicionerImpl);
                        setChanged();
                        notifyObservers();
                    }
                }
            }
        });
    }
}
