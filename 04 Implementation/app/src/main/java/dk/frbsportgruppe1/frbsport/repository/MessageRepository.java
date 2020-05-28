package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public class MessageRepository implements Observer {

    private static final String TAG = "MessageRepository";

    private MessageIndex messageIndex;

    public MessageRepository(MessageIndex messageIndex) {
        this.messageIndex = messageIndex;
        messageIndex.addObserver(this);
    }

    public void populateMessageIndex() {
        // TODO: populate from db

        Patient patient = new Patient("Tom Jensen");
        Message message = new Message();
        try {
            message.setSender(patient);
            message.setText("Dette er en herre lang besked som kommer fra vores BeskedRepository. Den er godt nok statisk, men senere henter vi det bare fra Firebase.");
            message.setDateTime(LocalDateTime.now());

            messageIndex.addMessage(message);

        } catch (SenderIsNullException e) {
            Log.d(TAG, "populateMessageIndex: " + e.getMessage());
        } catch (MessageIsNullException e) {
            Log.d(TAG, "populateMessageIndex: " + e.getMessage());
        }

        Log.d(TAG, "populateMessageIndex: notify observers");
        messageIndex.notifyObservers(messageIndex);
    }

    @Override
    public void update(Observable o, Object arg) {
        messageIndex = (MessageIndex) arg;
    }
}
