import java.util.*;

class Solution {

    class BIT {
        int[] bit;

        BIT(int n) {
            bit = new int[n + 2];
        }

        void add(int i, int val) {
            while (i < bit.length) {
                bit[i] += val;
                i += i & -i;
            }
        }

        int query(int i) {
            int ans = 0;
            while (i > 0) {
                ans += bit[i];
                i -= i & -i;
            }
            return ans;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        long[] pref = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int v = (nums[i] == target) ? 1 : -1;
            pref[i + 1] = pref[i] + v;
        }

        long[] all = pref.clone();
        Arrays.sort(all);

        Map<Long, Integer> map = new HashMap<>();

        int idx = 1;
        for (long x : all) {
            if (!map.containsKey(x)) {
                map.put(x, idx++);
            }
        }

        BIT bit = new BIT(idx + 2);

        long ans = 0;

        for (long x : pref) {

            int id = map.get(x);

            ans += bit.query(id - 1);

            bit.add(id, 1);
        }

        return ans;
    }
}