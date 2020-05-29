package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

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
            throw new MessageIsNullException("Beskeden kan ikke sendes");
        } else {
            messages.add(message);
            setChanged();
            notifyObservers(this);
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
