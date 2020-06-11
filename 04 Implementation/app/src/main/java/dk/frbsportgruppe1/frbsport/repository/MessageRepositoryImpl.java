package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageImpl;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.MessageIndexImpl;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.User;
import dk.frbsportgruppe1.frbsport.model.UserImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;

public class MessageRepositoryImpl implements MessageRepository {

    private static final String TAG = "MessageRepository";
    public static final String MESSAGES = "messages";
    public static final String DATETIME = "datetime";
    public static final String PATIENT = "patient";
    public static final String USERS = "users";
    public static final String SENDER = "sender";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String TEXT = "text";

    private final FirebaseFirestore db;

    public MessageRepositoryImpl() {
        db = FirebaseFirestore.getInstance();
    }

    /**
     * Udfyld et messageIndex med beskeder fra Firestore.
     * @param messageIndex en reference til det messageIndex der skal udfyldes.
     */
    public void populateMessageIndex(final MessageIndex messageIndex) {
        String patientId = messageIndex.getPatient().getId();
        Log.d(TAG, "populateMessageIndex: patient id: " + patientId);

        // TODO: extract strings til konstanter. Refactor > extract > constant
        db.collection(MESSAGES)
                .orderBy(DATETIME, Query.Direction.ASCENDING)
                .whereEqualTo(PATIENT, patientId)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                        db.collection(USERS).document(documentChange.getDocument().getString(SENDER)).get().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                                DocumentSnapshot documentSnapshot = task.getResult();
                                User sender = new UserImpl(documentSnapshot.getId(), documentSnapshot.getString(NAME), documentSnapshot.getString(EMAIL));
                                DocumentSnapshot changeSnapshot = documentChange.getDocument();
                                Message message = new MessageImpl(changeSnapshot.getId(),
                                        changeSnapshot.getString(TEXT),
                                        sender,
                                        LocalDateTime.parse(changeSnapshot.getString(DATETIME), formatter));
                                Log.d(TAG, "populateMessageIndex: messagetext: " + message.getText());

                                switch (documentChange.getType()) {
                                    case ADDED:
                                        try {
                                            Log.d(TAG, "populateMessageIndex: event ADDED for: " + message.getId());
                                            Log.d(TAG, "populateMessageIndex: messageIndex size: " + messageIndex.getMessages().size());
                                            message.setSent(true);
                                            messageIndex.addMessage(message); // TODO: kalder notifyObservers for hver besked. Ikke optimalt?
                                        } catch (MessageIsNullException ex) {
                                            ex.printStackTrace();
                                        }
                                        break;
                                    case REMOVED:
                                        Log.d(TAG, "populateMessageIndex: event REMOVED for: " + message.getId() + " (hash " + message.hashCode() + ")");
                                        messageIndex.removeMessage(message);
                                        break;
                                }
                            }
                        });
                    }
                });
    }

    /**
     * Tilføj en besked til et messageIndex og forsøg derefter at sende den til firestore.
     * @param messageText beskedens tekstindhold.
     * @param sender brugeren der har afsendt beskeden.
     */
    public void sendMessage(String messageText, User sender, Patient patient) {

        Map<String, Object> data = new HashMap<>();
        data.put(DATETIME, LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        data.put(SENDER, sender.getId());
        data.put(PATIENT, patient.getId());
        data.put("text", messageText);

        db.collection(MESSAGES).add(data).addOnSuccessListener(documentReference -> {
            Log.d(TAG, "sendMessage: send message success");
        }).addOnFailureListener(e -> {
            Log.d(TAG, "sendMessage: send message fail");
        });
    }
}
