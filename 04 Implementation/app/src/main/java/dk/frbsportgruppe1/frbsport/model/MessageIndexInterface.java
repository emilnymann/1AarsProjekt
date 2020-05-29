package dk.frbsportgruppe1.frbsport.model;

import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.InvalidMessageException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageTooLongException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public interface MessageIndexInterface {

    void showMessageHistory();
    void addMessage(Message message) throws MessageIsNullException;

    void sendMessage(String message, User sender) throws MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException;
}
