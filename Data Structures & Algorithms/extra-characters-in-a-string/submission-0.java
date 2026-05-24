class Solution {

    public int minExtraChar(String s, String[] dictionary) {

        Set<String> set = new HashSet<>(Arrays.asList(dictionary));

        int[] dp = new int[s.length()];

        Arrays.fill(dp, -1);

        return solve(s, 0, set, dp);
    }

    public int solve(String s, int index, Set<String> set, int[] dp) {

        if (index == s.length()) {
            return 0;
        }

        if (dp[index] != -1) {
            return dp[index];
        }

        int extra = 1 + solve(s,  index + 1, set, dp);

        for (int i = index; i < s.length(); i++) {
            String word = s.substring(index, i + 1);

            if (set.contains(word)) {

                extra = Math.min(extra, solve(s, i + 1, set, dp));
            }
        }

        return dp[index] = extra;
    }
}