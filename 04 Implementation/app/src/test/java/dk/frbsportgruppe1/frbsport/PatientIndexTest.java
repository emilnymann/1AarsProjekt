package dk.frbsportgruppe1.frbsport;
//UTD0102

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.frbsportgruppe1.frbsport.model.Message;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.PatientIndex;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.exceptions.MessageIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.PracticionerIsNullException;

public class PatientIndexTest{
    @Test
    public void isPatientIndexEmpty_tc1() throws PracticionerIsNullException{
        PatientIndex patientIndex =new PatientIndex();
        Practicioner practicioner=new Practicioner("Christian Iuul");
        patientIndex.setPracticioner(practicioner);
        assertEquals("PatientList.patientSize gav andet end 0 når det burde være 0 når der ingen patienter er.",0,patientIndex.patientSize());
    }

    @Test
    public void isPatientAddedToPatientIndex_tc2() throws PracticionerIsNullException,PatientIsNullException{
        PatientIndex patientIndex=new PatientIndex();
        Practicioner practicioner=new Practicioner("Christian Iuul");
        patientIndex.setPracticioner(practicioner);
        patientIndex.addPatient(new Patient("Tom Jensen",practicioner));
        assertEquals("patientSize gav andet end 1 når det burde være 1 når der er en patienter.",1,patientIndex.patientSize());
    }

    @Test(expected=PatientIsNullException.class)
    public void isPatientNull_tc3() throws PracticionerIsNullException,PatientIsNullException {
        PatientIndex po=new PatientIndex();
        Practicioner practicioner=new Practicioner("Christian Iuul");
        po.setPracticioner(practicioner);
        po.addPatient(null);
    }

    @Test(expected=PracticionerIsNullException.class)
    public void isPracticionerNull_tc4() throws PracticionerIsNullException,PatientIsNullException {
        PatientIndex po=new PatientIndex();
        Practicioner practicioner=new Practicioner("Christian Iuul");
        po.setPracticioner(null);
        po.addPatient(new Patient("Tom Jensen",practicioner));
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
    public void getDateTimeInMessages_tc6() throws PracticionerIsNullException,PatientIsNullException,MessageIsNullException{
        //Grundopsætning af patientoversigt.
        PatientIndex patientIndex=new PatientIndex();
        Practicioner practicioner=new Practicioner("Christian Iuul");
        patientIndex.setPracticioner(practicioner);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        //Patient 1
        Patient p1=new Patient("Tom Jensen",practicioner);
        Message msg1=new Message("Hej Christian",p1);
        LocalDateTime ldt1=LocalDateTime.parse("2020-02-22 13:37:01",formatter);
        msg1.setDateTime(ldt1);
        p1.addMessage(msg1);
        patientIndex.addPatient(p1);

        //Patient 2
        Patient p2=new Patient("Preben Hansen",practicioner);
        Message msg2=new Message("Hej Iuul",p2);
        LocalDateTime ldt2=LocalDateTime.parse("2020-02-23 14:10:15",formatter);
        msg2.setDateTime(ldt2);
        p2.addMessage(msg2);
        patientIndex.addPatient(p2);

        assertEquals("PatientList.patientSize gav andet end 2 når det burde være 2 når der er to patienter.",2,patientIndex.patientSize());
        assertEquals("Kunne ikke få den rigtige tidsstempel på besked 0 hos patient et","2020-02-22T13:37:01",patientIndex.getPatient(0).getMessages().get(0).getDateTime().toString());
        assertEquals("Kunne ikke få den rigtige tidsstempel på besked 0 hos patient to","2020-02-23T14:10:15",patientIndex.getPatient(1).getMessages().get(0).getDateTime().toString());
    }
}