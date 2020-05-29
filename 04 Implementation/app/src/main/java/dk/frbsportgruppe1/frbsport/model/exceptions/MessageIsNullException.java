package dk.frbsportgruppe1.frbsport.model.exceptions;

public class MessageIsNullException extends Exception {

    /**
     * Denne constructor bliver kaldt et andet sted når vores program skal vise
     * denne exception, det er hvis en besked ikke indeholder noget.
     * @param errorMessage den tekst der vises når denne exception bliver kaldt.
     */
    public MessageIsNullException(String errorMessage) {
        super(errorMessage);
    }
}

