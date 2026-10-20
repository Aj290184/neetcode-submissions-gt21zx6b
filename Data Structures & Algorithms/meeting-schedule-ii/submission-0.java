class Solution {
    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return 0;
        }

        intervals.sort((a, b) -> Integer.compare(a.start, b.start));

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Interval meeting : intervals) {
            if (!minHeap.isEmpty() &&
                minHeap.peek() <= meeting.start) {
                minHeap.poll();
            }

            minHeap.offer(meeting.end);
        }

        return minHeap.size();
    }
}
