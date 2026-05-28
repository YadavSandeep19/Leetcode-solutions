
class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];

        int idx = -1;
        int len = Integer.MAX_VALUE;
    }

    TrieNode root = new TrieNode();

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        // Build Trie
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = query(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String word, int idx) {

        TrieNode node = root;

        // update root answer
        if (word.length() < node.len) {
            node.len = word.length();
            node.idx = idx;
        }

        for (int i = word.length() - 1; i >= 0; i--) {

            int c = word.charAt(i) - 'a';

            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }

            node = node.child[c];

            // maintain best candidate
            if (word.length() < node.len) {
                node.len = word.length();
                node.idx = idx;
            }
        }
    }

    private int query(String word) {

        TrieNode node = root;

        for (int i = word.length() - 1; i >= 0; i--) {

            int c = word.charAt(i) - 'a';

            if (node.child[c] == null) {
                break;
            }

            node = node.child[c];
        }

        return node.idx;
    }
}