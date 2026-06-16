class Solution {
    public int findCheapestPrice(int n,
                                 int[][] flights,
                                 int src,
                                 int dst,
                                 int k) {

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], x -> new ArrayList<>())
                 .add(new int[]{flight[1], flight[2]});
        }

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});

        cost[src] = 0;

        int stops = 0;

        while (!queue.isEmpty() && stops <= k) {

            int size = queue.size();

            int[] tempCost = Arrays.copyOf(cost, n);

            for (int i = 0; i < size; i++) {

                int[] curr = queue.poll();

                int city = curr[0];
                int currCost = curr[1];

                if (!graph.containsKey(city)) {
                    continue;
                }

                for (int[] next : graph.get(city)) {

                    int neighbor = next[0];
                    int price = next[1];

                    if (currCost + price < tempCost[neighbor]) {

                        tempCost[neighbor] = currCost + price;

                        queue.offer(new int[]{
                            neighbor,
                            tempCost[neighbor]
                        });
                    }
                }
            }

            cost = tempCost;
            stops++;
        }

        return cost[dst] == Integer.MAX_VALUE
                ? -1
                : cost[dst];
    }
}