package dk.frbsportgruppe1.frbsport;
//UTD0102

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.frbsportgruppe1.frbsport.model.MessageImpl;
import dk.frbsportgruppe1.frbsport.model.PatientImpl;
import dk.frbsportgruppe1.frbsport.model.PatientIndexImpl;
import dk.frbsportgruppe1.frbsport.model.PractitionerImpl;
import dk.frbsportgruppe1.frbsport.model.exceptions.DateIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PractitionerIsNullException;

public class PatientImplIndexTest {
    @Test
    public void isPatientIndexEmpty_tc1() throws PractitionerIsNullException {
        PatientIndexImpl patientIndexImpl =new PatientIndexImpl();
        PractitionerImpl practicionerImpl =new PractitionerImpl("Christian Iuul", "TestUsername");
        patientIndexImpl.setPractitioner(practicionerImpl);
        assertEquals("PatientList.patientSize gav andet end 0 når det burde være 0 når der ingen patienter er.",0, patientIndexImpl.patientSize());
    }

    @Test
    public void isPatientAddedToPatientIndex_tc2() throws PractitionerIsNullException,PatientIsNullException{
        PatientIndexImpl patientIndexImpl =new PatientIndexImpl();
        PractitionerImpl practicionerImpl =new PractitionerImpl("Christian Iuul", "TestUsername");
        patientIndexImpl.setPractitioner(practicionerImpl);
        patientIndexImpl.addPatient(new PatientImpl("Tom Jensen", "TestUsername", practicionerImpl));
        assertEquals("patientSize gav andet end 1 når det burde være 1 når der er en patienter.",1, patientIndexImpl.patientSize());
    }

    @Test(expected=PatientIsNullException.class)
    public void isPatientNull_tc3() throws PractitionerIsNullException,PatientIsNullException {
        PatientIndexImpl po=new PatientIndexImpl();
        PractitionerImpl practicionerImpl =new PractitionerImpl("Christian Iuul", "TestUsername");
        po.setPractitioner(practicionerImpl);
        po.addPatient(null);
    }

    @Test(expected= PractitionerIsNullException.class)
    public void isPracticionerNull_tc4() throws PractitionerIsNullException,PatientIsNullException {
        PatientIndexImpl po=new PatientIndexImpl();
        PractitionerImpl practicionerImpl =new PractitionerImpl("Christian Iuul", "TestUsername");
        po.setPractitioner(null);
        po.addPatient(new PatientImpl("Tom Jensen", "TestUsername", practicionerImpl));
    }

    //Samme som TC4
    /*@Test(expected=PracticionerIsNullException.class)
    public void isPracticionerNull_tc5() throws PracticionerIsNullException, PatientIsNullException {
        PatientIndex po=new PatientIndex();
        Practicioner practicioner=new Practicioner("Christian Iuul");
        po.setPracticioner(null);
        po.addPatient(new Patient("Tom Jensen",practicioner));
    }*/

    @Test
    public void getDateTimeInMessages_tc6() throws PractitionerIsNullException, PatientIsNullException, MessageIsNullException, DateIsNullException {
        //Grundopsætning af patientoversigt.
        PatientIndexImpl patientIndexImpl =new PatientIndexImpl();
        PractitionerImpl practicionerImpl =new PractitionerImpl("Christian Iuul", "TestUsername");
        patientIndexImpl.setPractitioner(practicionerImpl);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //Patient 1
        PatientImpl p1=new PatientImpl("Tom Jensen", "TestUsername", practicionerImpl);
        MessageImpl msg1=new MessageImpl("Hej Christian",p1);
        LocalDateTime ldt1=LocalDateTime.parse("2020-02-22 13:37:01",formatter);
        msg1.setDateTime(ldt1);
        p1.addMessage(msg1);
        patientIndexImpl.addPatient(p1);

        //Patient 2
        PatientImpl p2=new PatientImpl("Preben Hansen", "TestUsername", practicionerImpl);
        MessageImpl msg2=new MessageImpl("Hej Iuul",p2);
        LocalDateTime ldt2=LocalDateTime.parse("2020-02-23 14:10:15",formatter);
        msg2.setDateTime(ldt2);
        p2.addMessage(msg2);
        patientIndexImpl.addPatient(p2);

        assertEquals("PatientList.patientSize gav andet end 2 når det burde være 2 når der er to patienter.",2, patientIndexImpl.patientSize());
        assertEquals("Kunne ikke få den rigtige tidsstempel på besked 0 hos patient et","2020-02-22T13:37:01", patientIndexImpl.getPatient(0).getMessages().get(0).getDateTime().toString());
        assertEquals("Kunne ikke få den rigtige tidsstempel på besked 0 hos patient to","2020-02-23T14:10:15", patientIndexImpl.getPatient(1).getMessages().get(0).getDateTime().toString());
    }
}