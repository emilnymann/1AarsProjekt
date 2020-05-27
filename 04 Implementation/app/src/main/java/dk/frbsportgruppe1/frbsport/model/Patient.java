package dk.frbsportgruppe1.frbsport.model;

public class Patient extends User implements PatientInterface {
    private String name;

    public Patient(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
