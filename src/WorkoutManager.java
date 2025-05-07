/*
 * WorkoutManager.java
 * Handles logging, editing, deleting, viewing, and filtering workouts for users.
 */

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class WorkoutManager {
    // Log a new workout for the user
    public void logWorkout(Scanner scanner, User user) {
        System.out.println("\n=== Log Workout ===");
        System.out.print("Enter workout type (e.g., Running, Weightlifting): ");
        String type = scanner.nextLine();
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format. Workout not logged.\n");
            return;
        }
        System.out.print("Distance (km, 0 if N/A): ");
        double distance = Utils.readDouble(scanner);
        System.out.print("Sets (0 if N/A): ");
        int sets = Utils.readInt(scanner);
        System.out.print("Reps (0 if N/A): ");
        int reps = Utils.readInt(scanner);
        System.out.print("Calories burned (0 if unknown): ");
        int calories = Utils.readInt(scanner);
        System.out.print("Notes (optional): ");
        String notes = scanner.nextLine();
        Workout workout = new Workout(type, date, distance, sets, reps, calories, notes);
        user.addWorkout(workout);
        System.out.println("Workout logged successfully!\n");
    }

    // View, edit, and delete workout history
    public void viewHistory(Scanner scanner, User user) {
        System.out.println("\n=== Workout History ===");
        List<Workout> workouts = user.getWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts logged yet.\n");
            return;
        }
        for (int i = 0; i < workouts.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, workouts.get(i));
        }
        System.out.println("\nOptions: [E]dit, [D]elete, [F]ilter, [Enter] to return");
        String option = scanner.nextLine().trim().toUpperCase();
        switch (option) {
            case "E":
                editWorkout(scanner, user, workouts);
                break;
            case "D":
                deleteWorkout(scanner, user, workouts);
                break;
            case "F":
                filterWorkouts(scanner, workouts);
                break;
            default:
                // Return to main menu
                break;
        }
    }

    // Edit a workout entry
    private void editWorkout(Scanner scanner, User user, List<Workout> workouts) {
        System.out.print("Enter workout number to edit: ");
        int idx = Utils.readInt(scanner) - 1;
        if (idx < 0 || idx >= workouts.size()) {
            System.out.println("Invalid selection.\n");
            return;
        }
        // Remove the selected workout, then re-log
        workouts.remove(idx);
        System.out.println("Re-enter workout details:");
        logWorkout(scanner, user);
    }

    // Delete a workout entry
    private void deleteWorkout(Scanner scanner, User user, List<Workout> workouts) {
        System.out.print("Enter workout number to delete: ");
        int idx = Utils.readInt(scanner) - 1;
        if (idx < 0 || idx >= workouts.size()) {
            System.out.println("Invalid selection.\n");
            return;
        }
        workouts.remove(idx);
        System.out.println("Workout deleted.\n");
    }

    // Filter workouts by date or type
    private void filterWorkouts(Scanner scanner, List<Workout> workouts) {
        System.out.println("Filter by: 1. Date  2. Type");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String dateStr = scanner.nextLine();
            try {
                LocalDate date = LocalDate.parse(dateStr);
                workouts.stream().filter(w -> w.getDate().equals(date))
                        .forEach(w -> System.out.println(w));
            } catch (Exception e) {
                System.out.println("Invalid date format.");
            }
        } else if (choice.equals("2")) {
            System.out.print("Enter type: ");
            String type = scanner.nextLine();
            workouts.stream().filter(w -> w.getType().equalsIgnoreCase(type))
                    .forEach(w -> System.out.println(w));
        } else {
            System.out.println("Invalid filter option.");
        }
    }

    // Show performance summary (weekly/monthly trends)
    public void showSummary(User user) {
        List<Workout> workouts = user.getWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts to summarize.\n");
            return;
        }
        int totalWorkouts = workouts.size();
        double totalDistance = 0;
        int maxReps = 0;
        int totalCalories = 0;
        for (Workout w : workouts) {
            totalDistance += w.getDistance();
            if (w.getReps() > maxReps) maxReps = w.getReps();
            totalCalories += w.getCalories();
        }
        System.out.println("\n=== Performance Summary ===");
        System.out.printf("Total Workouts: %d\n", totalWorkouts);
        System.out.printf("Total Distance: %.2f km\n", totalDistance);
        System.out.printf("Max Reps in a Workout: %d\n", maxReps);
        System.out.printf("Total Calories Burned: %d\n", totalCalories);
    }
}
