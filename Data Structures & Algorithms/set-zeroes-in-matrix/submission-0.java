class Solution {

    public void setZeroes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[] zeroRow = new boolean[rows];
        boolean[] zeroCol = new boolean[cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (matrix[i][j] == 0) {

                    zeroRow[i] = true;
                    zeroCol[j] = true;
                }
            }
        }

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (zeroRow[i] ||
                    zeroCol[j]) {

                    matrix[i][j] = 0;
                }
            }
        }
    }
}