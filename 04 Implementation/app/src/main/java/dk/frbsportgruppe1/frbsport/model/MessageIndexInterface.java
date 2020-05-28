package dk.frbsportgruppe1.frbsport.model;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;

public interface MessageIndexInterface {

    void showMessageHistory();
    void addMessage(Message message) throws MessageIsNullException;
}
