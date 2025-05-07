/*
 * User.java
 * Represents a user in the Fitness Progress Tracker system.
 * Stores user credentials and provides access to user-specific data.
 */

import java.util.ArrayList;
import java.util.List;

public class User {
    // Username for login
    private String username;
    // Password (for demo purposes, stored as plain text; use hashing in production)
    private String password;
    // List of workouts logged by the user
    private List<Workout> workouts;

    // Constructor initializes user with username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.workouts = new ArrayList<>();
    }

    // Get the username
    public String getUsername() {
        return username;
    }

    // Check if the provided password matches the user's password
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    // Add a workout to the user's workout list
    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    // Get the user's workout list
    public List<Workout> getWorkouts() {
        return workouts;
    }

    // Remove a workout by index
    public void removeWorkout(int index) {
        if (index >= 0 && index < workouts.size()) {
            workouts.remove(index);
        }
    }
}
