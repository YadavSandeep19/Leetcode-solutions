class Solution {

    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;

        dp = new int[n][n + 1];
        suffix = new int[n + 1];

        // Suffix sum
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }

        return solve(piles, 0, 1);
    }

    private int solve(int[] piles, int i, int m) {

        if (i >= piles.length) {
            return 0;
        }

        if (dp[i][m] != 0) {
            return dp[i][m];
        }

        int n = piles.length;

        // Can take all remaining stones
        if (i + 2 * m >= n) {
            return dp[i][m] = suffix[i];
        }

        int opponent = Integer.MAX_VALUE;

        int taken = 0;

        for (int x = 1; x <= 2 * m; x++) {

            taken += piles[i + x - 1];

            int next = solve(piles, i + x, Math.max(m, x));

            opponent = Math.min(opponent, next);
        }

        // Current player gets all remaining stones
        // minus what opponent can force us to leave
        return dp[i][m] = suffix[i] - opponent;
    }
}