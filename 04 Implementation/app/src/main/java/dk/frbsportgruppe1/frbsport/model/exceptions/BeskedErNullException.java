package dk.frbsportgruppe1.frbsport.model.exceptions;

public class BeskedErNullException extends Exception {

    /**
     * Denne constructor bliver kaldt et andet sted når vores program skal vise
     * denne exception, det er hvis en besked ikke indeholder noget.
     * @param fejlbesked den tekst der vises når denne exception bliver kaldt.
     */
    public BeskedErNullException(String fejlbesked) {
        super(fejlbesked);
    }
}
