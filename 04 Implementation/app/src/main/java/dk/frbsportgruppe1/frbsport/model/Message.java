package dk.frbsportgruppe1.frbsport.model;

import java.time.LocalDateTime;

import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

/**
 * Dette interface bliver implementeret af klassen Message.
 */
public interface Message {

    /**
     * Denne metode bliver implementeret i besked klassen og metodens body udfyldes der
     * @param text er en string som brugeren af appen angiver. Den tekst sendes igennem
     *              systemet til denne metode.
     */
    void setText(String text);

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @return return afleverer en string tilbage som man kan arbejde med.
     * Når implementationen er færdig vil metoden returnere tekst.
     */
    String getText();

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @param sender afsender er af Typen bruger. User kan både være en patient
     *                 eller en behandler. afsenderen angives da man skal se hvem der har
     *                 sendt beskeden.
     */
    void setSender(UserImpl sender) throws SenderIsNullException;

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @return her returneres afsenderen angivet i metoden setSender,
     * eller den constructor der kommer til at være i Message klassen.
     */
    User getSender();

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @param localDateTime Vi bruger LocalDateTime klassen, da en Message skal tilknyttes
     *                      et tidspunkt så man kan se hvornår den er sendt.
     */
    void setDateTime(LocalDateTime localDateTime) throws DateIsNullException;

    /**
     * Denne metode bliver implementeret i besked klassen og udfyldes der.
     * @return da datotid skal bruges skal den returneres, så en anden klasse kan arbejde datotid.
     */
    LocalDateTime getDateTime();

    /**
     *
     */
    public boolean isSent();

    public void setSent(boolean sent);

}
