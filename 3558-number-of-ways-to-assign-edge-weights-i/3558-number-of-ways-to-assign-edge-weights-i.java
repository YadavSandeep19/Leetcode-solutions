import java.util.*;

class Solution {

    static final long MOD = 1_000_000_007L;

    public int assignEdgeWeights(int[][] edges) {

        int n = edges.length + 1;

        List<Integer>[] g = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];

            g[u].add(v);
            g[v].add(u);
        }

        int maxDepth = dfs(1, 0, g);

        return (int) modPow(2, maxDepth - 1);
    }

    private int dfs(int u, int parent,
                    List<Integer>[] g) {

        int depth = 0;

        for (int v : g[u]) {

            if (v == parent) continue;

            depth = Math.max(depth,
                    1 + dfs(v, u, g));
        }

        return depth;
    }

    private long modPow(long a, long b) {

        long res = 1;

        while (b > 0) {

            if ((b & 1) == 1) {
                res = (res * a) % MOD;
            }

            a = (a * a) % MOD;
            b >>= 1;
        }

        return res;
    }
}