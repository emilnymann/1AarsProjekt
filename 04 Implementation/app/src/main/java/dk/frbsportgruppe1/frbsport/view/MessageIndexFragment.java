package dk.frbsportgruppe1.frbsport.view;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.repository.MessageRepository;
import dk.frbsportgruppe1.frbsport.viewmodel.MessageIndexViewModel;

public class MessageIndexFragment extends Fragment implements Observer {

    private static final String TAG = "MessageIndexView";

    private MessageIndexViewModel viewModel;

    RecyclerView recyclerViewMessages;

    public MessageIndexFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_messageindex, container, false);

        recyclerViewMessages = rootView.findViewById(R.id.messageRecyclerView);

        try {
            Practicioner practicioner = new Practicioner("Test Behandler", "PracUsername");
            Patient patient = new Patient("Test Patient", "TestUsername", practicioner); // skal ikke laves her, skal hentes fra app context
            MessageIndex messageIndex = new MessageIndex(patient);

            viewModel = new MessageIndexViewModel(messageIndex);
            MessageRepository messageRepository = new MessageRepository(messageIndex);
            viewModel.addObserver(this);
            messageRepository.populateMessageIndex();


        } catch (PatientIsNullException e) {
            Log.d(TAG, "onCreateView: " + e.getMessage());
        }

        return rootView;

    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Message> messages = viewModel.getMessages();
        Log.d(TAG, "update: Got messages from viewmodel. " + messages.get(0).getText() + ", " + messages.get(0).getDateTime().toString());

        MessageIndexAdapter messageIndexAdapter = new MessageIndexAdapter(messages);
        recyclerViewMessages.setAdapter(messageIndexAdapter);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));


    }
}
