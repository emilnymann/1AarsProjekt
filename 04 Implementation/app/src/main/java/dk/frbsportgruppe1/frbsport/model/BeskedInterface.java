package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;

import dk.frbsportgruppe1.frbsport.model.exceptions.AfsenderErNullException;

/**
 * Dette interface bliver implementeret af klassen Besked.
 */
public interface BeskedInterface {

    /**
     * Denne metode bliver implementeret i besked klassen og metodens body udfyldes der
     * @param tekst er en string som brugeren af appen angiver. Den tekst sendes igennem
     *              systemet til denne metode.
     */
    void saetTekst(String tekst);

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @return return afleverer en string tilbage som man kan arbejde med.
     * Når implementationen er færdig vil metoden returnere tekst.
     */
    String hentTekst();

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @param afsender afsender er af Typen bruger. Bruger kan både være en patient
     *                 eller en behandler. afsenderen angives da man skal se hvem der har
     *                 sendt beskeden.
     */
    void saetAfsender(Bruger afsender) throws AfsenderErNullException;

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @return her returneres afsenderen angivet i metoden saetAfsender,
     * eller den constructor der kommer til at være i Besked klassen.
     */
    Bruger hentAfsender();

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @param localDateTime Vi bruger LocalDateTime klassen, da en Besked skal tilknyttes
     *                      et tidspunkt så man kan se hvornår den er sendt.
     */
    void saetDatotid(LocalDateTime localDateTime);

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @return da datotid skal bruges skal den returneres, så en anden klasse kan arbejde datotid.
     */
    LocalDateTime hentDatotid();

}
