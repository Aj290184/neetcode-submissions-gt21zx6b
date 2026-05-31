class Solution {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int rows = heights.length;
        int cols = heights[0].length;

        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {

            dfs(heights, i, 0, pacific);
            dfs(heights, i, cols - 1, atlantic);
        }

        for (int j = 0; j < cols; j++) {

            dfs(heights, 0, j, pacific);
            dfs(heights, rows - 1, j, atlantic);
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (pacific[i][j] &&
                    atlantic[i][j]) {

                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;
    }

    public void dfs(int[][] heights, int row, int col, boolean[][] visited) {

        visited[row][col] = true;

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow < 0 || newCol < 0 ||
                newRow >= heights.length ||
                newCol >= heights[0].length ||
                visited[newRow][newCol] ||
                heights[newRow][newCol] < heights[row][col]) {

                continue;
            }

            dfs(heights, newRow, newCol, visited);
        }
    }
}