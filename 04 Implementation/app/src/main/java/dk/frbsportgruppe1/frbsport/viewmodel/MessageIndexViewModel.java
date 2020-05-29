package dk.frbsportgruppe1.frbsport.viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;

public class MessageIndexViewModel extends Observable implements Observer {

    private static final String TAG = "MessageIndexViewModel";

    private ArrayList<Message> messages;

    public MessageIndexViewModel(MessageIndex messageIndex) {
        messages = new ArrayList<>();
        messageIndex.addObserver(this);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG, "update: viewmodel notified");
        MessageIndex messageIndex = (MessageIndex) arg;
        messages = messageIndex.getMessages();

        setChanged();
        notifyObservers();
    }
}
