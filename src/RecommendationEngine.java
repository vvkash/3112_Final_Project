/*
 * RecommendationEngine.java
 * Analyzes workout history and provides suggestions to the user.
 * Example: recommends rest days, new goals, or highlights trends.
 */

import java.util.List;

public class RecommendationEngine {
    // Provide suggestions based on user's workout trends
    public void provideSuggestions(User user) {
        List<Workout> workouts = user.getWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts yet. Start logging your workouts to receive suggestions!\n");
            return;
        }
        // Count recent workouts (last 7 days)
        long recent = workouts.stream()
                .filter(w -> w.getDate().isAfter(java.time.LocalDate.now().minusDays(7)))
                .count();
        if (recent >= 5) {
            System.out.println("You've worked out 5+ times in the last week. Consider a rest day for recovery!\n");
        } else if (recent == 0) {
            System.out.println("No workouts logged this week. Set a small goal to get started!\n");
        } else {
            System.out.printf("You've logged %d workouts this week. Keep up the consistency!\n", recent);
        }
        // Suggest new goals if progress stalls
        int maxReps = workouts.stream().mapToInt(Workout::getReps).max().orElse(0);
        if (maxReps < 10) {
            System.out.println("Try increasing your reps for more challenge.");
        }
        // Suggest increasing distance if running/cycling
        double maxDistance = workouts.stream().mapToDouble(Workout::getDistance).max().orElse(0);
        if (maxDistance < 2 && maxDistance > 0) {
            System.out.println("Consider increasing your run distance for endurance gains.");
        }
    }
}
