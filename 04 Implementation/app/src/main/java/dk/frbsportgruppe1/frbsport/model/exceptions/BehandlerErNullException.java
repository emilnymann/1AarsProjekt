package dk.frbsportgruppe1.frbsport.model.exceptions;

/**
 * Bruges til give fejl, hvis behandler ikke er sat.
 * Udvider Exception for test kan gribe fejlen.
 */
public class BehandlerErNullException extends Exception{
    public BehandlerErNullException(String fejlBesked){
        super(fejlBesked);
    }
}