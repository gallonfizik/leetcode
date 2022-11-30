package org.gallonfizik.leetcode.valid_sudoku;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.DoubleSummaryStatistics;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class ValidSudokuTest {
    protected abstract ValidSudoku instance();

    static Stream<Arguments> bestCaseData() {
        return Stream.of(
                Arguments.of(
                        Named.of(
                                "Valid, empty", new char[][]{
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                        {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
                                }),
                        true
                ),
                Arguments.of(
                        Named.of("Invalid: duplicates in row",
                                 new char[][]{
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '1', '.', '.', '1'}
                                 }),
                        false
                ),
                Arguments.of(
                        Named.of("Invalid: duplicates in column",
                                 new char[][]{
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '1'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '1'}
                                 }),
                        false
                ),
                Arguments.of(
                        Named.of("Invalid: duplicates in square",
                                 new char[][]{
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '1', '.'},
                                         {'.', '.', '.', '.', '.', '.', '.', '.', '1'}
                                 }),
                        false
                )
        );
    }

    static Stream<Arguments> worstCaseData() {
        return Stream.of(
                Arguments.of(
                        Named.of(
                                "Valid, solved", new char[][]{
                                        {'5', '1', '9', '7', '4', '8', '6', '3', '2'},
                                        {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
                                        {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
                                        {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
                                        {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
                                        {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
                                        {'9', '7', '5', '8', '6', '3', '1', '2', '4'},
                                        {'8', '3', '2', '4', '9', '1', '7', '5', '6'},
                                        {'6', '4', '1', '2', '7', '5', '9', '8', '3'}
                                }),
                        true
                ),
                Arguments.of(
                        Named.of("Invalid: duplicates in row",
                                 new char[][]{
                                         {'5', '1', '9', '7', '4', '8', '6', '3', '2'},
                                         {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
                                         {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
                                         {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
                                         {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
                                         {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
                                         {'9', '7', '5', '8', '6', '.', '1', '2', '4'},
                                         {'8', '3', '2', '4', '9', '1', '7', '5', '6'},
                                         {'6', '4', '1', '2', '7', '3', '9', '8', '3'}
                                 }),
                        false
                ),
                Arguments.of(
                        Named.of("Invalid: duplicates in column",
                                 new char[][]{
                                         {'5', '1', '9', '7', '4', '8', '6', '3', '2'},
                                         {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
                                         {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
                                         {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
                                         {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
                                         {'1', '9', '8', '5', '2', '4', '.', '6', '3'},
                                         {'9', '7', '5', '8', '6', '3', '1', '2', '4'},
                                         {'8', '3', '2', '4', '9', '1', '7', '5', '6'},
                                         {'6', '4', '1', '2', '7', '5', '9', '8', '3'}
                                 }),
                        false
                ),
                Arguments.of(
                        Named.of("Invalid: duplicates in square",
                                 new char[][]{
                                         {'5', '1', '9', '7', '4', '8', '6', '.', '2'},
                                         {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
                                         {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
                                         {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
                                         {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
                                         {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
                                         {'9', '7', '5', '8', '6', '3', '1', '2', '4'},
                                         {'8', '.', '2', '4', '9', '1', '7', '3', '6'},
                                         {'6', '4', '1', '2', '7', '5', '9', '8', '3'}
                                 }),
                        false
                )

        );
    }

    @ParameterizedTest
    @MethodSource("worstCaseData")
    void worstCase(char[][] board, boolean expected) {
        doTest(board, expected);
    }

    @ParameterizedTest
    @MethodSource("bestCaseData")
    void bestCase(char[][] board, boolean expected) {
        doTest(board, expected);
    }

    private void doTest(char[][] board, boolean expected) {
        int batches = 5;
        int batchSize = 400000;
        DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();
        for (int batch = 0; batch < batches; batch++) {
            long tic = System.currentTimeMillis();
            for (int i = 0; i < batchSize; i++) {
                instance().isValidSudoku(board);
            }
            long elapsed = System.currentTimeMillis() - tic;
            statistics.accept(1.0 * batchSize / elapsed);
        }
        log.info("Average throughput: {} KOps/s.", (int) statistics.getAverage());

        assertThat(instance().isValidSudoku(board)).isEqualTo(expected);
    }

    @Test
    void unitTest1() {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        assertThat(instance().isValidSudoku(board)).isTrue();
    }

    @Test
    void unitTest2() {
        char[][] board = new char[][]{
                {'.', '.', '.', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '.', '.', '6', '.', '.', '.'},
                {'4', '.', '.', '.', '.', '.', '3', '8', '.'},
                {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '5', '3', '.', '.', '.'},
                {'.', '.', '.', '.', '6', '8', '.', '.', '.'},
                {'3', '.', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '1', '1', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };

        assertThat(instance().isValidSudoku(board)).isFalse();
    }
}
