
import java.util.HashMap;
import java.util.Map;

public class MainA3 {

    public static class Result {

        public String item;
        public int count;

        public Result(String item, int count) {
            this.item = item;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    public static Result mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) {
            return null;
        }

        Map<String, Integer> counts = new HashMap<>();
        int maxCount = 0;

        // Pass 1: Build frequency map
        for (String order : orders) {
            int currentCount = counts.getOrDefault(order, 0) + 1;
            counts.put(order, currentCount);
            if (currentCount > maxCount) {
                maxCount = currentCount;
            }
        }

        // Pass 2: Find the first item in order of appearance with maxCount
        for (String order : orders) {
            if (counts.get(order) == maxCount) {
                return new Result(order, maxCount);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Sample 1
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        System.out.println("Sample 1 Output: " + mostPopular(orders1));
        // Expected Output: ("dosa", 3)

        // Sample 2
        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        System.out.println("Sample 2 Output: " + mostPopular(orders2));
        // Expected Output: ("tea", 2)
    }
}
