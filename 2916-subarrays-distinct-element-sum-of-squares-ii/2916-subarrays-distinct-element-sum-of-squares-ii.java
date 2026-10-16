class Solution {
    long[] sum;
    int[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        final long MOD = 1_000_000_007L;
        sum = new long[4 * n];
        lazy = new int[4 * n];
        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long S = 0, ans = 0;
        for (int j = 0; j < n; j++) {
            int l = last[nums[j]] + 1;
            S = (S + 2 * query(1, 0, n - 1, l, j) + (j - l + 1)) % MOD;
            update(1, 0, n - 1, l, j);
            last[nums[j]] = j;
            ans = (ans + S) % MOD;
        }
        return (int) ans;
    }

    void push(int node, int lo, int hi) {
        if (lazy[node] != 0) {
            int mid = (lo + hi) >> 1;
            int L = node << 1, R = L | 1;
            sum[L] += (long) lazy[node] * (mid - lo + 1);
            lazy[L] += lazy[node];
            sum[R] += (long) lazy[node] * (hi - mid);
            lazy[R] += lazy[node];
            lazy[node] = 0;
        }
    }

    void update(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return;
        if (l <= lo && hi <= r) {
            sum[node] += hi - lo + 1;
            lazy[node]++;
            return;
        }
        push(node, lo, hi);
        int mid = (lo + hi) >> 1;
        update(node << 1, lo, mid, l, r);
        update(node << 1 | 1, mid + 1, hi, l, r);
        sum[node] = sum[node << 1] + sum[node << 1 | 1];
    }

    long query(int node, int lo, int hi, int l, int r) {
        if (r < lo || hi < l) return 0;
        if (l <= lo && hi <= r) return sum[node];
        push(node, lo, hi);
        int mid = (lo + hi) >> 1;
        return query(node << 1, lo, mid, l, r) + query(node << 1 | 1, mid + 1, hi, l, r);
    }
}