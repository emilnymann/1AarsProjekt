package dk.frbsportgruppe1.frbsport;
//ATD0102

import org.junit.Test;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class PatientoversigtTest {

    @Test
    public void visPatientoversigtTcOne(){
        Patientoversigt po=new Patientoversigt();
        Behandler b=new Behandler(1,"Christian Luul");
        po.saetBehandler(b);
        assertEquals(0,po.patientSize,"Patientoversigt.patientSize gav andet end 0 når det burde være 0 når der ingen patienter er.");
    }

    @Test
    public void visPatientoversigtTcTwo(){
        Patientoversigt po=new Patientoversigt();
        Behandler b=new Behandler(1,"Christian Luul");
        po.saetBehandler(b);
        po.tilfoej(new Patient(2,"Tom Jensen",b));
        assertEquals(1,po.patientSize,"Patientoversigt.patientSize gav andet end 1 når det burde være 1 når der er en patienter.");
    }

    @Test(expected = PatientErNullException.class)
    public void visPatientoversigtTcThree(){
        Patientoversigt po=new Patientoversigt();
        Behandler b=new Behandler(1,"Christian Luul");
        po.saetBehandler(b);
        po.tilfoej(null);
    }

    @Test(expected = BehandlerErNullException.class)
    public void visPatientoversigtTcFour(){
        Patientoversigt po=new Patientoversigt();
        Behandler b=new Behandler(1,"Christian Luul");
        po.saetBehandler(null);
        po.tilfoej(new Patient(2,"Tom Jensen",b));
    }

    //Samme som TC4
    /*@Test(expected = BehandlerErNullException.class)
    public void visPatientoversigtTcFive(){
        Patientoversigt po=new Patientoversigt();
        po.saetBehandler(null);
        po.tilfoej(new Patient(2,"Tom Jensen"));
    }*/

    @Test
    public void visPatientoversigtTcSix(){
        Patientoversigt po=new Patientoversigt();
        Behandler b=new Behandler(1,"Christian Luul");
        po.saetBehandler(b);
        Patient pEt=new Patient(2,"Tom Jensen",b);
        Patient pTo=new Patient(3,"Preben Hansen",b);
        LocalDateTime ldtEt=LocalDateTime.parse("2020-02-22 13:37:00");
        pET.tilfoejBesked(new Besked("Hej",2,ldtEt));
        LocalDateTime ldtTo=LocalDateTime.parse("2020-02-23 14:10:15");
        pET.tilfoejBesked(new Besked("Hej",2,ldtTo));
        po.tilfoej(pEt);
        po.tilfoej(pTo);
        assertEquals(2,po.patientSize,"Patientoversigt.patientSize gav andet end 2 når det burde være 2 når der er to patienter.");
        assertEquals("2020-02-22 13:37:00",pEt.hentDatoTidPaaBesked(0),"Kunne ikke få den rigtige tidsstempel på besked 0 hos patient et");
        assertEquals("2020-02-23 14:10:15",pTo.hentDatoTidPaaBesked(0),"Kunne ikke få den rigtige tidsstempel på besked 0 hos patient to");
    }
}
