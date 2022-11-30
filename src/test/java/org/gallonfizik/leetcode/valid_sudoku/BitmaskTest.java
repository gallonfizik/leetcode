package org.gallonfizik.leetcode.valid_sudoku;

class BitmaskTest extends ValidSudokuTest {
    @Override
    protected ValidSudoku instance() {
        return new Bitmask();
    }
}
