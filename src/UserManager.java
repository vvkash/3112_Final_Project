/*
 * UserManager.java
 * Handles user registration, login, and credential management.
 * For demo purposes, user data is stored in memory.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    // Map of usernames to User objects
    private Map<String, User> users = new HashMap<>();

    // Register a new user
    public void register(Scanner scanner) {
        System.out.println("\n=== Register ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Try another.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.put(username, new User(username, password));
        System.out.println("Registration successful!\n");
    }

    // Login an existing user
    public User login(Scanner scanner) {
        System.out.println("\n=== Login ===");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            System.out.println("Login successful!\n");
            return user;
        } else {
            System.out.println("Invalid credentials.\n");
            return null;
        }
    }
}
