
class Solution {
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> available = new PriorityQueue<>();

        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            int cmp = Long.compare(a[0], b[0]);
            if (cmp != 0) return cmp;
            return Long.compare(a[1], b[1]);
        });

        for (int i = 0; i < n; i++) {
            available.offer(i);
        }

        int[] count = new int[n];

        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;

            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.offer((int) busy.poll()[1]);
            }

            if (!available.isEmpty()) {
                int room = available.poll();
                busy.offer(new long[]{end, room});
                count[room]++;
            } else {
                long[] earliest = busy.poll();
                long freeTime = earliest[0];
                int room = (int) earliest[1];

                busy.offer(new long[]{freeTime + duration, room});
                count[room]++;
            }
        }

        int result = 0;

        for (int i = 1; i < n; i++) {
            if (count[i] > count[result]) {
                result = i;
            }
        }

        return result;
    }
}
