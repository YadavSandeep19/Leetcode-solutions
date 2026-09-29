class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][m + n];

        // Starting cell
        if (grid[0][0] == '(') {
            dp[0][0][1] = true;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                for (int balance = 0; balance < m + n; balance++) {

                    if (!dp[i][j][balance]) {
                        continue;
                    }

                    // Move down
                    if (i + 1 < m) {
                        if (grid[i + 1][j] == '(') {
                            dp[i + 1][j][balance + 1] = true;
                        } else if (balance > 0) {
                            dp[i + 1][j][balance - 1] = true;
                        }
                    }

                    // Move right
                    if (j + 1 < n) {
                        if (grid[i][j + 1] == '(') {
                            dp[i][j + 1][balance + 1] = true;
                        } else if (balance > 0) {
                            dp[i][j + 1][balance - 1] = true;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}