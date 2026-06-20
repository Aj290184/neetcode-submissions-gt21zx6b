class Solution {

    class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py) return;

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }
        }
    }

    public boolean canTraverseAllPairs(int[] nums) {

        int n = nums.length;

        if (n == 1) {
            return true;
        }

        for (int num : nums) {
            if (num == 1) {
                return false;
            }
        }

        DSU dsu = new DSU(n);

        Map<Integer, Integer> factorToIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int x = nums[i];

            for (int factor = 2; factor * factor <= x; factor++) {

                if (x % factor == 0) {

                    if (factorToIndex.containsKey(factor)) {
                        dsu.union(i, factorToIndex.get(factor));
                    } else {
                        factorToIndex.put(factor, i);
                    }

                    while (x % factor == 0) {
                        x /= factor;
                    }
                }
            }

            if (x > 1) {
                if (factorToIndex.containsKey(x)) {
                    dsu.union(i, factorToIndex.get(x));
                } else {
                    factorToIndex.put(x, i);
                }
            }
        }

        int root = dsu.find(0);

        for (int i = 1; i < n; i++) {
            if (dsu.find(i) != root) {
                return false;
            }
        }

        return true;
    }
}