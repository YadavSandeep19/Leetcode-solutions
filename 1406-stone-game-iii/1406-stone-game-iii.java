class Solution {
    private Integer[] dp;

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        dp = new Integer[n];

        int diff = solve(stoneValue, 0);

        if (diff > 0) return "Alice";
        if (diff < 0) return "Bob";
        return "Tie";
    }

    private int solve(int[] stoneValue, int idx) {
        if (idx >= stoneValue.length) {
            return 0;
        }

        if (dp[idx] != null) {
            return dp[idx];
        }

        int take = 0;
        int best = Integer.MIN_VALUE;

        for (int k = 0; k < 3 && idx + k < stoneValue.length; k++) {
            take += stoneValue[idx + k];
            best = Math.max(best, take - solve(stoneValue, idx + k + 1));
        }

        return dp[idx] = best;
    }
}