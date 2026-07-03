
class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] g = new ArrayList[n];
        int[] indeg = new int[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        int maxW = 0;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            g[u].add(new int[]{v, w});
            indeg[v]++;
            maxW = Math.max(maxW, w);
        }

        int[] topo = new int[n];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indeg[i] == 0) {
                q.offer(i);
            }
        }

        int idx = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (int[] e : g[u]) {
                int v = e[0];
                if (--indeg[v] == 0) {
                    q.offer(v);
                }
            }
        }

        int lo = 0, hi = maxW, ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (can(mid, g, topo, online, k)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int score,
                        List<int[]>[] g,
                        int[] topo,
                        boolean[] online,
                        long k) {

        int n = online.length;

        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {

            if (dist[u] == INF) continue;

            if (u != 0 && u != n - 1 && !online[u]) {
                continue;
            }

            for (int[] e : g[u]) {

                int v = e[0];
                int w = e[1];

                if (w < score) continue;

                if (v != n - 1 && !online[v]) {
                    continue;
                }

                long nd = dist[u] + w;

                if (nd < dist[v]) {
                    dist[v] = nd;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}