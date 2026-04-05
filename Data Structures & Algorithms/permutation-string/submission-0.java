class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        int[] count = new int[26];

        for(char c : s1.toCharArray()){
            count[c - 'a']++;
        }

        int left = 0;

        for(int right = 0; right < s2.length(); right++){
            char ch = s2.charAt(right);
            count[ch - 'a']--;

            while(count[ch - 'a'] < 0){
                count[s2.charAt(left) - 'a']++;
                left++;
            }

            if(right - left + 1 == s1.length()){
                return true;
            }
        }

        return false;
    }
}