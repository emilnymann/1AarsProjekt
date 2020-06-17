package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import java.util.function.Predicate;

import dk.frbsportgruppe1.frbsport.model.exceptions.WorkoutIsNullException;
import dk.frbsportgruppe1.frbsport.model.exceptions.WorkoutplanIsNullException;

public class WorkoutIndexImpl extends Observable implements WorkoutIndex {
    Workoutplan workoutplan;
    ArrayList<Workout> workouts = new ArrayList<>();

    public WorkoutIndexImpl(Workoutplan workoutplan) throws WorkoutplanIsNullException {
        if (workoutplan == null){
        throw new WorkoutplanIsNullException("Træningsplan findes ikke");
        } else {
            this.workoutplan = workoutplan;
        }

    }
    @Override
    public void addWorkout(Workout workout) throws WorkoutIsNullException {
        if (workout == null){
            throw new WorkoutIsNullException("øvelsen findes ikke");
        } else {
            workouts.add(workout);
            setChanged();
            notifyObservers(this);
        }
    }

    @Override
    public void removeWorkout(Workout workout) {
        workouts.removeIf(wo -> Objects.equals(wo.getId(), workout.getId()));
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void setWorkouts(ArrayList<Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public ArrayList<Workout> getWorkouts() {
        return workouts;
    }

    @Override
    public Workoutplan getWorkoutPlan() {
        return workoutplan;
    }

    @Override
    public void setWorkoutplan(Workoutplan workoutplan) {
        this.workoutplan = workoutplan;
    }
}
