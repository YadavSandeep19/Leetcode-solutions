class Solution {
    private List<Integer>[] graph;
    private int[] tin, tout, depth;
    private int[][] up;
    private int timer;
    private int LOG;
    private Fenwick bit;

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        up = new int[LOG][n];

        // Iterative DFS avoids stack overflow on a long tree.
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] stack = new int[n];
        int[] next = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;
        tin[0] = ++timer;

        while (top > 0) {
            int u = stack[top - 1];

            if (next[u] < graph[u].size()) {
                int v = graph[u].get(next[u]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                up[0][v] = u;

                for (int j = 1; j < LOG; j++) {
                    up[j][v] = up[j - 1][up[j - 1][v]];
                }

                tin[v] = ++timer;
                stack[top++] = v;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        for (int j = 1; j < LOG; j++) {
            up[j][0] = 0;
        }

        char[] chars = s.toCharArray();
        bit = new Fenwick(n + 1);

        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');
            bit.update(tin[i], mask);
            bit.update(tout[i] + 1, mask);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta = (1 << (chars[u] - 'a'))
                          ^ (1 << (c - 'a'));

                if (delta != 0) {
                    bit.update(tin[u], delta);
                    bit.update(tout[u] + 1, delta);
                    chars[u] = c;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(u, v);
                int mask = bit.query(tin[u])
                         ^ bit.query(tin[v])
                         ^ (1 << (chars[ancestor] - 'a'));

                // At most one odd-frequency character.
                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) return u;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            for (int i = index; i <= n; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int query(int index) {
            int result = 0;

            for (int i = index; i > 0; i -= i & -i) {
                result ^= tree[i];
            }

            return result;
        }
    }
}
