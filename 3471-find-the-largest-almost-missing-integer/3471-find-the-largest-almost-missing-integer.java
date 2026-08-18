
class Solution {
    public int largestInteger(int[] nums, int k) {

        int n = nums.length;

        // Case 1: k == n
        // Pura array hi ek subarray hai.
        if (k == n) {
            int max = 0;

            for (int num : nums) {
                max = Math.max(max, num);
            }

            return max;
        }

        int[] freq = new int[51];

        for (int num : nums) {
            freq[num]++;
        }

        // Case 2: k == 1
        // Har element khud ek subarray hai.
        // Isliye sirf globally unique elements valid hain.
        if (k == 1) {
            int ans = -1;

            for (int num : nums) {
                if (freq[num] == 1) {
                    ans = Math.max(ans, num);
                }
            }

            return ans;
        }

        // Case 3: 1 < k < n
        // Sirf first aur last element hi
        // exactly one k-sized subarray mein aa sakte hain.
        int ans = -1;

        if (freq[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }

        if (freq[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}