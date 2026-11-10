
class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE / 2);

        // Starting point
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            // Jump from previous 1, 2 or 3 steps
            for (int j = Math.max(0, i - 3); j < i; j++) {

                int jump = i - j;

                int currentCost = costs[i - 1] + jump * jump;

                dp[i] = Math.min(
                    dp[i],
                    dp[j] + currentCost
                );
            }
        }

        return dp[n];
    }
}
