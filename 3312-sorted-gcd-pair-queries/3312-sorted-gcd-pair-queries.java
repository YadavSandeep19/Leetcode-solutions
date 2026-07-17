
class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int mx = 0;
        for (int x : nums) mx = Math.max(mx, x);

        int[] freq = new int[mx + 1];
        for (int x : nums) freq[x]++;

        long[] cntG = new long[mx + 1];

        for (int g = mx; g >= 1; g--) {
            long cnt = 0;
            for (int j = g; j <= mx; j += g) {
                cnt += freq[j];
                cntG[g] -= cntG[j];
            }
            cntG[g] += cnt * (cnt - 1) / 2;
        }

        for (int i = 2; i <= mx; i++) {
            cntG[i] += cntG[i - 1];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = upperBound(cntG, queries[i]);
        }

        return ans;
    }

    private int upperBound(long[] arr, long target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}