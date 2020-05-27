package dk.frbsportgruppe1.frbsport.model;

public class Patient extends Bruger implements PatientInterface {
    private String navn;

    public Patient(String navn){
        this.navn = navn;
    }

    public void saetNavn(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }
}
