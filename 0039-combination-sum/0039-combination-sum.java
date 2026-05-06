class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        solve(candidates,0,target,new ArrayList<>());
        return ans;
    }
    public void solve(int[] candidates, int idx, int target, List<Integer> curr) {
        if (target==0) {
            ans.add(new ArrayList<>(curr));
            return;
        }
        if (target<0 || idx==candidates.length) return;
        curr.add(candidates[idx]);
        solve(candidates,idx,target-candidates[idx], curr);
        curr.remove(curr.size()-1);
        solve(candidates,idx+1,target,curr);
    }
}