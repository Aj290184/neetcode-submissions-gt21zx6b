class Solution {

    public boolean carPooling(int[][] trips, int capacity) {
        int[] stops = new int[1001];

        for (int[] trip : trips) {

            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];

            stops[from] += passengers;
            stops[to] -= passengers;
        }

        int currentPassengers = 0;

        for (int passengers : stops) {

            currentPassengers += passengers;

            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }
}