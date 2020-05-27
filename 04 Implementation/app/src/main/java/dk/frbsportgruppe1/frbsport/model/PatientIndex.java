package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.PracticionerIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class PatientIndex implements PatientIndexInterface {
    private Practicioner practicioner;
    private ArrayList<Patient> patients = new ArrayList<Patient>();

    /**
    * @param practicioner er den practicioner patientoversigten er forbundet til.
    * @throws PracticionerIsNullException bliver udløst hvis denne method bilver kaldt unden en practicioner.
    */
    public void setPracticioner(Practicioner practicioner) throws PracticionerIsNullException {
        if(practicioner!=null){
            this.practicioner = practicioner;
        }else{
            throw new PracticionerIsNullException("Behandler er null");
        }
    }

    @Override
    public ArrayList<Patient> getPatients() {
        return patients;
    }

    /**
    * @param patient er den patient som skal tilføjes til patientoversigten.
    * @throws PatientIsNullException bliver udløst hvis denne method bilver kaldt unden en patient.
    */
    public void addPatient(Patient patient) throws PatientIsNullException {
        if(patient!=null){
            patients.add(patient);
        }else{
            throw new PatientIsNullException("Patient er null");
        }
    }

    /**
     * @param listId nummeret på en specefik patient. Nummeret bliver angivet ud fra hvornår patienten blev tilføjet patientoversigten.
     * @return Returnere patient objektet på den ønskede patient.
     */
    public Patient getPatient(int listId){
        return patients.get(listId);
    }

    /**
    * @return Retunere antal af patients forbundet til patientoversigten.
    */
    public int patientSize(){
        return patients.size();
    }
}