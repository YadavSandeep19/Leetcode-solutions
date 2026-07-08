class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        int[] digitPrefix = new int[n + 1];
        int[] nonZeroPrefix = new int[n + 1];
        long[] concatPrefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';

            digitPrefix[i + 1] = digitPrefix[i] + d;
            nonZeroPrefix[i + 1] = nonZeroPrefix[i];
            concatPrefix[i + 1] = concatPrefix[i];

            if (d != 0) {
                nonZeroPrefix[i + 1]++;
                concatPrefix[i + 1] =
                        (concatPrefix[i + 1] * 10 + d) % MOD;
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int digitSum = digitPrefix[r + 1] - digitPrefix[l];
            int cnt = nonZeroPrefix[r + 1] - nonZeroPrefix[l];

            long x = (concatPrefix[r + 1]
                    - concatPrefix[l] * pow10[cnt] % MOD + MOD) % MOD;

            ans[i] = (int) (x * digitSum % MOD);
        }

        return ans;
    }
}