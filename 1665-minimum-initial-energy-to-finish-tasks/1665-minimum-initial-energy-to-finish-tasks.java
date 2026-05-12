class Solution {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));

        int ans = 0;
        int cur = 0;

        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            if (cur < minimum) {
                ans += minimum - cur;
                cur = minimum;
            }

            cur -= actual;
        }

        return ans;
    }
}