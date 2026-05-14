class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        PriorityQueue<int[]> minCapital = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> maxProfit =
        new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            minCapital.offer(new int[]{capital[i], profits[i]});
        }

        for (int i = 0; i < k; i++) {

            while (!minCapital.isEmpty() &&
                    minCapital.peek()[0] <= w) {

                maxProfit.offer(minCapital.poll()[1]);
            }

            if (maxProfit.isEmpty()) {
                break;
            }

            w += maxProfit.poll();
        }

        return w;
    }
}