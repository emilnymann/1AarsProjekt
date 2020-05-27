package dk.frbsportgruppe1.frbsport;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.frbsportgruppe1.frbsport.model.Besked;
import dk.frbsportgruppe1.frbsport.model.Beskedhistorik;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.exceptions.AfsenderErNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.BeskedErNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientErNullException;

public class BeskedhistorikTest {

    @Test
    public void tc1_nyBeskedhistorik() throws PatientErNullException {
        Patient patient = new Patient("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        assertEquals(0, beskedhistorik.hentBeskeder().size());
        assertEquals("Tom Jensen", beskedhistorik.hentPatient().hentNavn());
    }

    @Test
    public void tc2_tilfoejBesked() throws BeskedErNullException, AfsenderErNullException, PatientErNullException {
        Patient patient = new Patient("Tom Jensen");

        Besked besked = new Besked();
        besked.saetTekst("tekst");
        besked.saetAfsender(patient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        besked.saetDatotid(LocalDateTime.parse("2020-05-22 15:10", formatter));

        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
        beskedhistorik.tilfoejBesked(besked);

        assertEquals(1, beskedhistorik.hentBeskeder().size());
        assertEquals("Tom Jensen", beskedhistorik.hentPatient().hentNavn());
    }

    @Test(expected = BeskedErNullException.class)
    public void tc3_nullBesked() throws BeskedErNullException, PatientErNullException {
        Patient patient = new Patient("Tom Jensen");
        Besked besked = null;

        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
        beskedhistorik.tilfoejBesked(besked);
    }

    @Test(expected = AfsenderErNullException.class)
    public void tc4_nullAfsender() throws BeskedErNullException, AfsenderErNullException, PatientErNullException {
        Patient patient = null;

        Besked besked = new Besked();
        besked.saetTekst("tekst");
        besked.saetAfsender(patient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        besked.saetDatotid(LocalDateTime.parse("2020-05-22 15:10", formatter));

        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
        beskedhistorik.tilfoejBesked(besked);
    }

    @Test(expected = PatientErNullException.class)
    public void tc5_nullPatient() throws PatientErNullException {
        Patient patient = null;
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
    }

    @Test
    public void tc6_sorteredeBeskeder() throws BeskedErNullException, AfsenderErNullException, PatientErNullException {
        Patient patient = new Patient("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime datotid = LocalDateTime.parse("2020-05-22 15:10", formatter);
        Besked besked = new Besked();
        besked.saetTekst("tekst");
        besked.saetAfsender(patient);
        besked.saetDatotid(datotid);
        beskedhistorik.tilfoejBesked(besked);

        LocalDateTime datotid2 = LocalDateTime.parse("2020-05-22 14:00", formatter);
        Besked besked2 = new Besked();
        besked2.saetTekst("tekst");
        besked2.saetAfsender(patient);
        besked2.saetDatotid(datotid2);
        beskedhistorik.tilfoejBesked(besked2);

        assertEquals(datotid2.toString(), beskedhistorik.hentBeskeder().get(0).hentDatotid().toString());
        assertEquals(datotid.toString(), beskedhistorik.hentBeskeder().get(1).hentDatotid().toString());
    }

}
