package dk.frbsportgruppe1.frbsport.model.exceptions;

public class MessageTooLongException extends Exception {
    /**
     * Denne constructor bliver kaldt et andet sted når vores program skal vise
     * denne exception, det er hvis en besked der sendes er for lang.
     * @param errorMessage
     */
    public MessageTooLongException(String errorMessage) {
        super(errorMessage);
    }
}
