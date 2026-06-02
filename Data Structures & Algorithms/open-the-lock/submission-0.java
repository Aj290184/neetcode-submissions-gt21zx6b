class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>(Arrays.asList(deadends));

        if (dead.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer("0000");
        visited.add("0000");

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String curr = queue.poll();

                if (curr.equals(target)) {
                    return steps;
                }

                for (String next : getNeighbors(curr)) {
                    if (!dead.contains(next) && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private List<String> getNeighbors(String state) {
        List<String> neighbors = new ArrayList<>();

        char[] chars = state.toCharArray();

        for (int i = 0; i < 4; i++) {
            char original = chars[i];

            chars[i] = (char) ((original - '0' + 1) % 10 + '0');
            neighbors.add(new String(chars));

            chars[i] = (char) ((original - '0' + 9) % 10 + '0');
            neighbors.add(new String(chars));

            chars[i] = original;
        }

        return neighbors;
    }
}