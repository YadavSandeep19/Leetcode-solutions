class Solution {

    static class Node {
        Node[] child = new Node[10];
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Node root = new Node();

        // Insert all numbers from arr1 into trie
        for (int num : arr1) {
            insert(root, num);
        }

        int ans = 0;

        // Query all numbers from arr2
        for (int num : arr2) {
            ans = Math.max(ans, search(root, num));
        }

        return ans;
    }

    private void insert(Node root, int num) {
        char[] digits = String.valueOf(num).toCharArray();
        Node curr = root;

        for (char ch : digits) {
            int d = ch - '0';
            if (curr.child[d] == null) {
                curr.child[d] = new Node();
            }
            curr = curr.child[d];
        }
    }

    private int search(Node root, int num) {
        char[] digits = String.valueOf(num).toCharArray();
        Node curr = root;
        int len = 0;

        for (char ch : digits) {
            int d = ch - '0';
            if (curr.child[d] == null) {
                break;
            }
            curr = curr.child[d];
            len++;
        }

        return len;
    }
}