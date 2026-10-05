class Solution {

    int m;
    int n;

    int[][] memo;

    int[][] directions = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    public int longestIncreasingPath(int[][] matrix) {

        m = matrix.length;
        n = matrix[0].length;

        memo = new int[m][n];

        int answer = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                answer = Math.max(
                    answer,
                    dfs(matrix, i, j)
                );
            }
        }

        return answer;
    }

    private int dfs(int[][] matrix, int row, int col) {

        if (memo[row][col] != 0) {
            return memo[row][col];
        }

        int longest = 1;

        for (int[] dir : directions) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow < 0 || newRow >= m ||
                newCol < 0 || newCol >= n) {
                continue;
            }
            if (matrix[newRow][newCol] > matrix[row][col]) {

                longest = Math.max(
                    longest,
                    1 + dfs(matrix, newRow, newCol)
                );
            }
        }

        memo[row][col] = longest;
        return longest;
    }
}