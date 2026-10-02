
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    private void backtrack(List<String> ans, String s,
                           int open, int close, int n) {

        // Complete valid string
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }

        // Add '(' if we still have some left
        if (open < n) {
            backtrack(ans, s + "(", open + 1, close, n);
        }

        // Add ')' only when it won't make string invalid
        if (close < open) {
            backtrack(ans, s + ")", open, close + 1, n);
        }
    }
}