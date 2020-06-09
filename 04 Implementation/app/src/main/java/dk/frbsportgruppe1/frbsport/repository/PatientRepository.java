package dk.frbsportgruppe1.frbsport.repository;

import dk.frbsportgruppe1.frbsport.model.PatientIndex;

public interface PatientRepository {
    void populatePatientIndex(PatientIndex patientIndex);
}
