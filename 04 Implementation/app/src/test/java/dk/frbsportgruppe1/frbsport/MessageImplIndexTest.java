package dk.frbsportgruppe1.frbsport;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.frbsportgruppe1.frbsport.model.MessageImpl;
import dk.frbsportgruppe1.frbsport.model.MessageIndexImpl;
import dk.frbsportgruppe1.frbsport.model.PatientImpl;
import dk.frbsportgruppe1.frbsport.model.PractitionerImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.SenderIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

public class MessageImplIndexTest {

    @Test
    public void newMessageHistory_tc1() throws PatientIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername",new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);

        assertEquals(0, messageIndexImpl.getMessages().size());
        assertEquals("Tom Jensen", messageIndexImpl.getPatient().getName());
    }

    @Test
    public void addMessage_tc2() throws MessageIsNullException, SenderIsNullException, PatientIsNullException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername",new PractitionerImpl("Christian Iuul", "TestUsername"));

        MessageImpl messageImpl = new MessageImpl();
        messageImpl.setText("tekst");
        messageImpl.setSender(patientImpl);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        messageImpl.setDateTime(LocalDateTime.parse("2020-05-22 15:10", formatter));

        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);
        messageIndexImpl.addMessage(messageImpl);

        assertEquals(1, messageIndexImpl.getMessages().size());
        assertEquals("Tom Jensen", messageIndexImpl.getPatient().getName());
    }

    @Test(expected = MessageIsNullException.class)
    public void nullMessage_tc3() throws MessageIsNullException, PatientIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername",new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageImpl messageImpl = null;

        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);
        messageIndexImpl.addMessage(messageImpl);
    }

    @Test(expected = SenderIsNullException.class)
    public void nullSender_tc4() throws MessageIsNullException, SenderIsNullException, PatientIsNullException, DateIsNullException {
        PatientImpl patientImpl = null;

        MessageImpl messageImpl = new MessageImpl();
        messageImpl.setText("tekst");
        messageImpl.setSender(patientImpl);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        messageImpl.setDateTime(LocalDateTime.parse("2020-05-22 15:10", formatter));

        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);
        messageIndexImpl.addMessage(messageImpl);
    }

    @Test(expected = PatientIsNullException.class)
    public void nullPatient_tc5() throws PatientIsNullException {
        PatientImpl patientImpl = null;
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);
    }

    @Test
    public void sortedMessages_tc6() throws MessageIsNullException, SenderIsNullException, PatientIsNullException, DateIsNullException {
        PatientImpl patientImpl = new PatientImpl("Tom Jensen", "TestUsername",new PractitionerImpl("Christian Iuul", "TestUsername"));
        MessageIndexImpl messageIndexImpl = new MessageIndexImpl(patientImpl);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime dateTime = LocalDateTime.parse("2020-05-22 15:10", formatter);
        MessageImpl messageImpl = new MessageImpl();
        messageImpl.setText("tekst");
        messageImpl.setSender(patientImpl);
        messageImpl.setDateTime(dateTime);
        messageIndexImpl.addMessage(messageImpl);

        LocalDateTime datotid2 = LocalDateTime.parse("2020-05-22 14:00", formatter);
        MessageImpl messageImpl2 = new MessageImpl();
        messageImpl2.setText("tekst");
        messageImpl2.setSender(patientImpl);
        messageImpl2.setDateTime(datotid2);
        messageIndexImpl.addMessage(messageImpl2);

        assertEquals(datotid2.toString(), messageIndexImpl.getMessages().get(0).getDateTime().toString());
        assertEquals(dateTime.toString(), messageIndexImpl.getMessages().get(1).getDateTime().toString());
    }

}
