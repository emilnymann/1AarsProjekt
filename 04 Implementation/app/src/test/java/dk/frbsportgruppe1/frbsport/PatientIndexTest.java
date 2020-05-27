package dk.frbsportgruppe1.frbsport;
//ATD0102

import org.junit.Test;
import java.time.LocalDateTime;

import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.PatientIndex;
import dk.frbsportgruppe1.frbsport.model.Practicioner;

public class PatientIndexTest {

    @Test
    public void visPatientListTcOne(){
        PatientIndex patientIndex =new PatientIndex();
        Practicioner practicioner=new Practicioner("Christian Iuul");
        patientIndex.setPracticioner(practicioner);
        assertEquals(0, patientIndex.patientSize,"PatientList.patientSize gav andet end 0 når det burde være 0 når der ingen patienter er.");
    }

    @Test
    public void visPatientListTcTwo(){
        PatientIndex po=new PatientIndex();
        Practicioner b=new Practicioner(1,"Christian Luul");
        po.saetPracticioner(b);
        po.addPatient("Tom Jensen");
        assertEquals(1,po.patientSize,"PatientList.patientSize gav andet end 1 når det burde være 1 når der er en patienter.");
    }

    @Test(expected = PatientErNullException.class)
    public void visPatientListTcThree(){
        PatientIndex po=new PatientIndex();
        Practicioner b=new Practicioner(1,"Christian Luul");
        po.saetPracticioner(b);
        po.tilfoej(null);
    }

    @Test(expected = PracticionerErNullException.class)
    public void visPatientListTcFour(){
        PatientIndex po=new PatientIndex();
        Practicioner b=new Practicioner(1,"Christian Luul");
        po.saetPracticioner(null);
        po.tilfoej(new Patient(2,"Tom Jensen",b));
    }

    //Samme som TC4
    /*@Test(expected = PracticionerIsNullException.class)
    public void visPatientListTcFive(){
        PatientList po=new PatientList();
        po.setPracticioner(null);
        po.addPatient(new Patient(2,"Tom Jensen"));
    }*/

    @Test
    public void visPatientListTcSix(){
        PatientIndex po=new PatientIndex();
        Practicioner b=new Practicioner(1,"Christian Luul");
        po.saetPracticioner(b);
        Patient pEt=new Patient(2,"Tom Jensen",b);
        Patient pTo=new Patient(3,"Preben Hansen",b);
        LocalDateTime ldtEt=LocalDateTime.parse("2020-02-22 13:37:00");
        pET.tilfoejBesked(new Besked("Hej",2,ldtEt));
        LocalDateTime ldtTo=LocalDateTime.parse("2020-02-23 14:10:15");
        pET.tilfoejBesked(new Besked("Hej",2,ldtTo));
        po.tilfoej(pEt);
        po.tilfoej(pTo);
        assertEquals(2,po.patientSize,"PatientList.patientSize gav andet end 2 når det burde være 2 når der er to patienter.");
        assertEquals("2020-02-22 13:37:00",pEt.hentDatoTidPaaBesked(0),"Kunne ikke få den rigtige tidsstempel på besked 0 hos patient et");
        assertEquals("2020-02-23 14:10:15",pTo.hentDatoTidPaaBesked(0),"Kunne ikke få den rigtige tidsstempel på besked 0 hos patient to");
    }
}
