class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        int half = n / 2;

        int sum1 = 0;
        int sum2 = 0;

        int count1 = 0;
        int count2 = 0;

        // First half
        for (int i = 0; i < half; i++) {

            char ch = num.charAt(i);

            if (ch == '?') {
                count1++;
            } else {
                sum1 += ch - '0';
            }
        }

        // Second half
        for (int i = half; i < n; i++) {

            char ch = num.charAt(i);

            if (ch == '?') {
                count2++;
            } else {
                sum2 += ch - '0';
            }
        }

        // Odd number of '?' -> Alice wins
        if ((count1 + count2) % 2 == 1) {
            return true;
        }

        // Bob can force equality only in this exact case
        return 2 * (sum1 - sum2) != 9 * (count2 - count1);
    }
}