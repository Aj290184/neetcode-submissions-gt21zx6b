class CountSquares {
    private Map<Integer, Map<Integer, Integer>> points;

    public CountSquares() {
        points = new HashMap<>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];

        points.putIfAbsent(x, new HashMap<>());
        Map<Integer, Integer> column = points.get(x);

        column.put(y, column.getOrDefault(y, 0) + 1);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        int result = 0;

        Map<Integer, Integer> column = points.get(x);

        if (column == null) {
            return 0;
        }

        for (Map.Entry<Integer, Integer> entry : column.entrySet()) {
            int y2 = entry.getKey();

            if (y2 == y) {
                continue;
            }

            int side = Math.abs(y2 - y);
            int verticalCount = entry.getValue();

            // Check squares on the left and right.
            for (int x2 : new int[]{x - side, x + side}) {
                int horizontalCount =
                    points.getOrDefault(x2, Collections.emptyMap())
                          .getOrDefault(y, 0);

                int diagonalCount =
                    points.getOrDefault(x2, Collections.emptyMap())
                          .getOrDefault(y2, 0);

                result += verticalCount
                        * horizontalCount
                        * diagonalCount;
            }
        }

        return result;
    }
}
