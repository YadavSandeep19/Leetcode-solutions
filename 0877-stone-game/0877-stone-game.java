// class Solution {
//     public boolean stoneGame(int[] piles) {
//         return true;
//     }
// }

class Solution {
    private Integer[][] dp;

    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        dp = new Integer[n][n];
        return solve(piles, 0, n - 1) > 0;
    }

    private int solve(int[] piles, int left, int right) {
        if (left == right) {
            return piles[left];
        }

        if (dp[left][right] != null) {
            return dp[left][right];
        }

        int pickLeft = piles[left] - solve(piles, left + 1, right);
        int pickRight = piles[right] - solve(piles, left, right - 1);

        return dp[left][right] = Math.max(pickLeft, pickRight);
    }
}