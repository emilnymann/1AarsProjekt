package dk.frbsportgruppe1.frbsport.model.exceptions;

/**
 * Bruges til give fejl, hvis behandler ikke er sat.
 * Udvider Exception for test kan gribe fejlen.
 */
public class PractitionerIsNullException extends Exception{
    public PractitionerIsNullException(String errorMessage){
        super(errorMessage);
    }
}
