package dk.frbsportgruppe1.frbsport;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.frbsportgruppe1.frbsport.model.MessageImpl;
import dk.frbsportgruppe1.frbsport.model.MessageIndexImpl;
import dk.frbsportgruppe1.frbsport.model.PatientImpl;
import dk.frbsportgruppe1.frbsport.model.PractitionerImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.InvalidMessageException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageTooLongException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SendMessageImplTest {

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD02 når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke beskeder over 255 tegn.
     */
    @Test (expected = MessageTooLongException.class)
    public void sendMessage_tc1() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername", new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);

        String messageText = "";
        for (int i = 0; i <= 256; i++) {
            messageText += "z";
        }

        messageIndexImpl.sendMessage(messageText, patientImpl);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke tomme beskeder.
     */
    @Test (expected = MessageIsNullException.class)
    public void sendMessage_tc2() throws PatientIsNullException, SenderIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername", new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);

        messageIndexImpl.sendMessage("", patientImpl);
    }

    /**
     *     Denne test er lavet for at sikre vores main success scenario virker og beskeder bliver sendt.
     */
    @Test
    public void sendMessage_tc3() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, SenderIsNullException, InvalidMessageException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername", new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);

        messageIndexImpl.sendMessage("flot", patientImpl);

        MessageImpl messageImpl = new MessageImpl();
        messageImpl.setText("flot");
        messageImpl.setSender(patientImpl);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        messageImpl.setDateTime(LocalDateTime.parse("2020-05-22 15:10", formatter));

        messageImpl.setSent(true);

        assertEquals(1, messageIndexImpl.getMessages().size());
        assertTrue(messageImpl.isSent());
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi tillader ikke beskeder kun at indeholde whitespaces.
     */
    @Test (expected = InvalidMessageException.class)
    public void sendMessage_tc4() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername", new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);

        messageIndexImpl.sendMessage("               ", patientImpl);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi skal knytte en patient til en beskedhistorik.
     */
    @Test (expected = SenderIsNullException.class)
    public void sendMessage_tc5() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername", new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);

        messageIndexImpl.sendMessage("flot", null);
    }

    /**
     * Denne test udføres på UI'et fordi vi kaster exceptions før
     *     vi i vores SD når til object creation og derfor ikke bruger datotid parametret.
     *     Vi skal have dato på en sendt besked.
     */
    @Test (expected = DateIsNullException.class)
    public void sendMessage_tc6() throws PatientIsNullException, MessageTooLongException, MessageIsNullException, InvalidMessageException, SenderIsNullException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername", new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);
        messageIndexImpl.sendMessage("flot", patientImpl);

        MessageImpl messageImpl = new MessageImpl();
        messageImpl.setText("flot");
        messageImpl.setSender(patientImpl);
        messageImpl.setDateTime(null);
        messageIndexImpl.addMessage(messageImpl);
    }
}
