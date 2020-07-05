package shejimos;

public class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            int[] js = grid[i];
            for (int j = 0; j < js.length; j++) {
                max = Math.max(max, island(grid, i, j));
            }
        }
        return max;
    }

    private static int island(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length) {
            return 0;
        }
        int area = 0;
        if (grid[x][y] == 1) {
            area = 1;
        } else {
            return 0;
        }
        grid[x][y] = 0;

        return area + island(grid, x, y - 1) + island(grid, x - 1, y) + island(grid, x, y + 1) + island(grid, x + 1, y);
    }
}
