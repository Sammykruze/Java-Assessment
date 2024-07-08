package question2;

import java.util.Random;
import java.time.LocalTime;

public class PancakeShopNonConcurrent {
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int MAX_PANCAKES_PER_BATCH = 12;
    private static final int NUM_USERS = 3;

    public static void main(String[] args) {
        Random random = new Random();

        // Time slots
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = startTime.plusSeconds(30);

        // Pancake shop operations
        int pancakesMade = MAX_PANCAKES_PER_BATCH;
        int[] pancakesEaten = new int[NUM_USERS];
        int totalPancakesEaten = 0;

        for (int i = 0; i < NUM_USERS; i++) {
            pancakesEaten[i] = random.nextInt(MAX_PANCAKES_PER_USER + 1);
            totalPancakesEaten += pancakesEaten[i];
        }

        boolean needsMet = totalPancakesEaten <= pancakesMade;
        int pancakesWasted = needsMet ? pancakesMade - totalPancakesEaten : 0;
        int unmetOrders = needsMet ? 0 : totalPancakesEaten - pancakesMade;

        // Output results
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Pancakes Made by Shopkeeper: " + pancakesMade);
        System.out.println("Pancakes Eaten by Users: ");
        for (int i = 0; i < NUM_USERS; i++) {
            System.out.println("User " + (i + 1) + ": " + pancakesEaten[i] + " pancakes");
        }
        System.out.println("Shopkeeper was able to meet the needs: " + needsMet);
        System.out.println("Pancakes wasted: " + pancakesWasted);
        if (!needsMet) {
            System.out.println("Unmet pancake orders: " + unmetOrders);
        }
    }
}
