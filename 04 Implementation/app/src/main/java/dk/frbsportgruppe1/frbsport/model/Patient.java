package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;

public interface Patient extends User {
    public void setPractitioner(Practitioner practitioner);
    public Practitioner getPractitioner();
    public String getId();
}