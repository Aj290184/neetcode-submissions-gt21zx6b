class Solution {
    public long minEnd(int n, int x) {
        
        long ans = x;
        long k = n - 1L;
        int bit = 0;

        while (k > 0) {
            if ((((long) x >> bit) & 1L) == 0) {
                ans |= (k & 1L) << bit;
                k >>= 1;
            }
            bit++;
        }

        return ans;
    }
}