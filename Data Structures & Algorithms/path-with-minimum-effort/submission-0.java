class Solution {
    public int minimumEffortPath(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        int[][] effort = new int[m][n];

        for (int[] row : effort) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] - b[0]);

        pq.offer(new int[]{0, 0, 0});
        effort[0][0] = 0;

        int[][] dirs = {
            {1, 0}, {-1, 0},
            {0, 1}, {0, -1}
        };

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int currEffort = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (r == m - 1 && c == n - 1) {
                return currEffort;
            }

            if (currEffort > effort[r][c]) {
                continue;
            }

            for (int[] dir : dirs) {

                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                int edgeWeight = Math.abs(heights[r][c] - heights[nr][nc]);

                int newEffort = Math.max(currEffort, edgeWeight);

                if (newEffort < effort[nr][nc]) {

                    effort[nr][nc] = newEffort;

                    pq.offer(new int[]{newEffort, nr, nc});
                }
            }
        }

        return 0;
    }
}