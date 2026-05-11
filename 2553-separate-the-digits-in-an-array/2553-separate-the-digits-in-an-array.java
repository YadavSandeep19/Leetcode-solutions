class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            Stack<Integer> stack = new Stack<>();

            // digits ko ulta collect karo
            while (num > 0) {
                stack.push(num % 10);
                num /= 10;
            }

            // original order me add karo
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }
}