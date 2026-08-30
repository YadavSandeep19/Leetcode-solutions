class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int mn = 0, mx = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[mn]) mn = i;
            if (nums[i] > nums[mx]) mx = i;
        }
        int i = Math.min(mn, mx), j = Math.max(mn, mx);
        int frontBoth = j + 1;
        int backBoth = n - i;
        int split = (i + 1) + (n - j);
        return Math.min(frontBoth, Math.min(backBoth, split));
    }
}