package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.PracticionerIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

/**
* Interface til OC0102 PatientList
*/
public interface PatientListInterface {
    public void setPracticioner(Practicioner practicioner) throws PracticionerIsNullException;
    public void addPatient(Patient patient) throws PatientIsNullException;
    public ArrayList<Patient> getPatients();
    public int patientSize();
}