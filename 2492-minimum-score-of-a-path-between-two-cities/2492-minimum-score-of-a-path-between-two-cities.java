
class Solution {

    int ans = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] r : roads) {
            int u = r[0];
            int v = r[1];
            int w = r[2];

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        boolean[] vis = new boolean[n + 1];

        dfs(1, graph, vis);

        return ans;
    }

    private void dfs(int u,
                     List<int[]>[] graph,
                     boolean[] vis) {

        vis[u] = true;

        for (int[] e : graph[u]) {

            int v = e[0];
            int w = e[1];

            ans = Math.min(ans, w);

            if (!vis[v]) {
                dfs(v, graph, vis);
            }
        }
    }
}