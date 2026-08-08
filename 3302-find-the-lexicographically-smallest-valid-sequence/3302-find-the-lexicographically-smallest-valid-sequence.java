
class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        Arrays.fill(last, -1);

        // last[j] = earliest/last useful position in word1
        // from which word2[j...] can be matched exactly.
        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        int[] ans = new int[m];

        // We are allowed at most one mismatch.
        boolean usedMismatch = false;
        j = 0;

        for (i = 0; i < n && j < m; i++) {

            // Exact match
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            }

            // Use this position as the one mismatch
            else if (!usedMismatch &&
                    (j == m - 1 || (last[j + 1] != -1 && i < last[j + 1]))) {

                ans[j] = i;
                j++;
                usedMismatch = true;
            }
        }

        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}