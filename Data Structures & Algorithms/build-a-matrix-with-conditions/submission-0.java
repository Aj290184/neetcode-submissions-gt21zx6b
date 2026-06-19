class Solution {

    public int[][] buildMatrix(int k, int[][] rowConditions,
        int[][] colConditions) {

        List<Integer> rowOrder = topoSort(k, rowConditions);
        List<Integer> colOrder = topoSort(k, colConditions);

        if (rowOrder.isEmpty() || colOrder.isEmpty()) {
            return new int[0][0];
        }

        int[] rowPos = new int[k + 1];
        int[] colPos = new int[k + 1];

        for (int i = 0; i < k; i++) {
            rowPos[rowOrder.get(i)] = i;
            colPos[colOrder.get(i)] = i;
        }

        int[][] matrix = new int[k][k];

        for (int num = 1; num <= k; num++) {
            matrix[rowPos[num]][colPos[num]] = num;
        }

        return matrix;
    }

    private List<Integer> topoSort(int k, int[][] conditions) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= k; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[k + 1];

        for (int[] condition : conditions) {

            int u = condition[0];
            int v = condition[1];

            graph.get(u).add(v);
            indegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= k; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {

            int node = queue.poll();
            order.add(node);

            for (int neighbor : graph.get(node)) {

                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return order.size() == k ? order : new ArrayList<>();
    }
}