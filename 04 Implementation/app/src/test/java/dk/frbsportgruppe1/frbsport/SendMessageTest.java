package dk.frbsportgruppe1.frbsport;

import androidx.appcompat.app.AppCompatActivity;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class SendMessageTest {

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD02 når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke beskeder over 255 tegn.
     */
    @Test (expected = BeskedForLangException.class)
    public void sendBesked_tc1(){
        Patient patient = new Patient ("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        String beskedtekst = "";
        for (int i = 0; i <= 256; i++) {
            beskedtekst += "z";
        }

        Besked besked = new Besked ();
        besked.saetTekst(beskedtekst);
        besked.saetAfsender(patient);

        beskedhistorik.sendBesked(besked, patient);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke tomme beskeder.
     */
    @Test (expected = BeskedtekstErNullException)
    public void sendBesked_tc2(){
        Patient patient = new Patient ("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        Besked besked = new Besked();
        besked.saetTekst(null);
        besked.saetAfsender(patient);

        beskedhistorik.sendBesked(besked, patient);
    }

    /**
     *     Denne test er lavet for at sikre vores main success scenario virker og beskeder bliver sendt.
     */
    @Test
    public void sendBesked_tc3(){
        Patient patient = new Patient ("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        beskedhistorik.sendBesked(besked, patient);

        Besked besked = new Besked();
        besked.saetTekst("flot");
        besked.saetAfsender(patient);
        besked.saetDatotid(LocalDateTime.parse("2020-05-22 15:10"));
        
        beskedhistorik.tilfoejBesked(besked, patient);

        assertEquals(1, beskedhistorik.beskeder.size());
        assertEquals(besked.afsendt, true);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke beskeder kun at indeholde whitespaces.
     */
    @Test (expected = UgyldigBeskedException)
    public void sendBesked_tc4(){
        Patient patient = new Patient ("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        beskedhistorik.sendBesked(besked, patient);

        Besked besked = new Besked();
        besked.saetTekst("          ");
        besked.saetAfsender(patient);

    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi skal knytte en patient til en beskedhistorik.
     */
    @Test (expected = AfsenderErNullException)
    public void sendBesked_tc5(){
        Patient patient = new Patient (null);
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        Besked besked = new Besked();
        besked.saetTekst("flot");
        besked.saetAfsender(patient);

        beskedhistorik.sendBesked(besked, patient);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi skal have dato på en sendt besked.
     */
    @Test (expected = DatoErNullException)
    public void sendBesked_tc6(){
        Patient patient = new Patient ("Tom Jensen");
        Beskedhistorik beskedhistorik = new Beskedhistorik(patient);

        beskedhistorik.sendBesked(besked, patient);

        Besked besked = new Besked();
        besked.saetTekst("flot");
        besked.saetAfsender(patient);
        besked.saetDatotid(null);
    }
}
