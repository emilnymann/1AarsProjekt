package dk.frbsportgruppe1.frbsport.model;

public interface Workoutplan {
    /*
    Dette interface bruger til at overholde clean architecture.
    Det indeholder kun getters og setter til brug i de ydre lag.
     */
    public String getTitle();
    public void setTitle(String title);
    public Patient getPatient();
    void setPatient(Patient patient);

}
