
class Solution {
    public int lengthOfLIS(int[] nums) {
        List<Integer> lis = new ArrayList<>();

        for (int num : nums) {
            int index = Collections.binarySearch(lis, num);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == lis.size()) {
                lis.add(num);
            } else {
                lis.set(index, num);
            }
        }

        return lis.size();
    }
}