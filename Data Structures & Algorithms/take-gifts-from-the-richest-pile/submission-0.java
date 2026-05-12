class Solution {
    public long pickGifts(int[] gifts, int k) {

        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>(Collections.reverseOrder());

        for (int gift : gifts) {
            maxHeap.offer(gift);
        }

        while (k-- > 0) {
            int max = maxHeap.poll();
            int remaining = (int) Math.sqrt(max);
            maxHeap.offer(remaining);
        }

        long sum = 0;

        for (int val : maxHeap) {
            sum += val;
        }

        return sum;
    }
}