class Solution {

    Map<String, Integer> dp = new HashMap<>();

    public int findTargetSumWays(int[] nums, int target) {
        return solve(nums, 0, 0, target);
    }

    public int solve(int[] nums, int index, int sum, int target) {

        // Base case
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }

        String key = index + "," + sum;

        // Already calculated
        if (dp.containsKey(key)) {
            return dp.get(key);
        }

        // Choose +
        int plus = solve(
            nums,
            index + 1,
            sum + nums[index],
            target
        );

        // Choose -
        int minus = solve(
            nums,
            index + 1,
            sum - nums[index],
            target
        );

        int result = plus + minus;

        dp.put(key, result);

        return result;
    }
}