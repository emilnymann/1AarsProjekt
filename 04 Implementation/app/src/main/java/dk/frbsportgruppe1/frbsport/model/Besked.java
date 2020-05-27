package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;

import dk.frbsportgruppe1.frbsport.model.exceptions.AfsenderErNullException;

public class Besked implements BeskedInterface{
    private String tekst;
    private Bruger afsender;
    private LocalDateTime datotid;
    private boolean afsendt;

    /**
     * Constructoren sætter parametrene tekst og afsender.
     * @param tekst er den tekst der bliver lagt ind i vores beskedhistorik
     * @param afsender er hvem der angiver teksten, så man kan se hvem der har sendt beskeden
     *                 i beskedhistorik.
     */
    public Besked(String tekst, Bruger afsender) {
        this.tekst = tekst;
        this.afsender = afsender;
    }

    /**
     * Dette er en no-arg constructor da vi skal kunne lave et objekt uden at give den parametre
     */
    public Besked() {
    }

    /**
     * metoden sætter teksten på klassevariablen tekst til den String der er angivet i metodesignaturen
     * @param tekst er en string som brugeren af appen angiver. Den tekst håndteres længere oppe i systemet,
     *              den sendes igennem systemet til denne metode.
     */
    @Override
    public void saetTekst(String tekst) {
        this.tekst = tekst;
    }

    /**
     * tager klassvariablen tekst og returnere den
     * @return tekst returneres
     */
    @Override
    public String hentTekst() {
        return tekst;
    }

    /**
     * klassevariablen afsender sættes i denne metode.
     * @param afsender afsender er af Typen bruger. Bruger kan både være en patient
     *                 eller en behandler. afsenderen angives da man skal se hvem der har
     * @throws AfsenderErNullException
     */
    @Override
    public void saetAfsender(Bruger afsender) throws AfsenderErNullException {
        if (afsender == null) {
            throw new AfsenderErNullException("Beskeden kunne ikke sendes");
        } else {
            this.afsender = afsender;
        }

    }

    /**
     * metoden returnere afsender
     * @return afsender
     */
    @Override
    public Bruger hentAfsender() {
        return afsender;
    }

    /**
     * Metoden sætter klassevariablen Datotid til en angivet datotid.
     * @param datotid bruges til at sortere beskeder efter dato. Brugerene skal se beskederne i den rigtige rækkefølge.
     */
    @Override
    public void saetDatotid(LocalDateTime datotid) {
        this.datotid = datotid;
    }

    /**
     * Metoden returnere datotid som skal bruges til sortering.
     * @return datotid til brug af sortering
     */
    @Override
    public LocalDateTime hentDatotid() {
        return datotid;
    }


}
