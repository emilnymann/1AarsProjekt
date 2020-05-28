package dk.frbsportgruppe1.frbsport;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.MessageIndex;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class MessageIndexTest {

    @Test
    public void newMessageHistory_tc1() throws PatientIsNullException {
        Patient patient = new Patient("Tom Jensen",new Practicioner("Christian Iuul"));
        MessageIndex messageIndex = new MessageIndex(patient);

        assertEquals(0, messageIndex.getMessages().size());
        assertEquals("Tom Jensen", messageIndex.getPatient().getName());
    }

    @Test
    public void addMessage_tc2() throws MessageIsNullException, SenderIsNullException, PatientIsNullException {
        Patient patient = new Patient("Tom Jensen",new Practicioner("Christian Iuul"));

        Message message = new Message();
        message.setText("tekst");
        message.setSender(patient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        message.setDateTime(LocalDateTime.parse("2020-05-22 15:10", formatter));

        MessageIndex messageIndex = new MessageIndex(patient);
        messageIndex.addMessage(message);

        assertEquals(1, messageIndex.getMessages().size());
        assertEquals("Tom Jensen", messageIndex.getPatient().getName());
    }

    @Test(expected = MessageIsNullException.class)
    public void nullMessage_tc3() throws MessageIsNullException, PatientIsNullException {
        Patient patient = new Patient("Tom Jensen",new Practicioner("Christian Iuul"));
        Message message = null;

        MessageIndex messageIndex = new MessageIndex(patient);
        messageIndex.addMessage(message);
    }

    @Test(expected = SenderIsNullException.class)
    public void nullSender_tc4() throws MessageIsNullException, SenderIsNullException, PatientIsNullException {
        Patient patient = null;

        Message message = new Message();
        message.setText("tekst");
        message.setSender(patient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        message.setDateTime(LocalDateTime.parse("2020-05-22 15:10", formatter));

        MessageIndex messageIndex = new MessageIndex(patient);
        messageIndex.addMessage(message);
    }

    @Test(expected = PatientIsNullException.class)
    public void nullPatient_tc5() throws PatientIsNullException {
        Patient patient = null;
        MessageIndex messageIndex = new MessageIndex(patient);
    }

    @Test
    public void sortedMessages_tc6() throws MessageIsNullException, SenderIsNullException, PatientIsNullException {
        Patient patient = new Patient("Tom Jensen",new Practicioner("Christian Iuul"));
        MessageIndex messageIndex = new MessageIndex(patient);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse("2020-05-22 15:10", formatter);
        Message message = new Message();
        message.setText("tekst");
        message.setSender(patient);
        message.setDateTime(dateTime);
        messageIndex.addMessage(message);

        LocalDateTime datotid2 = LocalDateTime.parse("2020-05-22 14:00", formatter);
        Message message2 = new Message();
        message2.setText("tekst");
        message2.setSender(patient);
        message2.setDateTime(datotid2);
        messageIndex.addMessage(message2);

        assertEquals(datotid2.toString(), messageIndex.getMessages().get(0).getDateTime().toString());
        assertEquals(dateTime.toString(), messageIndex.getMessages().get(1).getDateTime().toString());
    }

}
