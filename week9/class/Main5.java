
public class Main5 {

    public static int maxContainerArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(heights[left], heights[right]);
            int area = width * currentHeight;

            maxArea = Math.max(maxArea, area);

            // Move pointer pointing to the shorter wall
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int result = maxContainerArea(heights);
        System.out.println("Max Container Area Output: " + result); // Expected: 49
    }
}
