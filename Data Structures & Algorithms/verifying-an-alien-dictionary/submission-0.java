class Solution {

    public boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];

        for (int i = 0; i < order.length(); i++) {
            map[order.charAt(i) - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) {

            if (!isSorted(words[i], words[i + 1], map)) {
                return false;
            }
        }

        return true;
    }

    public boolean isSorted(String word1, String word2, int[] map) {

        int len = Math.min(word1.length(), word2.length());

        for (int i = 0; i < len; i++) {

            char ch1 = word1.charAt(i);
            char ch2 = word2.charAt(i);

            if (ch1 != ch2) {
                return map[ch1 - 'a'] < map[ch2 - 'a'];
            }
        }

        return word1.length() <= word2.length();
    }
}