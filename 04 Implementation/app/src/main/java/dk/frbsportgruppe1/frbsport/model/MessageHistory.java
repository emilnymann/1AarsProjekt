package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class MessageHistory extends Observable implements MessageHistoryInterface {


    private ArrayList<Message> beskeder = new ArrayList<>();
    private Patient patient;

    /**
            * Beskedhistorikken er bundet til en patient, denne patient sættes i constructoren.
            * @param patient er den der binder beskedhistorikken mellem patient og behandler
     */
    public MessageHistory(Patient patient) throws PatientIsNullException {
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
            beskeder.add(message);
        }
    }

    public void saetBeskeder(ArrayList<Message> beskeder) {
        this.beskeder = beskeder;
    }

    public ArrayList<Message> getMessages() {
        beskeder.sort(new SortMessages());
        return beskeder;
    }

    public void saetPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

}
