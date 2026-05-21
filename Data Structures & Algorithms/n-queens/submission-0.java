class Solution {
    public List<List<String>> solveNQueens(int n) {

        List<List<String>> ans = new ArrayList<>();

        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        solve(board, 0, ans);

        return ans;
    }

    public void solve(char[][] board, int row, List<List<String>> ans) {

        if (row == board.length) {

            List<String> list = new ArrayList<>();

            for (char[] arr : board) {
                list.add(new String(arr));
            }

            ans.add(list);
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {

                board[row][col] = 'Q';

                solve(board, row + 1, ans);

                board[row][col] = '.';
            }
        }
    }

    public boolean isSafe(char[][] board, int row, int col) {

        for (int i = 0; i < row; i++) {

            if (board[i][col] == 'Q') {
                return false;
            }
        }

        int i = row - 1;
        int j = col - 1;

        while (i >= 0 && j >= 0) {
            if (board[i][j] == 'Q') {
                return false;
            }

            i--;
            j--;
        }

        i = row - 1;
        j = col + 1;

        while (i >= 0 && j < board.length) {

            if (board[i][j] == 'Q') {
                return false;
            }

            i--;
            j++;
        }

        return true;
    }
}