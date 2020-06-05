package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.PractitionerIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

/**
* Interface til OC0102 PatientList
*/
public interface PatientIndex {
    public void setPractitioner(Practitioner practitioner) throws PractitionerIsNullException;
    public void addPatient(Patient patient) throws PatientIsNullException;
    public ArrayList<Patient> getPatients();
    Practitioner getPractitioner();
}