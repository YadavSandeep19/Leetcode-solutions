class Solution {
    public char processStr(String s, long k) {

        long len = 0;

        for (char ch : s.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                len++;
            } else if (ch == '*') {
                if (len > 0) len--;
            } else if (ch == '#') {
                len = Math.min((long)1e18, len * 2);
            }
        }

        if (k >= len) {
            return '.';
        }

        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {

                if (k == len - 1) {
                    return ch;
                }

                len--;
            }

            else if (ch == '*') {

                len++;

                if (k == len - 1) {
                    return '.';
                }
            }

            else if (ch == '#') {

                long half = len / 2;

                if (k >= half) {
                    k -= half;
                }

                len = half;
            }

            else if (ch == '%') {

                k = len - 1 - k;
            }
        }

        return '.';
    }
}