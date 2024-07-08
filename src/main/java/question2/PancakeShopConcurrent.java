package question2;

import java.time.LocalTime;
import java.util.Random;

public class PancakeShopConcurrent {
    private static final int MAX_PANCAKES_PER_USER = 5;
    private static final int MAX_PANCAKES_PER_BATCH = 12;
    private static final int NUM_USERS = 3;
    private int pancakesMade = 0;
    private int[] pancakesEaten = new int[3];

    public PancakeShopConcurrent() {
    }

    public static void main(String[] args) {
        PancakeShopConcurrent shop = new PancakeShopConcurrent();
        shop.runSimulation();
    }

    public synchronized void makePancakes() {
        this.pancakesMade = 12;
    }

    public synchronized void eatPancakes(int user, int pancakes) {
        if (this.pancakesMade >= pancakes) {
            this.pancakesEaten[user] = pancakes;
            this.pancakesMade -= pancakes;
        } else {
            this.pancakesEaten[user] = this.pancakesMade;
            this.pancakesMade = 0;
        }

    }

    public void runSimulation() {
        Random random = new Random();
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = startTime.plusSeconds(30L);
        this.makePancakes();

        int totalPancakesEaten;
        int i;
        for(totalPancakesEaten = 0; totalPancakesEaten < 3; ++totalPancakesEaten) {
            i = random.nextInt(6);
            this.eatPancakes(totalPancakesEaten, i);
        }

        totalPancakesEaten = 0;

        for(i = 0; i < 3; ++i) {
            totalPancakesEaten += this.pancakesEaten[i];
        }

        boolean needsMet = totalPancakesEaten <= 12;
        int pancakesWasted = needsMet ? 12 - totalPancakesEaten : 0;
        int unmetOrders = needsMet ? 0 : totalPancakesEaten - 12;
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Pancakes Made by Shopkeeper: 12");
        System.out.println("Pancakes Eaten by Users: ");

        for(i = 0; i < 3; ++i) {
            System.out.println("User " + (i + 1) + ": " + this.pancakesEaten[i] + " pancakes");
        }

        System.out.println("Shopkeeper was able to meet the needs: " + needsMet);
        System.out.println("Pancakes wasted: " + pancakesWasted);
        if (!needsMet) {
            System.out.println("Unmet pancake orders: " + unmetOrders);
        }

    }
}
