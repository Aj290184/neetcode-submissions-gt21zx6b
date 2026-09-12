class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {

        Set<String> set = new HashSet<>(wordDict);

        Boolean[] dp = new Boolean[s.length()];

        return solve(s, 0, set, dp);
    }

    public boolean solve(String s,
                         int index,
                         Set<String> set,
                         Boolean[] dp) {

        // Reached end
        if (index == s.length()) {
            return true;
        }

        // Already calculated
        if (dp[index] != null) {
            return dp[index];
        }

        // Try every substring
        for (int i = index; i < s.length(); i++) {

            String word =
                    s.substring(index, i + 1);

            // Valid dictionary word
            if (set.contains(word)) {

                if (solve(s,
                          i + 1,
                          set,
                          dp)) {

                    return dp[index] = true;
                }
            }
        }

        return dp[index] = false;
    }
}