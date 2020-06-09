package dk.frbsportgruppe1.frbsport.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.MessageIndexImpl;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.PatientImpl;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.repository.MessageRepository;
import dk.frbsportgruppe1.frbsport.repository.MessageRepositoryImpl;
import dk.frbsportgruppe1.frbsport.viewmodel.MessageIndexViewModel;

public class MessageIndexFragment extends Fragment implements Observer{

    private static final String TAG = "MessageIndexView";

    private MessageIndexViewModel viewModel;

    RecyclerView recyclerViewMessages;
    EditText chatTextInput;
    TextInputLayout chatTextInputLayout;
    ProgressBar messageIndexProgressBar;
    Patient patient;

    public MessageIndexFragment() {

    }

    public MessageIndexFragment(Patient patient) {
        this.patient = patient;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_messageindex, container, false);
        messageIndexProgressBar = rootView.findViewById((R.id.messageIndexProgressBar));
        recyclerViewMessages = rootView.findViewById(R.id.messageRecyclerView);
        chatTextInput = rootView.findViewById(R.id.chatTextInputTextField);
        chatTextInputLayout = rootView.findViewById(R.id.chatTextInputLayout);

        try {
            if (patient == null) {
                patient = (Patient)SessionManager.getInstance().getCurrentUser();
            }
            final MessageIndexImpl messageIndex = new MessageIndexImpl(patient);

            viewModel = new MessageIndexViewModel(messageIndex);
            viewModel.addObserver(this);
            final MessageRepositoryImpl messageRepository = new MessageRepositoryImpl();
            messageRepository.populateMessageIndex(messageIndex);

            chatTextInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        messageRepository.sendMessage(v.getText().toString(), SessionManager.getInstance().getCurrentUser(), patient);
                        v.setText("");
                        return true;
                    }
                    return false;
                }
            });

            chatTextInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    messageRepository.sendMessage(chatTextInput.getText().toString(), SessionManager.getInstance().getCurrentUser(), patient);
                    chatTextInput.setText("");
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

        MessageIndexAdapter messageIndexAdapter = new MessageIndexAdapter(messages);
        recyclerViewMessages.setAdapter(messageIndexAdapter);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMessages.scrollToPosition(messages.size() - 1);
        messageIndexProgressBar.setVisibility(View.INVISIBLE);
        recyclerViewMessages.setVisibility(View.VISIBLE);


    }
}
