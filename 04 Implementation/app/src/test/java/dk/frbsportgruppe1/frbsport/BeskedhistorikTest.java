package dk.frbsportgruppe1.frbsport;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class BeskedhistorikTest {

    @Test
    public void tc1_nyBeskedhistorik() {
        Patient patient = new Patient("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        assertEquals(0, beskedhistorik.beskeder.size());
        assertEquals("Tom Jensen", beskedhistorik.patient.getNavn());
    }

    @Test
    public void tc2_tilfoejBesked() {
        Patient patient = new Patient("Tom Jensen");

        Besked besked = new Besked();
        besked.tekst = "tekst";
        besked.afsender = patient;
        besked.datotid = LocalDateTime.parse("2020-05-22 15:10");

        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
        beskedhistorik.tilfoejBesked(besked, patient);

        assertEquals(1, beskedhistorik.beskeder.size());
        assertEquals("Tom Jensen", beskedhistorik.patient.getName());
    }

    @Test(expected = BeskedErNullException.class)
    public void tc3_nullBesked() {
        Patient patient = new Patient("Tom Jensen");
        Besked besked = null;

        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
        beskedhistorik.tilfoejBesked(besked, patient);
    }

    @Test(expected = AfsenderErNullException.class)
    public void tc4_nullAfsender() {
        Patient patient = null;
        Patient patient2 = new Patient("Tom Jensen");
        Besked besked = new Besked();
        besked.tekst = "tekst";
        besked.afsender = patient2;
        besked.datotid = LocalDateTime.parse("2020-05-22 15:10");

        Beskedhistorik beskedhistorik = new Beskedhistorik(patient2);
        beskedhistorik.tilfoejBesked(besked, patient);
    }

    @Test(expected = PatientErNullException.class)
    public void tc5_nullPatient() {
        Patient patient = null;
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
    }

    @Test
    public void tc6_sorteredeBeskeder() {
        Patient patient = new Patient("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        LocalDateTime datotid = LocalDateTime.parse("2020-05-22 15:10");
        Besked besked = new Besked();
        besked.tekst = "tekst";
        besked.afsender = patient;
        besked.datotid = datotid;
        beskedhistorik.tilfoejBesked(besked, patient);

        LocalDateTime datotid2 = LocalDateTime.parse("2020-05-22 14:00");
        Besked besked2 = new Besked();
        besked2.tekst = "tekst";
        besked2.afsender = patient;
        besked2.datotid = datotid2;
        beskedhistorik.tilfoejBesked(besked2, patient);

        assertEquals(datotid.toString(), beskedhistorik.beskeder[0].datotid.toString());
        assertEquals(datotid2.toString(), beskedhistorik.beskeder[1].datotid.toString());
    }

}
