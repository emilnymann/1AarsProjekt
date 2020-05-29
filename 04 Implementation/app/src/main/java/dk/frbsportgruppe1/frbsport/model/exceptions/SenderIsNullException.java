package dk.frbsportgruppe1.frbsport.model.exceptions;

public class SenderIsNullException extends Throwable {

    /**
     * Denne constructor bliver kaldt et andet sted når vores program skal vise
     * denne exception, det er hvis der ikke er nogen afsender knyttet til en besked.
     * @param errorMessage den tekst der vises når denne exception bliver kaldt.
     */
    public SenderIsNullException(String errorMessage) {
        super(errorMessage);
    }
}

