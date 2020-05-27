package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.BehandlerErNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientErNullException;

/**
* Interface til OC0102 Patientoversigt
*/
public interface PatientoversigtInterface{
    private Behandler behandler;//Kun en behandler kan være forbundet til en patientoversigt.
    private ArrayList<Patient> patienter;//Flere patienter kan være forbundet til en patientoversigt.

    public void saetBehandler(Behandler b) throws BehandlerErNullException;
    public void tilfoej(Patient p) throws PatientErNullException;
    public Patient hentPatient(int listId);
    public int patientSize();
}