class Solution {

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();

        if (digits.length() == 0) {
            return ans;
        }

        String[] map = {
                "",     // 0
                "",     // 1
                "abc",  // 2
                "def",  // 3
                "ghi",  // 4
                "jkl",  // 5
                "mno",  // 6
                "pqrs", // 7
                "tuv",  // 8
                "wxyz"  // 9
        };

        solve(digits, 0, "", map, ans);

        return ans;
    }

    public void solve(String digits, int index, String str,
     String[] map, List<String> ans) {

        if (index == digits.length()) {
            ans.add(str);
            return;
        }

        String letters = map[digits.charAt(index) - '0'];

        for (char ch : letters.toCharArray()) {
            solve(digits, index + 1, str + ch, map, ans);
        }
    }
}