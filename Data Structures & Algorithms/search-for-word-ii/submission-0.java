class Solution {

    class TrieNode {

        TrieNode[] children = new TrieNode[26];

        String word;
    }

    TrieNode root = new TrieNode();

    List<String> ans = new ArrayList<>();

    public List<String> findWords(char[][] board,
                                  String[] words) {

        // Build Trie
        for (String word : words) {

            TrieNode current = root;

            for (char ch : word.toCharArray()) {

                int index = ch - 'a';

                if (current.children[index] == null) {

                    current.children[index] =
                            new TrieNode();
                }

                current =
                        current.children[index];
            }

            current.word = word;
        }

        // DFS from every cell
        for (int i = 0; i < board.length; i++) {

            for (int j = 0;
                 j < board[0].length;
                 j++) {

                dfs(board, i, j, root);
            }
        }

        return ans;
    }

    public void dfs(char[][] board,
                    int row,
                    int col,
                    TrieNode node) {

        // Invalid cases
        if (row < 0 ||
            col < 0 ||
            row >= board.length ||
            col >= board[0].length ||
            board[row][col] == '#') {

            return;
        }

        char ch = board[row][col];

        TrieNode next =
                node.children[ch - 'a'];

        // Word path not present
        if (next == null) {
            return;
        }

        // Word found
        if (next.word != null) {

            ans.add(next.word);

            next.word = null;
        }

        // Mark visited
        board[row][col] = '#';

        dfs(board, row + 1, col, next);
        dfs(board, row - 1, col, next);
        dfs(board, row, col + 1, next);
        dfs(board, row, col - 1, next);

        // Backtrack
        board[row][col] = ch;
    }
}