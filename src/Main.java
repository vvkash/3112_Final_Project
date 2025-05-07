/*
 * Fitness Progress Tracker
 * Name: Aakash Shah
 * ID: 801367795
 *
 * Main entry point for the Fitness Progress Tracker console application.
 * Handles the main menu, user login/registration, and overall navigation.
 */

import java.util.Scanner;

public class Main {
    // Scanner for user input
    private static final Scanner scanner = new Scanner(System.in);
    // UserManager handles user accounts and login
    private static final UserManager userManager = new UserManager();
    // WorkoutManager handles workout logging and history
    private static final WorkoutManager workoutManager = new WorkoutManager();
    // RecommendationEngine provides suggestions based on workout trends
    private static final RecommendationEngine recommendationEngine = new RecommendationEngine();

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("  Fitness Progress Tracker Console");
        System.out.println("  Name: Aakash Shah   ID: 801367795");
        System.out.println("====================================\n");

        User currentUser = null;
        while (currentUser == null) {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    currentUser = userManager.login(scanner);
                    break;
                case "2":
                    userManager.register(scanner);
                    break;
                case "3":
                    System.out.println("Exiting. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.\n");
            }
        }
        // Main application loop after login
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Log Workout");
            System.out.println("2. View Workout History");
            System.out.println("3. View Performance Summary");
            System.out.println("4. Get Recommendations");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    workoutManager.logWorkout(scanner, currentUser);
                    break;
                case "2":
                    workoutManager.viewHistory(scanner, currentUser);
                    break;
                case "3":
                    workoutManager.showSummary(currentUser);
                    break;
                case "4":
                    recommendationEngine.provideSuggestions(currentUser);
                    break;
                case "5":
                    System.out.println("Logging out...\n");
                    currentUser = null;
                    // Return to login/register
                    main(args);
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
