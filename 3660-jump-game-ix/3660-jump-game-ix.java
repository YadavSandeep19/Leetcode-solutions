class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int[] preMax = new int[n];

        preMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preMax[i] = Math.max(preMax[i - 1], nums[i]);
        }

        ans[n - 1] = preMax[n - 1];   // last index can reach max in array
        int rightMin = Integer.MAX_VALUE;

        for (int i = n - 2; i >= 0; i--) {
            if (preMax[i] > rightMin) {
                ans[i] = ans[i + 1];
            } else {
                ans[i] = preMax[i];
            }
            rightMin = Math.min(rightMin, nums[i + 1]);
        }

        return ans;
    }
}