class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];

        solve(nums, used, new ArrayList<>(), ans);

        return ans;
    }

    public void solve(int[] nums, boolean[] used,
    List<Integer> list, List<List<Integer>> ans) {

        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            list.add(nums[i]);

            solve(nums, used, list, ans);

            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
}