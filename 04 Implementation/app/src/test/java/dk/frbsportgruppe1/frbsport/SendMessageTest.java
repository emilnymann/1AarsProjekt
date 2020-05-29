package dk.frbsportgruppe1.frbsport;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.InvalidMessageException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageTooLongException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SendMessageTest {

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD02 når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke beskeder over 255 tegn.
     */
    @Test (expected = MessageTooLongException.class)
    public void sendBesked_tc1() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        Patient patient = new Patient ("Tom Jensen", "TestUsername", new Practicioner("Christian Iuul", "TestUsername"));
        MessageIndex messageIndex = new MessageIndex(patient);

        String messageText = "";
        for (int i = 0; i <= 256; i++) {
            messageText += "z";
        }

        messageIndex.sendMessage(messageText, patient);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke tomme beskeder.
     */
    @Test (expected = MessageIsNullException.class)
    public void sendBesked_tc2() throws PatientIsNullException, SenderIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, DateIsNullException {
        Patient patient = new Patient ("Tom Jensen", "TestUsername", new Practicioner("Christian Iuul", "TestUsername"));
        MessageIndex messageIndex = new MessageIndex(patient);

        messageIndex.sendMessage("", patient);
    }

    /**
     *     Denne test er lavet for at sikre vores main success scenario virker og beskeder bliver sendt.
     */
    @Test
    public void sendBesked_tc3() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, SenderIsNullException, InvalidMessageException, DateIsNullException {
        Patient patient = new Patient ("Tom Jensen", "TestUsername", new Practicioner("Christian Iuul", "TestUsername"));
        MessageIndex messageIndex = new MessageIndex(patient);

        messageIndex.sendMessage("flot", patient);

        Message message = new Message();
        message.setText("flot");
        message.setSender(patient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        message.setDateTime(LocalDateTime.parse("2020-05-22 15:10", formatter));

        message.setSent(true);

        assertEquals(1, messageIndex.getMessages().size());
        assertTrue(message.isSent());
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke beskeder kun at indeholde whitespaces.
     */
    @Test (expected = InvalidMessageException.class)
    public void sendBesked_tc4() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        Patient patient = new Patient ("Tom Jensen", "TestUsername", new Practicioner("Christian Iuul", "TestUsername"));
        MessageIndex messageIndex = new MessageIndex(patient);

        messageIndex.sendMessage("               ", patient);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi skal knytte en patient til en beskedhistorik.
     */
    @Test (expected = SenderIsNullException.class)
    public void sendBesked_tc5() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        Patient patient = new Patient("Tom Jensen", "TestUsername", new Practicioner("Christian Iuul", "TestUsername"));
        MessageIndex messageIndex = new MessageIndex(patient);

        messageIndex.sendMessage("flot", null);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi skal have dato på en sendt besked.
     */
    @Test (expected = DateIsNullException.class)
    public void sendBesked_tc6() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        Patient patient = new Patient ("Tom Jensen", "TestUsername", new Practicioner("Christian Iuul", "TestUsername"));
        MessageIndex messageIndex = new MessageIndex(patient);
        messageIndex.sendMessage("flot", patient);

        Message message = new Message();
        message.setText("flot");
        message.setSender(patient);
        message.setDateTime(null);
        messageIndex.addMessage(message);
    }
}
