class Solution {
    public static int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;

        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (canShip(weights, days, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static boolean canShip(int[] weights, int days, int capacity) {
        int daysUsed = 1;
        int currentLoad = 0;

        for (int w : weights) {
            if (currentLoad + w > capacity) {
                daysUsed++;
                currentLoad = w;
            } else {
                currentLoad += w;
            }
        }

        return daysUsed <= days;
    }
}