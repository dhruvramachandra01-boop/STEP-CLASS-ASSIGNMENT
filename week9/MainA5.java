
public class MainA5 {

    public static int findSlot(int[] prices, int newPrice) {
        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid; // Price already present
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // low pointer is sitting at the correct insert position
        return low;
    }

    public static void main(String[] args) {
        int[] prices = {120, 150, 200, 260};

        System.out.println("Sample 1 Output: " + findSlot(prices, 150)); // Expected: 1
        System.out.println("Sample 2 Output: " + findSlot(prices, 210)); // Expected: 3
        System.out.println("Sample 3 Output: " + findSlot(prices, 300)); // Expected: 4
    }
}
