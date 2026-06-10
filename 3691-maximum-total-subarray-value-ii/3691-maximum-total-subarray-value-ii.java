import java.util.*;

class Solution {

    int[][] mx;
    int[][] mn;
    int[] log;

    public long maxTotalValue(int[] nums, int k) {

        int n = nums.length;

        buildSparseTables(nums);

        PriorityQueue<Node> pq =
                new PriorityQueue<>((a, b) ->
                        Long.compare(b.val, a.val));

        for (int l = 0; l < n; l++) {
            long val = rangeMax(l, n - 1)
                    - rangeMin(l, n - 1);

            pq.offer(new Node(val, l, n - 1));
        }

        long ans = 0;

        while (k-- > 0) {

            Node cur = pq.poll();

            ans += cur.val;

            if (cur.r > cur.l) {

                int nr = cur.r - 1;

                long val = rangeMax(cur.l, nr)
                        - rangeMin(cur.l, nr);

                pq.offer(new Node(val, cur.l, nr));
            }
        }

        return ans;
    }

    private void buildSparseTables(int[] nums) {

        int n = nums.length;

        log = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        int K = log[n] + 1;

        mx = new int[K][n];
        mn = new int[K][n];

        for (int i = 0; i < n; i++) {
            mx[0][i] = nums[i];
            mn[0][i] = nums[i];
        }

        for (int k = 1; k < K; k++) {

            for (int i = 0;
                 i + (1 << k) <= n;
                 i++) {

                mx[k][i] =
                        Math.max(mx[k - 1][i],
                                mx[k - 1][i + (1 << (k - 1))]);

                mn[k][i] =
                        Math.min(mn[k - 1][i],
                                mn[k - 1][i + (1 << (k - 1))]);
            }
        }
    }

    private int rangeMax(int l, int r) {

        int len = r - l + 1;
        int k = log[len];

        return Math.max(
                mx[k][l],
                mx[k][r - (1 << k) + 1]
        );
    }

    private int rangeMin(int l, int r) {

        int len = r - l + 1;
        int k = log[len];

        return Math.min(
                mn[k][l],
                mn[k][r - (1 << k) + 1]
        );
    }

    static class Node {

        long val;
        int l;
        int r;

        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }
}