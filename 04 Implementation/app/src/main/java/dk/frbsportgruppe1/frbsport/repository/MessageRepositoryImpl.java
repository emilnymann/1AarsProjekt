package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageImpl;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
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
        final User[] sender = new User[1];

        db.collection("messages").whereEqualTo("patient", patientId).orderBy("datetime", Query.Direction.ASCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    final ArrayList<Message> messages = new ArrayList<>();
                    for (final QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        // TODO: refactor for at nedbringe antal af firestore kald
                        db.collection("users").document(documentSnapshot.getString("sender")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                                    sender[0] = new UserImpl(task.getResult().getId(), task.getResult().getString("name"), task.getResult().getString("email"));
                                    MessageImpl message = new MessageImpl(documentSnapshot.getString("text"), sender[0], LocalDateTime.parse(documentSnapshot.getString("datetime"), formatter));
                                    message.setSent(true);
                                    messages.add(message);
                                    messageIndex.setMessages(messages);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    /**
     * Tilføj en besked til et messageIndex og forsøg derefter at sende den til firestore.
     * @param messageText beskedens tekstindhold.
     * @param sender brugeren der har afsendt beskeden.
     * @param messageIndex det messageIndex som beskeden skal tilføjes til.
     */
    public void sendMessage(String messageText, User sender, final MessageIndex messageIndex) {

        final int messageNumber = messageIndex.getMessages().size();
        MessageImpl message = new MessageImpl(messageText, sender, LocalDateTime.now());
        message.setSent(false);
        try {
            messageIndex.addMessage(message);
        } catch (MessageIsNullException e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<>();
        data.put("datetime", message.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        data.put("sender", sender.getId());
        data.put("patient", messageIndex.getPatient().getId());
        data.put("text", messageText);

        db.collection("messages").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                ArrayList<Message> messages = messageIndex.getMessages();
                messages.get(messageNumber).setSent(true);
                messageIndex.setMessages(messages);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: send message failed");
            }
        });
    }
}
