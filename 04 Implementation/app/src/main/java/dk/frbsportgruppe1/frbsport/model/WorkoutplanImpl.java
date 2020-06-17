package dk.frbsportgruppe1.frbsport.model;

import java.util.Observable;

public class WorkoutplanImpl extends Observable implements Workoutplan {
    /*
    I denne klasse implementerer vi interfacet for Workoutplan og angiver variabler,
    disse sætter så ind i de getter og setters som vi overrider.
     */
    private String title;
    private Patient patient;

    /*
    Constructor til at oprette en træningsplan
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

    @Override
    public Patient getPatient() {
        return patient;
    }

    @Override
    public void setPatient(Patient patient ) {
        this.patient = patient;
    }
}
