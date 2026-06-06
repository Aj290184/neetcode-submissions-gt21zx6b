class Solution {
    public List<Boolean> checkIfPrerequisite(
            int numCourses,
            int[][] prerequisites,
            int[][] queries) {

        boolean[][] reachable = new boolean[numCourses][numCourses];

        for (int[] p : prerequisites) {
            reachable[p[0]][p[1]] = true;
        }

        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    reachable[i][j] |= reachable[i][k] && reachable[k][j];
                }
            }
        }

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {
            ans.add(reachable[q[0]][q[1]]);
        }

        return ans;
    }
}