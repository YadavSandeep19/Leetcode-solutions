class Solution {

    int[][] dp;
    int[] prefix;

    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        dp = new int[n][n];
        prefix = new int[n + 1];

        // Prefix Sum
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        return solve(stoneValue, 0, n - 1);
    }

    private int solve(int[] stoneValue, int l, int r) {

        // Only one stone -> cannot split
        if (l >= r) {
            return 0;
        }

        if (dp[l][r] != 0) {
            return dp[l][r];
        }

        int ans = 0;

        for (int k = l; k < r; k++) {

            // Sum of left part [l ... k]
            int left = prefix[k + 1] - prefix[l];

            // Sum of right part [k + 1 ... r]
            int right = prefix[r + 1] - prefix[k + 1];

            if (left < right) {

                ans = Math.max(
                    ans,
                    left + solve(stoneValue, l, k)
                );

            } else if (left > right) {

                ans = Math.max(
                    ans,
                    right + solve(stoneValue, k + 1, r)
                );

            } else {

                ans = Math.max(
                    ans,
                    Math.max(
                        left + solve(stoneValue, l, k),
                        right + solve(stoneValue, k + 1, r)
                    )
                );
            }
        }

        return dp[l][r] = ans;
    }
}