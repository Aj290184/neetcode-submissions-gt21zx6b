class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {

        int n = s.length();

        boolean[] dp = new boolean[n];

        dp[0] = true;

        int reachable = 0;

        for (int i = 1; i < n; i++) {

            int add = i - minJump;

            if (add >= 0 && dp[add]) {
                reachable++;
            }

            int remove = i - maxJump - 1;

            if (remove >= 0 && dp[remove]) {
                reachable--;
            }
            if (s.charAt(i) == '0' && reachable > 0) {
                dp[i] = true;
            }
        }

        return dp[n - 1];
    }
}