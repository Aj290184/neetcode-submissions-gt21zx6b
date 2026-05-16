class Solution {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);

        solve(candidates, target, 0, new ArrayList<>(), ans);

        return ans;
    }

    public void solve(int[] candidates, int target, int index,
    List<Integer> list, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index &&
                candidates[i] == candidates[i - 1]) {
                continue;
            }

            if (candidates[i] > target) {
                break;
            }

            list.add(candidates[i]);

            solve(candidates, target - candidates[i], i + 1, list, ans);

            list.remove(list.size() - 1);
        }
    }
}