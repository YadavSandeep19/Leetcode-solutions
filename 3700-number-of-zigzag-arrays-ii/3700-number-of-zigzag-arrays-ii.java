class Solution {

    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        int size = 2 * m;

        long[][] T = new long[size][size];

        for (int v = 1; v <= m; v++) {

            int up = v - 1;
            int down = m + v - 1;

            for (int x = 1; x < v; x++) {
                int prevDown = m + x - 1;
                T[up][prevDown] = 1;
            }

            for (int x = v + 1; x <= m; x++) {
                int prevUp = x - 1;
                T[down][prevUp] = 1;
            }
        }

        long[] base = new long[size];

        for (int v = 1; v <= m; v++) {
            base[v - 1] = v - 1;
            base[m + v - 1] = m - v;
        }

        if (n == 2) {
            long ans = 0;
            for (long x : base) {
                ans = (ans + x) % MOD;
            }
            return (int) ans;
        }

        long[][] power = matrixPower(T, n - 2);

        long[] result = multiply(power, base);

        long ans = 0;

        for (long x : result) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] A, long[] v) {

        int n = A.length;

        long[] res = new long[n];

        for (int i = 0; i < n; i++) {

            long sum = 0;

            for (int j = 0; j < n; j++) {
                sum = (sum + A[i][j] * v[j]) % MOD;
            }

            res[i] = sum;
        }

        return res;
    }

    private long[][] matrixPower(long[][] mat, long exp) {

        int n = mat.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {

            if ((exp & 1) == 1) {
                res = multiply(res, mat);
            }

            mat = multiply(mat, mat);

            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {

        int n = A.length;

        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {

            for (int k = 0; k < n; k++) {

                if (A[i][k] == 0) continue;

                long val = A[i][k];

                for (int j = 0; j < n; j++) {

                    if (B[k][j] == 0) continue;

                    C[i][j] =
                        (C[i][j] + val * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }
}