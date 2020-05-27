package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;

import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public class Message implements MessageInterface {
    private String tekst;
    private User afsender;
    private LocalDateTime datotid;
    private boolean afsendt;

    /**
     * Constructoren sætter parametrene tekst og afsender.
     * @param tekst er den tekst der bliver lagt ind i vores beskedhistorik
     * @param afsender er hvem der angiver teksten, så man kan se hvem der har sendt beskeden
     *                 i beskedhistorik.
     */
    public Message(String tekst, User afsender) {
        this.tekst = tekst;
        this.afsender = afsender;
    }

    /**
     * Dette er en no-arg constructor da vi skal kunne lave et objekt uden at give den parametre
     */
    public Message() {
    }

    /**
     * metoden sætter teksten på klassevariablen tekst til den String der er angivet i metodesignaturen
     * @param text er en string som brugeren af appen angiver. Den tekst håndteres længere oppe i systemet,
     *              den sendes igennem systemet til denne metode.
     */
    @Override
    public void setText(String text) {
        this.tekst = text;
    }

    /**
     * tager klassvariablen tekst og returnere den
     * @return tekst returneres
     */
    @Override
    public String getText() {
        return tekst;
    }

    /**
     * klassevariablen afsender sættes i denne metode.
     * @param sender afsender er af Typen bruger. User kan både være en patient
     *                 eller en behandler. afsenderen angives da man skal se hvem der har
     * @throws SenderIsNullException
     */
    @Override
    public void setSender(User sender) throws SenderIsNullException {
        if (sender == null) {
            throw new SenderIsNullException("Beskeden kunne ikke sendes");
        } else {
            this.afsender = sender;
        }

    }

    /**
     * metoden returnere afsender
     * @return afsender
     */
    @Override
    public User getSender() {
        return afsender;
    }

    /**
     * Metoden sætter klassevariablen Datotid til en angivet datotid.
     * @param datotid bruges til at sortere beskeder efter dato. Brugerene skal se beskederne i den rigtige rækkefølge.
     */
    @Override
    public void setDateTime(LocalDateTime datotid) {
        this.datotid = datotid;
    }

    /**
     * Metoden returnere datotid som skal bruges til sortering.
     * @return datotid til brug af sortering
     */
    @Override
    public LocalDateTime getDateTime() {
        return datotid;
    }


}
