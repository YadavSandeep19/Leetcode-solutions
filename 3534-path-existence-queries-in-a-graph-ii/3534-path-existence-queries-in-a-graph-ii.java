
class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] ord = new Integer[n];
        for (int i = 0; i < n; i++) ord[i] = i;

        Arrays.sort(ord, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[ord[i]] = i;

        int[] comp = new int[n];
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (nums[ord[i]] - nums[ord[i - 1]] <= maxDiff)
                comp[i] = comp[i - 1];
            else
                comp[i] = comp[i - 1] + 1;
        }

        int LOG = 18;
        int[][] up = new int[LOG][n];

        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r + 1 < n && nums[ord[r + 1]] - nums[ord[l]] <= maxDiff)
                r++;
            up[0][l] = r;
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = pos[queries[i][0]];
            int v = pos[queries[i][1]];

            if (u > v) {
                int t = u;
                u = v;
                v = t;
            }

            if (comp[u] != comp[v]) {
                ans[i] = -1;
                continue;
            }

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int cur = u;
            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < v) {
                    cur = up[k][cur];
                    steps += 1 << k;
                }
            }

            ans[i] = steps + 1;
        }

        return ans;
    }
}