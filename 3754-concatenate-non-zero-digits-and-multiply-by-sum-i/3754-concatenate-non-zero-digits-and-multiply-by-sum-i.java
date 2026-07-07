class Solution {
    public long sumAndMultiply(int n) {

        long x = 0;
        long sum = 0;

        while (n > 0) {
            int digit = n % 10;

            if (digit != 0) {
                x = digit + x * 10;
                sum += digit;
            }

            n /= 10;
        }

        // Reverse x because digits were built from right to left
        long rev = 0;
        while (x > 0) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }

        return rev * sum;
    }
}