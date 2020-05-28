package dk.frbsportgruppe1.frbsport.model.exceptions;

/**
 * Bruges til give fejl, hvis behandler ikke er sat.
 * Udvider Exception for test kan gribe fejlen.
 */
public class PracticionerIsNullException extends Exception{
    public PracticionerIsNullException(String errorMessage){
        super(errorMessage);
    }
}
