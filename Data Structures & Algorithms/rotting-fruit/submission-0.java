class Solution {

    public int orangesRotting(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        int fresh = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }

                else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) {
            return 0;
        }

        int minutes = 0;

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.size();

            minutes++;

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                int row = current[0];
                int col = current[1];

                for (int[] dir : directions) {

                    int newRow = row + dir[0];
                    int newCol = col + dir[1];

                    if (newRow < 0 || newCol < 0 ||
                        newRow >= rows ||
                        newCol >= cols ||
                        grid[newRow][newCol] != 1) {

                        continue;
                    }

                    grid[newRow][newCol] = 2;
                    fresh--;

                    queue.offer(new int[]{ newRow, newCol });
                }
            }
        }

        return fresh == 0 ? minutes : -1;
    }
}