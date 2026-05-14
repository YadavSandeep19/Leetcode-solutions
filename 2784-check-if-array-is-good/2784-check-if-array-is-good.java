class Solution {
    public boolean isGood(int[] nums) {
        int n = nums.length - 1;

        int[] freq = new int[n + 1];

        for (int x : nums) {
            if (x < 1 || x > n) return false;
            freq[x]++;
        }

        for (int i = 1; i < n; i++) {
            if (freq[i] != 1) return false;
        }

        return freq[n] == 2;
    }
}