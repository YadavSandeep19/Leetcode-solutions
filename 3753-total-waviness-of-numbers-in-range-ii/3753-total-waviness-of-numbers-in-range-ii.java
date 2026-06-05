
class Solution {

    static class Pair {
        long cnt;
        long wav;

        Pair(long cnt, long wav) {
            this.cnt = cnt;
            this.wav = wav;
        }
    }

    private char[] digits;
    private Pair[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long x) {
        if (x <= 0) return 0;

        digits = String.valueOf(x).toCharArray();

        int n = digits.length;

        memo = new Pair[n][11][11][2][2];

        return dfs(0, 10, 10, 1, 1).wav;
    }

    private Pair dfs(int pos,
                     int prev1,
                     int prev2,
                     int tight,
                     int started) {

        if (pos == digits.length) {
            return new Pair(1, 0);
        }

        if (memo[pos][prev1][prev2][tight][started] != null) {
            return memo[pos][prev1][prev2][tight][started];
        }

        int limit = tight == 1 ? digits[pos] - '0' : 9;

        long totalCnt = 0;
        long totalWav = 0;

        for (int d = 0; d <= limit; d++) {

            int ntight =
                    (tight == 1 && d == limit) ? 1 : 0;

            if (started == 1 && d == 0) {

                Pair next =
                        dfs(pos + 1,
                                10,
                                10,
                                ntight,
                                1);

                totalCnt += next.cnt;
                totalWav += next.wav;
            } else {

                Pair next;

                int add = 0;

                if (prev2 != 10) {

                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {

                        add = 1;
                    }
                }

                next =
                        dfs(pos + 1,
                                d,
                                prev1,
                                ntight,
                                0);

                totalCnt += next.cnt;

                totalWav +=
                        next.wav +
                        (long) add * next.cnt;
            }
        }

        return memo[pos][prev1][prev2][tight][started] =
                new Pair(totalCnt, totalWav);
    }
}