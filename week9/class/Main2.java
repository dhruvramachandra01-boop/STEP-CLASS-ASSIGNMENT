
public class Main2 {

    public static class SummaryResult {

        public long totalItems;
        public int row;
        public int col;

        public SummaryResult(long totalItems, int row, int col) {
            this.totalItems = totalItems;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", (" + row + ", " + col + "))";
        }
    }

    public static SummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new SummaryResult(0, -1, -1);
        }

        long totalItems = 0;
        int maxItems = Integer.MIN_VALUE;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int count = grid[r][c];
                totalItems += count;

                // Captures the first occurrence when scanning row-by-row, left-to-right
                if (count > maxItems) {
                    maxItems = count;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new SummaryResult(totalItems, maxRow, maxCol);
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        SummaryResult result = warehouseSummary(grid);
        System.out.println("Warehouse Summary Output: " + result);
    }
}
