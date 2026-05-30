
class Solution {

    public List<Boolean> getResults(int[][] queries) {

        int MAX = 50000;

        TreeSet<Integer> obstacles = new TreeSet<>();
        obstacles.add(0);
        obstacles.add(MAX);

        int[] seg = new int[4 * (MAX + 1)];

        build(seg, 1, 0, MAX);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int x = q[1];

                Integer right = obstacles.ceiling(x);
                Integer left = obstacles.floor(x);

                obstacles.add(x);

                update(seg, 1, 0, MAX, right, right - x);
                update(seg, 1, 0, MAX, x, x - left);

            } else {

                int x = q[1];
                int sz = q[2];

                Integer left = obstacles.floor(x);

                int best =
                        Math.max(
                                query(seg, 1, 0, MAX, 0, left),
                                x - left
                        );

                ans.add(best >= sz);
            }
        }

        return ans;
    }

    private void build(int[] seg, int idx, int l, int r) {
        if (l == r) return;

        int mid = (l + r) / 2;

        build(seg, idx * 2, l, mid);
        build(seg, idx * 2 + 1, mid + 1, r);
    }

    private void update(int[] seg,
                        int idx,
                        int l,
                        int r,
                        int pos,
                        int val) {

        if (l == r) {
            seg[idx] = val;
            return;
        }

        int mid = (l + r) / 2;

        if (pos <= mid) {
            update(seg, idx * 2, l, mid, pos, val);
        } else {
            update(seg, idx * 2 + 1, mid + 1, r, pos, val);
        }

        seg[idx] = Math.max(seg[idx * 2], seg[idx * 2 + 1]);
    }

    private int query(int[] seg,
                      int idx,
                      int l,
                      int r,
                      int ql,
                      int qr) {

        if (qr < l || ql > r) {
            return 0;
        }

        if (ql <= l && r <= qr) {
            return seg[idx];
        }

        int mid = (l + r) / 2;

        return Math.max(
                query(seg, idx * 2, l, mid, ql, qr),
                query(seg, idx * 2 + 1, mid + 1, r, ql, qr)
        );
    }
}