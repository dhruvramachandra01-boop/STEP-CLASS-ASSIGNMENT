
import java.util.HashSet;
import java.util.Set;

public class Main3 {

    // Optimal Approach: HashSet Complement Lookup
    public static boolean hasPairWithSum(int[] nums, int target) {
        Set<Integer> visited = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;
            if (visited.contains(complement)) {
                return true;
            }
            visited.add(num);
        }

        return false;
    }

    public static void main(String[] args) {
        // Sample 1[cite: 3, 4]
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Sample 1 Output: " + hasPairWithSum(nums1, target1)); // true

        // Sample 2[cite: 3, 4]
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("Sample 2 Output: " + hasPairWithSum(nums2, target2)); // false
    }
}
