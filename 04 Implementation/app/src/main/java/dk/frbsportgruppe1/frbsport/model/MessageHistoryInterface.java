package dk.frbsportgruppe1.frbsport.model;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;

public interface MessageHistoryInterface {

    void showMessageHistory();
    void addMessage(Message message) throws MessageIsNullException;
}
