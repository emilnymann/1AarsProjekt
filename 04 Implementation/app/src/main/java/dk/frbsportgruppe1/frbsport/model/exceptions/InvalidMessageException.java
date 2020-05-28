package dk.frbsportgruppe1.frbsport.model.exceptions;

public class InvalidMessageException extends Exception {
    public InvalidMessageException(String errorMessage) {
        super(errorMessage);
    }
}
