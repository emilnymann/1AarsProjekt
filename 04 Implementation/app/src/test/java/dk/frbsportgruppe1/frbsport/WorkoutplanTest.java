package dk.frbsportgruppe1.frbsport;

import org.junit.Test;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.exceptions.PatientIsNullException;

import static org.junit.Assert.*;

public class WorkoutplanTest {
    @Test
    public void newWorkoutplanList_tc1() throws PatientIsNullException {
        ArrayList<Workoutplan> workoutplanList = new ArrayList<>();
        Patient patient = new Patient("Tom Jensen", "TestUsername", new Practicioner("Christian Iuul", "TestUsername"));

        assertEquals("Tom Jensen", patient.getName());
        assertEquals(0, workoutplanList.size());
    }

    @Test
    public void addWorkoutplan_tc2() {
        Workoutplan workoutplan = new Workoutplan();
        workoutplan.setTitle("Ben");
        ArrayList<Workoutplan> workoutplanList = new ArrayList<>();
        workoutplanList.add(workoutplan);

        assertEquals(1, workoutplanList.size());
    }

    @Test
    public void workoutplanIsNull_tc3() {
        ArrayList<Workoutplan> workoutplanList = new ArrayList<>();
        Workoutplan workoutplan = null;
        workoutplan.setTitle = "Ben";
        workoutplanList.add(workoutplan);

        assertNull(workoutplan);
    }

    @Test
    public void patientIsNull_tc4() {

    }

    @Test void workoutplanListSize_tc5() {
        ArrayList<Workoutplan> workoutplanList = new ArrayList<>();
        Workoutplan workoutplan = new Workoutplan();
        workoutplan.setTitle("Ben");
        workoutplanList.add(workoutplan)

        Workoutplan workoutplan2 = new Workoutplan();
        workoutplan2.setTitle("Højre arm");
        workoutplanList.add(workoutplan2);

        assertEquals(2, workoutplanList);

    }
}
