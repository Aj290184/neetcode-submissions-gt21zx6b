class Solution {

    public List<String> wordBreak(String s, List<String> wordDict) {

        List<String> ans = new ArrayList<>();

        Set<String> set = new HashSet<>(wordDict);

        solve(s, 0, set, "", ans);

        return ans;
    }

    public void solve(String s, int index, Set<String> set,
        String sentence, List<String> ans) {

        if (index == s.length()) {

            ans.add(sentence.trim());
            return;
        }

        for (int i = index; i < s.length(); i++) {

            String word = s.substring(index, i + 1);

            if (set.contains(word)) {
                solve(s, i + 1, set, sentence + word + " ", ans);
            }
        }
    }
}