package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;

import dk.frbsportgruppe1.frbsport.model.exceptions.BeskedErNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientErNullException;

public class Beskedhistorik extends Observable implements BeskedhistorikInterface {


    private ArrayList<Besked> beskeder = new ArrayList<>();
    private Patient patient;

    /**
            * Beskedhistorikken er bundet til en patient, denne patient sættes i constructoren.
            * @param patient er den der binder beskedhistorikken mellem patient og behandler
     */
    public Beskedhistorik(Patient patient) throws PatientErNullException {
        if (patient == null) {
            throw new PatientErNullException("Beskeden kunne ikke sendes");
        } else {
            this.patient = patient;
        }


    }


    @Override
    public void visBeskedhistorik() {
    }

    /**
     * Denne metode tager imod en besked og tilføjer den besked til arratylisten beskeder
     * @param besked
     */
    @Override
    public void tilfoejBesked(Besked besked) throws BeskedErNullException {
        if (besked == null) {
            throw new BeskedErNullException("Beskeden kan ikke sendes");
        } else {
            beskeder.add(besked);
        }
    }

    public void saetBeskeder(ArrayList<Besked> beskeder) {
        this.beskeder = beskeder;
    }

    public ArrayList<Besked> hentBeskeder() {
        beskeder.sort(new SorterBeskeder());
        return beskeder;
    }

    public void saetPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient hentPatient() {
        return patient;
    }

}
