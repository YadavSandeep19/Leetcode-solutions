class Solution {
    public int numberOfSpecialChars(String word) {

        int[] lowerLast = new int[26];
        int[] upperFirst = new int[26];

        // initialize
        for (int i = 0; i < 26; i++) {
            lowerLast[i] = -1;
            upperFirst[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);

            if (Character.isLowerCase(c)) {
                lowerLast[c - 'a'] = i;
            } else {
                upperFirst[c - 'A'] = Math.min(upperFirst[c - 'A'], i);
            }
        }

        int ans = 0;

        for (int i = 0; i < 26; i++) {

            if (lowerLast[i] != -1 &&
                upperFirst[i] != Integer.MAX_VALUE &&
                lowerLast[i] < upperFirst[i]) {

                ans++;
            }
        }

        return ans;
    }
}