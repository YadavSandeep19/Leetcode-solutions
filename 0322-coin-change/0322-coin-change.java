// class Solution {
//     public int solve(int[] coins, int amount, int i) {
//         if (amount == 0)
//             return 0;
//         if (i == 0)
//             return Integer.MAX_VALUE;
//         int take = (int)1e9;
//         if (coins[i - 1] <= amount)
//             take = 1 + solve(coins, amount - coins[i - 1], i);
//         int skip = solve(coins, amount, i - 1);
//         return Math.min(take, skip);
//     }

//     public int coinChange(int[] coins, int amount) {
//         int ans = solve(coins, amount, coins.length);
//         return (ans >= (int)1e9) ? -1 : ans;
//     }
// }

class Solution {
    int[][] dp;
    static final int INF = (int) 1e9;
    public int solve(int[] coins, int amount, int i) {
        if (amount == 0)
            return 0;
        if (i == 0)
            return INF;
        if (dp[i][amount] != -1)
            return dp[i][amount];
        int skip = solve(coins, amount, i - 1);
        int take = INF;
        if (coins[i - 1] <= amount) {
            take = 1 + solve(coins, amount - coins[i - 1], i);
        }
        dp[i][amount] = Math.min(take, skip);
        return dp[i][amount];
    }
    public int coinChange(int[] coins, int amount) {
        dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                dp[i][j] = -1;
            }
        }
        int ans = solve(coins, amount, coins.length);
        return (ans >= INF) ? -1 : ans;
    }
}