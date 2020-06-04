package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.InvalidMessageException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageTooLongException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public class MessageIndexImpl extends Observable implements MessageIndex {


    private ArrayList<Message> messages = new ArrayList<>();
    private Patient patient;

    /**
            * Beskedhistorikken er bundet til en patient, denne patient sættes i constructoren.
            * @param patient er den der binder beskedhistorikken mellem patient og behandler
     */
    public MessageIndexImpl(Patient patient) throws PatientIsNullException {
        if (patient == null) {
            throw new PatientIsNullException("Beskeden kunne ikke sendes");
        } else {
            this.patient = patient;
        }
    }

    /**
     * Denne metode tager imod en message og tilføjer den message til arratylisten beskeder
     * @param message
     */
    @Override
    public void addMessage(Message message) throws MessageIsNullException {
        if (message == null) {
            throw new MessageIsNullException("Beskeden kan ikke tilføjes, da der ikke er nogen besked");
        } else {
            messages.add(message);
            setChanged();
            notifyObservers(this);
        }
    }

    @Override
    public void sendMessage(String messageText, User sender) throws MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        if (messageText.length() > 255) {
            throw new MessageTooLongException("Du kan ikke lave en besked på mere end 255 tegn");
        } else if (messageText.isEmpty()) {
            throw new MessageIsNullException("Beskeden kan ikke sendes");
        } else if (messageText.matches("^\\s*$")){
            throw new InvalidMessageException("Beskeden kan ikke sendes");
        } else if (sender == null) {
            throw new SenderIsNullException("Beskeden kan ikke sendes uden afsender");
        } else {
            Message messageImpl = new MessageImpl(messageText, sender, LocalDateTime.now());
            this.addMessage(messageImpl);
        }
    }

    /**
     * angiv en ArrayList af Message til en messageIndex
     * @param messages en liste af Message
     */
    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        Collections.sort(this.messages, new SortMessages());
        setChanged();
        notifyObservers(this);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

}
