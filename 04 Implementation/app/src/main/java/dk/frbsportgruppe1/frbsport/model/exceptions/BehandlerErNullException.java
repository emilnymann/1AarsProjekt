package dk.frbsportgruppe1.frbsport.model.exceptions;

public class BehandlerErNullException extends Exception{
    public BehandlerErNullException(String fejlBesked){
        super(fejlBesked);
    }
}