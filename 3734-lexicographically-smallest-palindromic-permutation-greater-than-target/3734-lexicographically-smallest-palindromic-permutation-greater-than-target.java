class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) cnt[s.charAt(i) - 'a']++;

        int oddIdx = -1, odds = 0;
        for (int c = 0; c < 26; c++) {
            if ((cnt[c] & 1) != 0) { odds++; oddIdx = c; }
        }
        if (odds > 1) return "";

        int m = n / 2;
        int[] half = new int[26];
        for (int c = 0; c < 26; c++) half[c] = cnt[c] >> 1;
        char mid = oddIdx >= 0 ? (char) ('a' + oddIdx) : 0;
        char[] t = target.toCharArray();

        int[] pre = new int[26];
        for (int i = 0; i < m; i++) pre[t[i] - 'a']++;
        boolean exact = true;
        for (int c = 0; c < 26; c++) {
            if (pre[c] != half[c]) { exact = false; break; }
        }
        if (exact) {
            char[] p = build(t, m, mid, n);
            if (isGreater(p, t)) return new String(p);
        }

        if (m == 0) return "";

        int[] rem = half.clone();
        int L = m;
        for (int i = 0; i < m; i++) {
            int c = t[i] - 'a';
            if (rem[c] == 0) { L = i; break; }
            rem[c]--;
        }
        int start = Math.min(L, m - 1);
        if (L == m) rem[t[m - 1] - 'a']++;

        int maxAvail = -1;
        for (int c = 25; c >= 0; c--) {
            if (rem[c] > 0) { maxAvail = c; break; }
        }

        for (int i = start; i >= 0; i--) {
            int cur = t[i] - 'a';
            if (maxAvail > cur) {
                int pick = cur + 1;
                while (rem[pick] == 0) pick++;
                rem[pick]--;

                char[] h = new char[m];
                for (int k = 0; k < i; k++) h[k] = t[k];
                h[i] = (char) ('a' + pick);
                int idx = i + 1;
                for (int c = 0; c < 26; c++) {
                    for (int k = 0; k < rem[c]; k++) h[idx++] = (char) ('a' + c);
                }
                return new String(build(h, m, mid, n));
            }
            if (i > 0) {
                int back = t[i - 1] - 'a';
                rem[back]++;
                if (back > maxAvail) maxAvail = back;
            }
        }
        return "";
    }

    private char[] build(char[] h, int m, char mid, int n) {
        char[] p = new char[n];
        for (int i = 0; i < m; i++) {
            p[i] = h[i];
            p[n - 1 - i] = h[i];
        }
        if ((n & 1) == 1) p[m] = mid;
        return p;
    }

    private boolean isGreater(char[] p, char[] t) {
        for (int i = 0; i < p.length; i++) {
            if (p[i] != t[i]) return p[i] > t[i];
        }
        return false;
    }
}