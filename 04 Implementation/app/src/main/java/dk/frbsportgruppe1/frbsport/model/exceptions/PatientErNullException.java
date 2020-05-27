package dk.frbsportgruppe1.frbsport.model.exceptions;

/**
* Bruges til give fejl, hvis patient ikke er sat.
* Udvider Exception for test kan gribe fejlen.
*/
public class PatientErNullException extends Exception{
    public PatientErNullException(String fejlBesked){
        super(fejlBesked);
    }
}