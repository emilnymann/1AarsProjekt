package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.exceptions.PractitionerIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class PatientIndexImpl extends Observable implements PatientIndex {
    private Practitioner practitioner;
    private ArrayList<Patient> patients = new ArrayList<>();

    public PatientIndexImpl(Practitioner practitioner) {
        this.practitioner = practitioner;
    }

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

    @Override
    public Practitioner getPractitioner() {
        return practitioner;
    }

    /**
    * @param patient er den patient som skal tilføjes til patientoversigten.
    * @throws PatientIsNullException bliver udløst hvis denne method bilver kaldt unden en patient.
    */
    public void addPatient(Patient patient) throws PatientIsNullException {
        if(patient != null){
            patients.add(patient);
            setChanged();
            notifyObservers(this);
        }else{
            throw new PatientIsNullException("Patient er null");
        }
    }
}