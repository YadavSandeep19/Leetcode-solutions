class Solution {
    public int distinctSubseqII(String s) {

        int MOD = 1000000007;
        int[] dp = new int[26];

        for (char ch : s.toCharArray()) {

            int total = 0;

            // All existing subsequences
            for (int x : dp) {
                total = (total + x) % MOD;
            }

            // New subsequences ending with ch
            dp[ch - 'a'] = (total + 1) % MOD;
        }

        int ans = 0;

        for (int x : dp) {
            ans = (ans + x) % MOD;
        }

        return ans;
    }
}