class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;

        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];

        return solve(nums, used, k, 0, 0, target);
    }

    public boolean solve(int[] nums, boolean[] used, int k,
        int start, int currentSum, int target) {

        if (k == 1) {
            return true;
        }

        if (currentSum == target) {

            return solve(nums, used, k - 1, 0, 0, target);
        }

        for (int i = start; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            if (currentSum + nums[i] > target) {
                continue;
            }

            used[i] = true;

            if (solve(nums, used, k, i + 1, currentSum + nums[i], target)) {
                return true;
            }

            used[i] = false;
        }

        return false;
    }
}