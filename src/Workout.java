/*
 * Workout.java
 * Represents a single workout entry in the Fitness Progress Tracker.
 * Stores details such as type, date, metrics, and notes.
 */

import java.time.LocalDate;

public class Workout {
    // Type of exercise (e.g., Running, Weightlifting)
    private String type;
    // Date of the workout
    private LocalDate date;
    // Distance covered (in kilometers, if applicable)
    private double distance;
    // Sets performed (if applicable)
    private int sets;
    // Repetitions per set (if applicable)
    private int reps;
    // Calories burned (if known)
    private int calories;
    // Optional notes for this workout
    private String notes;

    // Constructor for a workout entry
    public Workout(String type, LocalDate date, double distance, int sets, int reps, int calories, String notes) {
        this.type = type;
        this.date = date;
        this.distance = distance;
        this.sets = sets;
        this.reps = reps;
        this.calories = calories;
        this.notes = notes;
    }

    // Getters for each property
    public String getType() { return type; }
    public LocalDate getDate() { return date; }
    public double getDistance() { return distance; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public int getCalories() { return calories; }
    public String getNotes() { return notes; }

    // String representation for displaying workout details
    @Override
    public String toString() {
        return String.format("%s | %s | Dist: %.2f km | Sets: %d | Reps: %d | Cal: %d | %s",
                date, type, distance, sets, reps, calories, notes);
    }
}
