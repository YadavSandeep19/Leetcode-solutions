class Solution {
    public String reverseParentheses(String s) {
        if(!s.contains("(") || !s.contains(")"))
        return s;
        if(s == null)
        return s;

        Stack<Character> stk = new Stack();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != ')') stk.push(s.charAt(i));
            else{
                String temp = "";
                while(stk.peek()!='(') temp+=stk.pop();
                if(stk.size() > 0) stk.pop();
                temp.chars().forEach(c -> stk.push((char)c));
            }
        }
        String ans = stk.stream().map(sa -> sa.toString()).collect(Collectors.joining(""));
        return ans;
    }
}