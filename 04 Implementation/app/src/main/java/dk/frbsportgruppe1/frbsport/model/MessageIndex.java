package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.InvalidMessageException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageTooLongException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public class MessageIndex extends Observable implements MessageIndexInterface {


    private ArrayList<Message> messages = new ArrayList<>();
    private Patient patient;

    /**
            * Beskedhistorikken er bundet til en patient, denne patient sættes i constructoren.
            * @param patient er den der binder beskedhistorikken mellem patient og behandler
     */
    public MessageIndex(Patient patient) throws PatientIsNullException {
        if (patient == null) {
            throw new PatientIsNullException("Beskeden kunne ikke sendes");
        } else {
            this.patient = patient;
        }


    }


    @Override
    public void showMessageHistory() {
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
        }
    }

    @Override
    public void sendMessage(String message, User sender) throws MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException {
        if (message.length() > 255) {
            throw new MessageTooLongException("Du kan ikke lave en besked på mere end 255 tegn");
        } else if (message.isEmpty()) {
            throw new MessageIsNullException("Beskeden kan ikke sendes");
        } else if (message.matches("^\\s*$")){
            throw new InvalidMessageException("Beskeden kan ikke sendes");
        } else if (sender == null) {
            throw new SenderIsNullException("Beskeden kan ikke sendes uden afsender");
        }
    }

    public void setMessage(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        messages.sort(new SortMessages());
        return messages;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

}
