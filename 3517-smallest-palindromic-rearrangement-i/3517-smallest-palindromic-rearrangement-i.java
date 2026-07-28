class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        StringBuilder firstHalf = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < cnt[i] / 2; j++) {
                firstHalf.append((char) ('a' + i));
            }
            if ((cnt[i] & 1) == 1) {
                middle = (char) ('a' + i);
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(firstHalf);

        if (middle != 0) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(firstHalf).reverse());

        return ans.toString();
    }
}