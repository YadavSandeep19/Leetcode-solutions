
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // dp[i][j][0] = paths entering from left (moving right)
        // dp[i][j][1] = paths entering from top (moving down)
        int[][][] dp = new int[m][n][2];

        // Starting cell
        dp[0][0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int d = 0; d < 2; d++) {

                    int ways = dp[i][j][d];

                    if (ways == 0) continue;

                    if (grid[i][j] == 0) {

                        // Move right
                        if (j + 1 < n) {
                            dp[i][j + 1][0] =
                                (int)((dp[i][j + 1][0] + (long)ways) % MOD);
                        }

                        // Move down
                        if (i + 1 < m) {
                            dp[i + 1][j][1] =
                                (int)((dp[i + 1][j][1] + (long)ways) % MOD);
                        }

                    } else {

                        // Mirror reflection
                        if (d == 0) {
                            // Entered from left, turn down
                            if (i + 1 < m) {
                                dp[i + 1][j][1] =
                                    (int)((dp[i + 1][j][1] + (long)ways) % MOD);
                            }
                        } else {
                            // Entered from top, turn right
                            if (j + 1 < n) {
                                dp[i][j + 1][0] =
                                    (int)((dp[i][j + 1][0] + (long)ways) % MOD);
                            }
                        }
                    }
                }
            }
        }

        return (int)(((long)dp[m - 1][n - 1][0]
                    + dp[m - 1][n - 1][1]) % MOD);
    }
}
