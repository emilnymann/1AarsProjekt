package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class Patient extends User implements PatientInterface{
    private Practicioner practicioner;
    private MessageIndex messageIndex=new MessageIndex(this);

    public Patient(String name,Practicioner practicioner) throws PatientIsNullException{
        super(name);
        this.practicioner=practicioner;
    }

    public void setPracticioner(Practicioner practicioner) {
        this.practicioner=practicioner;
    }

    public Practicioner getPracticioner(){
        return practicioner;
    }

    public void addMessage(Message msg) throws MessageIsNullException{
        messageIndex.addMessage(msg);
    }

    public ArrayList<Message> getMessages(){
        return messageIndex.getMessages();
    }
}