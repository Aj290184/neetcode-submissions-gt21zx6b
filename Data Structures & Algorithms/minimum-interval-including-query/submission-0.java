
class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int n = intervals.length;
        int m = queries.length;

        // Store each query with its original index
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries,
            (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap: [interval length, right endpoint]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        int[] answer = new int[m];
        Arrays.fill(answer, -1);

        int i = 0;

        for (int[] query : sortedQueries) {
            int x = query[0];
            int index = query[1];

            // Add intervals whose left endpoint <= query
            while (i < n && intervals[i][0] <= x) {
                int left = intervals[i][0];
                int right = intervals[i][1];

                int length = right - left + 1;

                minHeap.offer(new int[]{length, right});
                i++;
            }

            // Remove intervals that cannot contain this query
            while (!minHeap.isEmpty() && minHeap.peek()[1] < x) {
                minHeap.poll();
            }

            // The smallest remaining interval is the answer
            if (!minHeap.isEmpty()) {
                answer[index] = minHeap.peek()[0];
            }
        }

        return answer;
    }
}
