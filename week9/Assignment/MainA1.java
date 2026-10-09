

public class MainA1 {

    // Helper class to represent the output tuple
    public static class Result {

        public int rowIndex;
        public int total;

        public Result(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + total + ")";
        }
    }

    public static Result findTopper(int[][] marks) {
        int bestRowIndex = -1;
        int maxTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            // Update only if strictly greater to keep the smallest row index on tie
            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestRowIndex = i;
            }
        }

        return new Result(bestRowIndex, maxTotal);
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90}, // total: 253
            {88, 92, 79}, // total: 259
            {65, 70, 95} // total: 230
        };

        Result result = findTopper(marks);
        System.out.println("Result: " + result); // Expected Output: (1, 259)
    }
}
