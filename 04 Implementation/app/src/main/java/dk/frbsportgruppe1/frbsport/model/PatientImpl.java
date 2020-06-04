package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class PatientImpl extends UserImpl implements Patient {
    private Practitioner practitioner;

    public PatientImpl(String id, String name, String email, Practitioner practitioner) throws PatientIsNullException{
        super(id, name, email);
        this.practitioner = practitioner;
    }

    public void setPractitioner(Practitioner practitioner) {
        this.practitioner = practitioner;
    }

    public Practitioner getPractitioner(){
        return practitioner;
    }
}