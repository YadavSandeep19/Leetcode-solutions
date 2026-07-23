class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n < 3) {
            return n;
        }

        int msb = 31 - Integer.numberOfLeadingZeros(n);
        return 1 << (msb + 1);
    }
}