package dk.frbsportgruppe1.frbsport.model.exceptions;

public class PatientErNullException extends Throwable {

    /**
     * Denne constructor bliver kaldt et andet sted når vores program skal vise
     * denne exception, det er hvis der ikke er nogen patient knyttet til en beskedhistorik.
     * @param fejlbesked den tekst der vises når denne exception bliver kaldt.
     */
    public PatientErNullException(String fejlbesked) {
        super(fejlbesked);
    }
}
