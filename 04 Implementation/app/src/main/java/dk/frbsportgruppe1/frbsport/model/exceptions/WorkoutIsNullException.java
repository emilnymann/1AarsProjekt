package dk.frbsportgruppe1.frbsport.model.exceptions;

public class WorkoutIsNullException extends Throwable{
    public WorkoutIsNullException(String errorMessage) {
        super(errorMessage);
    }

}
