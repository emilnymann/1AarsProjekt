package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;

public interface PatientInterface{
    public void setPracticioner(Practicioner practicioner);
    public Practicioner getPracticioner();
    public void addMessage(Message msg) throws MessageIsNullException;
    public ArrayList<Message> getMessages();
}