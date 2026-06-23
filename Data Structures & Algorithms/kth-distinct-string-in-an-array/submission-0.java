class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> freq = new HashMap<>();

        for (String str : arr) {
            freq.put(str, freq.getOrDefault(str, 0) + 1);
        }

        for (String str : arr) {
            if (freq.get(str) == 1) {
                k--;
                if (k == 0) {
                    return str;
                }
            }
        }

        return "";
    }
}