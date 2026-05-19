class Solution {
    public List<List<String>> partition(String s) {

        List<List<String>> ans = new ArrayList<>();

        solve(s, 0, new ArrayList<>(), ans);

        return ans;
    }

    public void solve(String s, int index, List<String> list,
    List<List<String>> ans) {

        if (index == s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < s.length(); i++) {
            if (isPalindrome(s, index, i)) {

                list.add(s.substring(index, i + 1));

                solve(s, i + 1, list, ans);

                list.remove(list.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int left, int right) {

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}