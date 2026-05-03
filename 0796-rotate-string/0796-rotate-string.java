class Solution {
    public boolean rotateString(String s, String goal) {

        // Length must match
        if (s.length() != goal.length()) {
            return false;
        }

        // Core logic
        String doubled = s + s;

        return doubled.contains(goal);
    }
}