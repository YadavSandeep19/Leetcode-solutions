
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        long[] key = new long[n];
        for (int i = 0; i < n; i++) key[i] = ((long) nums[i] << 32) | i;
        Arrays.sort(key);

        int[] res = new int[n];
        int[] idx = new int[n];
        int start = 0;

        for (int i = 1; i <= n; i++) {
            if (i == n || (int) (key[i] >>> 32) - (int) (key[i - 1] >>> 32) > limit) {
                int len = i - start;
                for (int k = 0; k < len; k++) idx[k] = (int) key[start + k];
                Arrays.sort(idx, 0, len);
                for (int k = 0; k < len; k++) res[idx[k]] = (int) (key[start + k] >>> 32);
                start = i;
            }
        }
        return res;
    }
}