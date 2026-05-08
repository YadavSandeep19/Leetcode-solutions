

class Solution {

    static class IntList {
        int[] a = new int[4];
        int size = 0;

        void add(int x) {
            if (size == a.length) a = Arrays.copyOf(a, a.length * 2);
            a[size++] = x;
        }
    }

    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;

        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);

        int[] spf = buildSPF(maxVal);

        IntList[] buckets = new IntList[maxVal + 1];

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (x > 1) {
                int p = spf[x];
                if (buckets[p] == null) buckets[p] = new IntList();
                buckets[p].add(i);
                while (x % p == 0) x /= p;
            }
        }

        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        int[] q = new int[n];
        int head = 0, tail = 0;

        boolean[] usedPrime = new boolean[maxVal + 1];

        q[tail++] = 0;
        dist[0] = 0;

        while (head < tail) {
            int i = q[head++];
            int d = dist[i];

            if (i == n - 1) return d;

            if (i - 1 >= 0 && dist[i - 1] == -1) {
                dist[i - 1] = d + 1;
                q[tail++] = i - 1;
            }
            if (i + 1 < n && dist[i + 1] == -1) {
                dist[i + 1] = d + 1;
                q[tail++] = i + 1;
            }

            int val = nums[i];
            if (val <= maxVal && spf[val] == val && !usedPrime[val]) {
                usedPrime[val] = true;

                IntList list = buckets[val];
                if (list != null) {
                    for (int k = 0; k < list.size; k++) {
                        int j = list.a[k];
                        if (dist[j] == -1) {
                            dist[j] = d + 1;
                            q[tail++] = j;
                        }
                    }
                    buckets[val] = null; // important: process once
                }
            }
        }

        return -1;
    }

    private int[] buildSPF(int maxVal) {
        int[] spf = new int[maxVal + 1];
        int[] primes = new int[maxVal + 1];
        int pc = 0;

        for (int i = 2; i <= maxVal; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                primes[pc++] = i;
            }
            for (int j = 0; j < pc; j++) {
                int p = primes[j];
                long v = 1L * i * p;
                if (v > maxVal || p > spf[i]) break;
                spf[(int) v] = p;
            }
        }
        return spf;
    }
}