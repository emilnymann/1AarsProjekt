package dk.frbsportgruppe1.frbsport.view;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

    TextView testMessage;
    TextView testMessageInfo;

    public MessageIndexFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_messageindex, container, false);

        ConstraintLayout constraintLayout = rootView.findViewById(R.id.outgoingMessageConstraintLayout);
        testMessage = constraintLayout.findViewById(R.id.outgoingMessageText);
        testMessageInfo = constraintLayout.findViewById(R.id.outgoingMessageInfo);

        try {
            Practicioner practicioner = new Practicioner("Test Behandler");
            Patient patient = new Patient("Test Patient", practicioner); // skal ikke laves her, skal hentes fra app context
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
        testMessage.setText(messages.get(0).getText());
        testMessageInfo.setText("d. " + messages.get(0).getDateTime().getDayOfMonth() + "/" + messages.get(0).getDateTime().getMonthValue() + " " + messages.get(0).getDateTime().getYear() + " kl. " + messages.get(0).getDateTime().getHour() + ":" + messages.get(0).getDateTime().getMinute());
    }
}
