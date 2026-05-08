class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                int temp = nums[i + 1];
                nums[i + 1] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        return i + 1;
    }
}

// class Solution {
//     public int removeDuplicates(int[] nums) {
//         if(nums.length == 0) return 0;
//         int start= 0;
//         int temp= 1;
//         while(temp<nums.length){
//             if(nums[temp] == nums[temp - 1]){
//                 temp++;
//                 continue;
//             }
//            nums[start + 1] = nums[temp];
//             start++;
//             temp++;
//         }
//         return start + 1;
//     }
// }









