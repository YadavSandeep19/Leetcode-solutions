
class Solution {
    public int myAtoi(String s) {

        int i = 0;
        int n = s.length();

        // Step 1: Ignore leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Check sign
        int sign = 1;

        if (i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        } else if (i < n && s.charAt(i) == '+') {
            i++;
        }

        // Step 3: Convert digits
        int result = 0;

        while (i < n) {

            char ch = s.charAt(i);

            if (ch < '0' || ch > '9') {
                break;
            }

            int digit = ch - '0';

            // Step 4: Check overflow
            if (result > Integer.MAX_VALUE / 10 ||
                (result == Integer.MAX_VALUE / 10 && digit > 7)) {

                return sign == 1
                    ? Integer.MAX_VALUE
                    : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;

            i++;
        }

        return result * sign;
    }
}
