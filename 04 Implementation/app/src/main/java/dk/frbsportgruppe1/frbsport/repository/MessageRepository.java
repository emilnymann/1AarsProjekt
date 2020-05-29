package dk.frbsportgruppe1.frbsport.repository;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
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

        try {
            Practicioner practicioner = new Practicioner("Test behandler", "PracUsername");
            Patient patient = new Patient("Tom Jensen","TestUsername", practicioner);
            Message message = new Message();

            message.setSender(patient);
            message.setText("Dette er en herre lang besked som kommer fra vores BeskedRepository. Den er godt nok statisk, men senere henter vi det bare fra Firebase.");
            message.setDateTime(LocalDateTime.now());

            messageIndex.addMessage(message);

            Message message2 = new Message();

            message2.setSender(practicioner);
            message2.setText("Dette er en herre lang besked (fra behandleren) som kommer fra vores BeskedRepository. Den er godt nok statisk, men senere henter vi det bare fra Firebase.");
            message2.setDateTime(LocalDateTime.now());

            messageIndex.addMessage(message2);

        } catch (SenderIsNullException e) {
            Log.d(TAG, "populateMessageIndex: " + e.getMessage());
        } catch (MessageIsNullException e) {
            Log.d(TAG, "populateMessageIndex: " + e.getMessage());
        } catch (PatientIsNullException e) {
            Log.d(TAG, "populateMessageIndex: " + e.getMessage());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        messageIndex = (MessageIndex) arg;
    }
}
