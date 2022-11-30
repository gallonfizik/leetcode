package org.gallonfizik.leetcode.valid_sudoku;

/**
 * Every cell value is used in 27 views.
 * It's few enough to be stored as bits in an int.
 * Bit layout, right to left: 9 occurrences in column views, 9 occurrences in row views, 9 occurrences in square views.
 */
public class Bitmask implements ValidSudoku {
    static final int BLANK = '.';

    private static final int[] masks = new int[81];

    static {
        int maskIndex = 0;
        int rowMask = 1 << 9;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int square = (row / 3) * 3 + (col / 3);
                masks[maskIndex++] = rowMask | (1 << 18 + square) | (1 << col);
            }
            rowMask = rowMask << 1;
        }
    }

    @Override
    public boolean isValidSudoku(char[][] board) {
        final int[] views = new int[9];

        for (int row = 0; row < 9; row++) {
            char[] line = board[row];
            int maskIndexRowPart = 9*row;
            for (int col = 0; col < 9; col++) {
                int digit = line[col];
                if (digit == BLANK) {
                    continue;
                }
                digit -= 49;
                int mask = masks[maskIndexRowPart + col];
                if ((views[digit] & mask) != 0) {
                    return false;
                }
                views[digit] |= mask;
            }
        }
        return true;
    }
}
