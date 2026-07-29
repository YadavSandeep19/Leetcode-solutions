class Solution {
    private static final long LIMIT = 1_000_001L;

    private long comb(int n, int r) {
        if (r > n) return 0;
        r = Math.min(r, n - r);

        long ans = 1;
        for (int i = 1; i <= r; i++) {
            ans = ans * (n - i + 1) / i;
            if (ans >= LIMIT) return LIMIT;
        }
        return ans;
    }

    private long countWays(int[] cnt) {
        int rem = 0;
        for (int x : cnt) rem += x;

        long ans = 1;
        int cur = rem;

        for (int x : cnt) {
            if (x == 0) continue;

            ans *= comb(cur, x);
            if (ans >= LIMIT) return LIMIT;

            cur -= x;
        }
        return ans;
    }

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        int[] half = new int[26];
        StringBuilder mid = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            if ((freq[i] & 1) == 1) {
                mid.append((char) ('a' + i));
            }
        }

        if (countWays(half) < k) return "";

        int len = 0;
        for (int x : half) len += x;

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        String right = left.reverse().toString();
        left.reverse();

        return left.toString() + mid.toString() + right;
    }
}