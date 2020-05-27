package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.BehandlerErNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientErNullException;

public class Patientoversigt implements PatientoversigtInterface{
    private Behandler behandler;//Kun en behandler kan være forbundet til en patientoversigt.
    private ArrayList<Patient> patienter=new ArrayList<Patient>;//Flere patienter kan være forbundet til en patientoversigt.

    public void saetBehandler(Behandler b) throws BehandlerErNullException{
        if(b!=null){
            behandler=b;
        }else{
            throw new BehandlerErNullException("Behandler er null");
        }
    }

    public void tilfoej(Patient p) throws PatientErNullException {
        if(p!=null){
            patienter.add(p);
        }else{
            throw new PatientErNullException("Patient er null");
        }
    }

    public Patient hentPatient(int listId){
        return patienter.get(listId);
    }

    public int patientSize(){
        return patienter.size();
    }
}