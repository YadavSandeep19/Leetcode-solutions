class Solution {
    public int totalWaviness(int num1, int num2) {
        int ans = 0;

        for (int x = num1; x <= num2; x++) {
            ans += waviness(x);
        }

        return ans;
    }

    private int waviness(int n) {
        String s = String.valueOf(n);

        if (s.length() < 3) return 0;

        int cnt = 0;

        for (int i = 1; i < s.length() - 1; i++) {
            char left = s.charAt(i - 1);
            char mid = s.charAt(i);
            char right = s.charAt(i + 1);

            if ((mid > left && mid > right) ||
                (mid < left && mid < right)) {
                cnt++;
            }
        }

        return cnt;
    }
}