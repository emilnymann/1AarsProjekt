package dk.frbsportgruppe1.frbsport.viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndexImpl;

public class MessageIndexViewModel extends Observable implements Observer {

    private static final String TAG = "MessageIndexViewModel";

    private ArrayList<Message> messages;

    public MessageIndexViewModel(Observable messageIndex) {
        messages = new ArrayList<>();
        messageIndex.addObserver(this);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG, "update: viewmodel notified");
        MessageIndexImpl messageIndexImpl = (MessageIndexImpl) arg;
        messages = messageIndexImpl.getMessages();

        setChanged();
        notifyObservers();
    }
}
