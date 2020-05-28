package dk.frbsportgruppe1.frbsport.model.exceptions;

/**
* Bruges til give fejl, hvis patient ikke er sat.
* Udvider Exception for test kan gribe fejlen.
*/
public class PatientIsNullException extends Exception{
    public PatientIsNullException(String fejlBesked){
        super(fejlBesked);
    }
}

