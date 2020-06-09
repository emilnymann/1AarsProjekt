package dk.frbsportgruppe1.frbsport.repository;

import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.User;

public interface MessageRepository {

    void populateMessageIndex(MessageIndex messageIndex);
    void sendMessage(String messageText, User sender, Patient patient);
}
