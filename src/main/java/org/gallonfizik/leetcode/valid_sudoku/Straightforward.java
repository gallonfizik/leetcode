package org.gallonfizik.leetcode.valid_sudoku;

/**
 * [+] read each cell only once
 * [+] use an abstraction for a View
 * [-] 27 View instantiations
 */
public class Straightforward implements ValidSudoku {
    @Override
    @SuppressWarnings("java:S3776") // "cognitive complexity too high"
    public boolean isValidSudoku(final char[][] board) {
        View[] views = new View[27]; // 2D array is obvious but slow. Use 1D array and flattened indices

        for (int i = 0; i < 27; i++) {
            views[i] = new View();
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char digit = board[row][col];
                if (digit == '.') {
                    continue;
                }

                if (views[row].accept(digit)) {
                    return false;
                }
                if (views[9+col].accept(digit)) {
                    return false;
                }
                int square = (row / 3) * 3 + (col /3);
                if (views[18+square].accept(digit)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Set serves well, but it's slow for such a small task.
     * We can cast numeric chars to ints and use them as indices in an array.
     * 1-9 map to monotonously growing values with increment = 1.
     * As next optimization, we can subtract 49 from char value to get integers 0...8, this saves space.
     */
    private static class View {
        private final boolean[] digits = new boolean[9];

        boolean accept(char digit) {
            digit -= 49;
            if (digits[digit]) {
                return true;
            }
            digits[digit] = true;
            return false;
        }
    }
}
