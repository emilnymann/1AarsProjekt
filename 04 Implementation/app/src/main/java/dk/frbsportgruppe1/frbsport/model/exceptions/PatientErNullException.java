package dk.frbsportgruppe1.frbsport.model.exceptions;

public class PatientErNullException extends Exception{
    public PatientErNullException(String fejlBesked){
        super(fejlBesked);
    }
}