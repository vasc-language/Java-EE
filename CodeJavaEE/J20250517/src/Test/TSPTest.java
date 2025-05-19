package Test;

import java.util.Arrays;

public class TSPTest {
    // City coordinates (x,y)
    private static final int[][] CITY_COORDINATES_10 = {
        {81, 14},  // City 0
        {3, 94},   // City 1
        {35, 31},  // City 2
        {28, 17},  // City 3
        {94, 13},  // City 4
        {86, 94},  // City 5
        {69, 11},  // City 6
        {75, 54},  // City 7
        {4, 3},    // City 8
        {11, 27}   // City 9
    };
    
    // Calculate the Euclidean distance matrix between cities
    private static int[][] calculateDistanceMatrix(int[][] coordinates) {
        int n = coordinates.length;
        int[][] distances = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else {
                    // Euclidean distance calculation
                    int dx = coordinates[i][0] - coordinates[j][0];
                    int dy = coordinates[i][1] - coordinates[j][1];
                    distances[i][j] = (int) Math.round(Math.sqrt(dx * dx + dy * dy));
                }
            }
        }
        
        return distances;
    }
    
    // Generate random coordinates for 12/15 cities test
    private static int[][] generateRandomCities(int n) {
        int[][] coordinates = new int[n][2];
        for (int i = 0; i < n; i++) {
            coordinates[i][0] = (int) (Math.random() * 100); // x coordinate (0-99)
            coordinates[i][1] = (int) (Math.random() * 100); // y coordinate (0-99)
        }
        return coordinates;
    }

    // Print the path
    private static void printPath(int[] path) {
        StringBuilder sb = new StringBuilder("Best path: ");
        for (int city : path) {
            sb.append(city).append(" -> ");
        }
        sb.append(path[0]);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        // Test with 10 cities
        System.out.println("=== Testing TSP with 10 cities ===");
        testTSP(CITY_COORDINATES_10);
        
        // Test with 12 cities
        System.out.println("\n=== Testing TSP with 12 cities (randomly generated) ===");
        testTSP(generateRandomCities(12));
        
        // Test with 15 cities
        System.out.println("\n=== Testing TSP with 15 cities (randomly generated) ===");
        testTSP(generateRandomCities(15));
    }
    
    private static void testTSP(int[][] coordinates) {
        int[][] distanceMatrix = calculateDistanceMatrix(coordinates);
        
        // Output city coordinates
        System.out.println("City coordinates:");
        for (int i = 0; i < coordinates.length; i++) {
            System.out.println("City " + i + ": (" + coordinates[i][0] + ", " + coordinates[i][1] + ")");
        }
        
        // Start timing
        long startTime = System.currentTimeMillis();
        
        // Execute TSP algorithm
        TSP.bestCost = Integer.MAX_VALUE; // Reset global variable
        TSP.bestPath.clear();             // Reset global variable 
        TSP.solveTSP(distanceMatrix);
        
        // End timing
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        // Output results
        System.out.println("Shortest path length: " + TSP.bestCost);
        System.out.println("Execution time: " + (duration / 1000.0) + " seconds");
        
        // Output path
        int[] path = new int[TSP.bestPath.size()];
        for (int i = 0; i < TSP.bestPath.size(); i++) {
            path[i] = TSP.bestPath.get(i);
        }
        printPath(path);
    }
}