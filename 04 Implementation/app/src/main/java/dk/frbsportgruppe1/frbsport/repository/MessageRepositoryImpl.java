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
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.User;
import dk.frbsportgruppe1.frbsport.model.UserImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;

public class MessageRepositoryImpl implements MessageRepository {

    private static final String TAG = "MessageRepository";

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

        db.collection("messages")
                .orderBy("datetime", Query.Direction.ASCENDING)
                .whereEqualTo("patient", patientId)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    for (DocumentChange documentChange : queryDocumentSnapshots.getDocumentChanges()) {
                        db.collection("users").document(documentChange.getDocument().getString("sender")).get().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                                User sender = new UserImpl(task.getResult().getId(), task.getResult().getString("name"), task.getResult().getString("email"));
                                Message message = new MessageImpl(documentChange.getDocument().getId(),
                                        documentChange.getDocument().getString("text"),
                                        sender,
                                        LocalDateTime.parse(documentChange.getDocument().getString("datetime"), formatter));
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
    public void sendMessage(String messageText, User sender) {

        Map<String, Object> data = new HashMap<>();
        data.put("datetime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        data.put("sender", sender.getId());
        data.put("patient", SessionManager.getInstance().getCurrentUser().getId());
        data.put("text", messageText);

        db.collection("messages").add(data).addOnSuccessListener(documentReference -> {
            Log.d(TAG, "sendMessage: send message success");
        }).addOnFailureListener(e -> {
            Log.d(TAG, "sendMessage: send message fail");
        });
    }
}
