package dk.frbsportgruppe1.frbsport.model;

import java.util.Observable;

public class WorkoutplanImpl extends Observable implements Workoutplan {
    private String title;
    /*
    Constructor til at oprette en træningsplan
    @param Wo
     */
    public WorkoutplanImpl(String title){
        this.title = title;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
