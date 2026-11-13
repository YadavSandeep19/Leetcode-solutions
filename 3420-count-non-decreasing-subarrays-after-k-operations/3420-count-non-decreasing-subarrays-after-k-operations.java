
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;

        long ans = 0;
        long cost = 0;

        int right = n - 1;

        // Stores {value, count} in non-increasing order.
        Deque<int[]> dq = new ArrayDeque<>();

        for (int left = n - 1; left >= 0; left--) {

            int num = nums[left];
            int count = 1;

            // Merge smaller values into current value.
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {

                int[] last = dq.pollLast();

                int value = last[0];
                int freq = last[1];

                cost += (long) (num - value) * freq;

                count += freq;
            }

            dq.offerLast(new int[]{num, count});

            // Reduce window until cost <= k.
            while (cost > k) {

                int[] first = dq.peekFirst();

                int value = first[0];

                cost -= value - nums[right];

                right--;

                if (first[1] == 1) {
                    dq.pollFirst();
                } else {
                    first[1]--;
                }
            }

            // All subarrays [left ... right] are valid.
            ans += right - left + 1;
        }

        return ans;
    }
}
