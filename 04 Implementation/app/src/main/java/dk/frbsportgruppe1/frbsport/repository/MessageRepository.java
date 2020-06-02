package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.User;
import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public class MessageRepository implements Observer {

    private static final String TAG = "MessageRepository";

    private final FirebaseFirestore db;

    public MessageRepository(MessageIndex messageIndex) {
        db = FirebaseFirestore.getInstance();
        messageIndex.addObserver(this);
    }

    public void populateMessageIndex(final MessageIndex messageIndex) {
        String patientId = messageIndex.getPatient().getId();
        final User[] sender = new User[1];
        db.collection("messages").whereEqualTo("patient", patientId).orderBy("datetime", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    final ArrayList<Message> messages = new ArrayList<>();
                    for (final QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        db.collection("users").document(documentSnapshot.getString("sender")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {

                                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

                                    sender[0] = new User(task.getResult().getId(), task.getResult().getString("name"), task.getResult().getString("email"));
                                    Message message = new Message(documentSnapshot.getString("text"), sender[0], LocalDateTime.parse(documentSnapshot.getString("datetime"), formatter));
                                    Log.d(TAG, "onComplete: message get: " + message.getText() + ", " + DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(message.getDateTime()));
                                    messages.add(message);
                                    messageIndex.setMessage(messages);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
