class Solution {

    public static List<List<Integer>> combine(int n, int k) {

        List<List<Integer>> ans = new ArrayList<>();

        solve(1, n, k, new ArrayList<>(), ans);

        return ans;
    }

    public static void solve(int start, int n, int k,
    List<Integer> list, List<List<Integer>> ans) {

        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= n; i++) {
            list.add(i);

            solve(i + 1, n, k, list, ans);

            list.remove(list.size() - 1);
        }
    }
}