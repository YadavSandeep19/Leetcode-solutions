
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];

        long total = 0;
        int max = 0;
        long k = (long) k1 + k2;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            max = Math.max(max, diff[i]);
        }

        if (total <= k) {
            return 0;
        }

        int left = 0, right = max;

        // Find the smallest possible maximum difference
        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                needed += Math.max(0, d - mid);
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Reduce every difference to at most left
        for (int i = 0; i < n; i++) {
            k -= Math.max(0, diff[i] - left);
            diff[i] = Math.min(diff[i], left);
        }

        // Use leftover operations to reduce remaining values by 1
        for (int i = 0; i < n && k > 0; i++) {
            if (diff[i] == left) {
                diff[i]--;
                k--;
            }
        }

        long ans = 0;

        for (int d : diff) {
            ans += (long) d * d;
        }

        return ans;
    }
}
