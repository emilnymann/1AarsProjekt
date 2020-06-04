package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.InvalidMessageException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageTooLongException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public interface MessageIndex {

    void addMessage(Message message) throws MessageIsNullException;
    void removeMessage(Message message);
    void setMessages(ArrayList<Message> messages);
    ArrayList<Message> getMessages();
    Patient getPatient();
}
