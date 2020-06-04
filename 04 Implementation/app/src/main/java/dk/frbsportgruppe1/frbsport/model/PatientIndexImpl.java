package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.PractitionerIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class PatientIndexImpl implements PatientIndex {
    private Practitioner practitioner;
    private ArrayList<Patient> patients = new ArrayList<>();

    /**
    * @param practitioner er den practicioner patientoversigten er forbundet til.
    * @throws PractitionerIsNullException bliver udløst hvis denne method bilver kaldt unden en practicioner.
    */
    public void setPractitioner(Practitioner practitioner) throws PractitionerIsNullException {
        if(practitioner != null){
            this.practitioner = practitioner;
        }else{
            throw new PractitionerIsNullException("Behandler er null");
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
        if(patient != null){
            patients.add(patient);
        }else{
            throw new PatientIsNullException("Patient er null");
        }
    }
}