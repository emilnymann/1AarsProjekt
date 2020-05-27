package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.BehandlerErNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientErNullException;

public class Patientoversigt implements PatientoversigtInterface{
    private Behandler behandler;
    private ArrayList<Patient> patienter=new ArrayList<Patient>;

    /**
    * @param b er den behandler patientoversigten er forbundet til.
    * @throws BehandlerErNullException bliver udløst hvis denne method bilver kaldt unden en behandler.
    */
    public void saetBehandler(Behandler b) throws BehandlerErNullException{
        if(b!=null){
            behandler=b;
        }else{
            throw new BehandlerErNullException("Behandler er null");
        }
    }

    /**
    * @param p er den patient som skal tilføjes til patientoversigten.
    * @throws PatientErNullException bliver udløst hvis denne method bilver kaldt unden en patient.
    */
    public void tilfoej(Patient p) throws PatientErNullException {
        if(p!=null){
            patienter.add(p);
        }else{
            throw new PatientErNullException("Patient er null");
        }
    }

    /**
     * @param listId nummeret på en specefik patient. Nummeret bliver angivet ud fra hvornår patienten blev tilføjet patientoversigten.
     * @return Returnere patient objektet på den ønskede patient.
     */
    public Patient hentPatient(int listId){
        return patienter.get(listId);
    }

    /**
    * @return Retunere antal af patienter forbundet til patientoversigten.
    */
    public int patientSize(){
        return patienter.size();
    }
}