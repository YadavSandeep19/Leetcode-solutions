class Solution {
    public boolean isPalindrome(int x) {

        // Negative numbers are never palindrome
        // Numbers ending with 0 (but not 0 itself) can't be palindrome
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;

        while (x > reversedHalf) {
            int digit = x % 10;
            reversedHalf = reversedHalf * 10 + digit;
            x = x / 10;
        }

        // For even digits: x == reversedHalf
        // For odd digits:  x == reversedHalf / 10
        return x == reversedHalf || x == reversedHalf / 10;
    }
}