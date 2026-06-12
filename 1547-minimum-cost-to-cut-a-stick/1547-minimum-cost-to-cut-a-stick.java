import java.util.*;

class Solution {
    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);

        int[] arr = new int[cuts.length + 2];
        arr[0] = 0;
        arr[arr.length - 1] = n;

        for (int i = 0; i < cuts.length; i++) {
            arr[i + 1] = cuts[i];
        }

        int[][] dp = new int[arr.length][arr.length];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solve(0, arr.length - 1, arr, dp);
    }

    private int solve(int left, int right, int[] arr, int[][] dp) {
        if (right - left <= 1) {
            return 0;
        }

        if (dp[left][right] != -1) {
            return dp[left][right];
        }

        int minCost = Integer.MAX_VALUE;

        for (int k = left + 1; k < right; k++) {
            int cost = arr[right] - arr[left]
                    + solve(left, k, arr, dp)
                    + solve(k, right, arr, dp);

            minCost = Math.min(minCost, cost);
        }

        return dp[left][right] = minCost;
    }
}