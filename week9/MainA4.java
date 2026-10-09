
public class MainA4 {

    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings.length < k) {
            return 0;
        }

        int alertsCount = 0;
        long currentSum = 0; // Using long to prevent overflow

        // Calculate sum of the first window
        for (int i = 0; i < k; i++) {
            currentSum += readings[i];
        }

        // Avoid floating-point division by checking currentSum >= (long) k * threshold
        long targetSum = (long) k * threshold;
        if (currentSum >= targetSum) {
            alertsCount++;
        }

        // Slide the window across the array
        for (int i = k; i < readings.length; i++) {
            currentSum += readings[i] - readings[i - k];
            if (currentSum >= targetSum) {
                alertsCount++;
            }
        }

        return alertsCount;
    }

    public static void main(String[] args) {
        int[] readings = {2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        int result = countAlerts(readings, k, threshold);
        System.out.println("Output: " + result);
        // Expected Output: 3 (blocks [2, 5, 5], [5, 5, 5], [5, 5, 8])
    }
}
