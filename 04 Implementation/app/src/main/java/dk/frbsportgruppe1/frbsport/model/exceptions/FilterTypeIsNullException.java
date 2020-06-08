package dk.frbsportgruppe1.frbsport.model.exceptions;

public class FilterTypeIsNullException extends Exception{
    public FilterTypeIsNullException(String errorMessage){
        super(errorMessage);
    }
}