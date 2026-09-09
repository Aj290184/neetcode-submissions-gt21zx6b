class Solution {
    public int numDecodings(String s) {
        int n = s.length();

        int prev2 = 1; // dp[0]
        int prev1 = 1; // dp[1]

        if (s.charAt(0) == '0') {
            return 0;
        }

        for (int i = 2; i <= n; i++) {
            int curr = 0;

            // Single digit decoding
            if (s.charAt(i - 1) != '0') {
                curr += prev1;
            }

            // Two digit decoding: 10 to 26
            char a = s.charAt(i - 2);
            char b = s.charAt(i - 1);

            if (a == '1' || (a == '2' && b <= '6')) {
                curr += prev2;
            }

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}