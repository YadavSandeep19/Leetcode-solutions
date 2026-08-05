
class Solution {
    private boolean[] suspicious;
    private boolean[] vis;
    private List<Integer>[] g;
    private List<Integer>[] ug;

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        suspicious = new boolean[n];
        vis = new boolean[n];
        g = new ArrayList[n];
        ug = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            ug[i] = new ArrayList<>();
        }

        for (int[] e : invocations) {
            int a = e[0], b = e[1];
            g[a].add(b);
            ug[a].add(b);
            ug[b].add(a);
        }

        dfs(k);

        for (int i = 0; i < n; i++) {
            if (!suspicious[i] && !vis[i]) {
                dfs2(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }

    private void dfs(int u) {
        suspicious[u] = true;
        for (int v : g[u]) {
            if (!suspicious[v]) {
                dfs(v);
            }
        }
    }

    private void dfs2(int u) {
        vis[u] = true;
        for (int v : ug[u]) {
            if (!vis[v]) {
                suspicious[v] = false;
                dfs2(v);
            }
        }
    }
}