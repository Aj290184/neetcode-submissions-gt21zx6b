class Solution {
    public double maxProbability(int n, int[][] edges,
        double[] succProb, int start_node, int end_node) {

        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];

            graph.get(u).add(new Pair(v, prob));
            graph.get(v).add(new Pair(u, prob));
        }

        double[] bestProb = new double[n];
        bestProb[start_node] = 1.0;

        PriorityQueue<State> pq = new PriorityQueue<>((a, b) ->
            Double.compare(b.probability, a.probability));

        pq.offer(new State(start_node, 1.0));

        while (!pq.isEmpty()) {

            State curr = pq.poll();

            int node = curr.node;
            double prob = curr.probability;

            if (node == end_node) {
                return prob;
            }

            if (prob < bestProb[node]) {
                continue;
            }

            for (Pair neighbor : graph.get(node)) {

                double newProb = prob * neighbor.probability;

                if (newProb > bestProb[neighbor.node]) {

                    bestProb[neighbor.node] = newProb;

                    pq.offer(
                        new State(neighbor.node, newProb)
                    );
                }
            }
        }

        return 0.0;
    }

    class Pair {
        int node;
        double probability;

        Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }

    class State {
        int node;
        double probability;

        State(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }
}