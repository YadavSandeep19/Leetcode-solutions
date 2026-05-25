class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;

        int count = 0; // number of reachable positions in current window

        for (int i = 1; i < n; i++) {
            if (i - minJump >= 0 && dp[i - minJump]) {
                count++;
            }
            if (i - maxJump - 1 >= 0 && dp[i - maxJump - 1]) {
                count--;
            }

            dp[i] = (s.charAt(i) == '0' && count > 0);
        }

        return dp[n - 1];
    }
}