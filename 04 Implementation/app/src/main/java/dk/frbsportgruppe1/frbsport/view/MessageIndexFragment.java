package dk.frbsportgruppe1.frbsport.view;


import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.InvalidMessageException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageTooLongException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;
import dk.frbsportgruppe1.frbsport.repository.MessageRepository;
import dk.frbsportgruppe1.frbsport.viewmodel.MessageIndexViewModel;

public class MessageIndexFragment extends Fragment implements Observer {

    private static final String TAG = "MessageIndexView";

    private MessageIndexViewModel viewModel;

    RecyclerView recyclerViewMessages;
    EditText chatTextInput;

    public MessageIndexFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_messageindex, container, false);

        recyclerViewMessages = rootView.findViewById(R.id.messageRecyclerView);
        chatTextInput = rootView.findViewById(R.id.chatTextInputTextField);

        try {
            Practicioner practicioner = new Practicioner("Test Behandler", "PracUsername");
            final Patient patient = new Patient("Test Patient", "TestUsername", practicioner); // skal ikke laves her, skal hentes fra app context
            final MessageIndex messageIndex = new MessageIndex(patient);

            viewModel = new MessageIndexViewModel(messageIndex);
            MessageRepository messageRepository = new MessageRepository(messageIndex);
            viewModel.addObserver(this);
            messageRepository.populateMessageIndex();

            chatTextInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        try {
                            messageIndex.sendMessage(v.getText().toString(), patient);
                            v.setText("");
                        } catch (MessageTooLongException e) {
                            e.printStackTrace();
                        } catch (MessageIsNullException e) {
                            e.printStackTrace();
                        } catch (InvalidMessageException e) {
                            e.printStackTrace();
                        } catch (SenderIsNullException e) {
                            e.printStackTrace();
                        } catch (DateIsNullException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }

                    return false;
                }
            });


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

        recyclerViewMessages.scrollToPosition(messages.size() - 1);


    }
}
