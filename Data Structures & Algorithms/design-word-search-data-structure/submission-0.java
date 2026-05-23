class WordDictionary {

    class Node {
        Node[] children = new Node[26];
        boolean isEnd;
    }

    Node root;

    public WordDictionary() {
        root = new Node();
    }
    
    public void addWord(String word) {
        Node curr = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            curr = curr.children[idx];
        }

        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, Node node) {
        if (node == null) {
            return false;
        }

        if (index == word.length()) {
            return node.isEnd;
        }

        char ch = word.charAt(index);

        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    if (dfs(word, index + 1, node.children[i])) {
                        return true;
                    }
                }
            }

            return false;
        } else {
            int idx = ch - 'a';
            return dfs(word, index + 1, node.children[idx]);
        }
    }
}