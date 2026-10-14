class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum > 9L * num) return "";

        StringBuilder sb = new StringBuilder(num);
        int rem = sum;
        for (int i = 0; i < num; i++) {
            int d = Math.min(9, rem);
            sb.append((char) ('0' + d));
            rem -= d;
        }
        return sb.toString();
    }
}