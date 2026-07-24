
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n == 1) return 1;

        BitSet pair = new BitSet(2048);
        BitSet ans = new BitSet(2048);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pair.set(nums[i] ^ nums[j]);
            }
        }

        for (int x = pair.nextSetBit(0); x >= 0; x = pair.nextSetBit(x + 1)) {
            for (int num : nums) {
                ans.set(x ^ num);
            }
        }

        return ans.cardinality();
    }
}