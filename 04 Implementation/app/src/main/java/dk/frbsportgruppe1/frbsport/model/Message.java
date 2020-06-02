package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;

import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

public class Message implements MessageInterface {
    private String text;
    private User sender;
    private LocalDateTime dateTime;
    private boolean sent;


    /**
     * Constructoren sætter parametrene text og sender.
     * @param text er den tekst der bliver lagt ind i vores beskedhistorik
     * @param sender er hvem der angiver teksten, så man kan se hvem der har sendt beskeden
     *               i beskedhistorik.
     */
    public Message(String text, User sender) {
        this.text = text;
        this.sender = sender;
    }

    public Message(String text, User sender, LocalDateTime dateTime) {
        this.text = text;
        this.sender = sender;
        this.dateTime = dateTime;
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
        this.text = text;
    }

    /**
     * tager klassvariablen tekst og returnere den
     * @return tekst returneres
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * klassevariablen sender sættes i denne metode.
     * @param sender afsender er af Typen bruger. User kan både være en patient
     *                 eller en behandler. afsenderen angives da man skal se hvem der har
     * @throws SenderIsNullException
     */
    @Override
    public void setSender(User sender) throws SenderIsNullException {
        if (sender == null) {
            throw new SenderIsNullException("Beskeden kunne ikke sendes");
        } else {
            this.sender = sender;
        }

    }

    /**
     * metoden returnerer sender
     * @return sender
     */
    @Override
    public User getSender() {
        return sender;
    }

    /**
     * Metoden sætter klassevariablen Localdatetime til en angivet dateTime.
     * @param dateTime bruges til at sortere beskeder efter dato. Brugerene skal se beskederne i den rigtige rækkefølge.
     */
    @Override
    public void setDateTime(LocalDateTime dateTime) throws DateIsNullException {
        if (dateTime == null) {
            throw new DateIsNullException("dato kan ikke sættes, da dato er nul");
        }
        this.dateTime = dateTime;
    }

    /**
     * Metoden returnerer datotid som skal bruges til sortering.
     * @return datotid til brug af sortering
     */
    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }



}
