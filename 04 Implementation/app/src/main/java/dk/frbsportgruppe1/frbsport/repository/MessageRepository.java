package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private MessageIndex messageIndex;

    public MessageRepository(MessageIndex messageIndex) {
        db = FirebaseFirestore.getInstance();
        this.messageIndex = messageIndex;
        messageIndex.addObserver(this);
    }

    public void populateMessageIndex() {
        db.collection("users").whereEqualTo("email", messageIndex.getPatient().getEmail()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    String patientUid = task.getResult().getDocuments().get(0).getId();
                    db.collection("messages").whereEqualTo("patient", "users/" + patientUid).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                ArrayList<Message> messages = new ArrayList<>();
                                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                    DocumentSnapshot senderRef = documentSnapshot.getDocumentReference("sender").get().getResult();
                                    User sender = new User(senderRef.getString("name"), senderRef.getString("email"));
                                    Message message = new Message(documentSnapshot.getString("text"), sender);
                                    messages.add(message);
                                }
//                                messageIndex.setMessage(messages);
                                Log.d(TAG, "onComplete: " + messages);
                            } else {
                                Log.d(TAG, "onComplete: get failed with " + task.getException());
                            }
                        }
                    });
                } else {
                    Log.d(TAG, "onComplete: get failed with " + task.getException());
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
//        if (messageIndex.hashCode() != ((MessageIndex) arg).hashCode()) {
//            // TODO: send new messages to database.
//        }
    }
}
