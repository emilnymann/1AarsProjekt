package dk.frbsportgruppe1.frbsport.model;

import java.util.ArrayList;

import dk.frbsportgruppe1.frbsport.model.exceptions.WorkoutIsNullException;

public interface WorkoutIndex {
    void addWorkout (Workout workout) throws WorkoutIsNullException;
    void removeWorkout(Workout workout);
    void setWorkouts(ArrayList<Workout> workouts);
    ArrayList<Workout> getWorkouts();
    Workoutplan getWorkoutPlan();
    void setWorkoutplan(Workoutplan workoutplan);
}
