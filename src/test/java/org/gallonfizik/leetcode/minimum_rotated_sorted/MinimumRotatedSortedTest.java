package org.gallonfizik.leetcode.minimum_rotated_sorted;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.gallonfizik.leetcode.BenchmarkRunner;
import org.gallonfizik.leetcode.minimum_rotated_sorted.MinimumRotatedSorted;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class MinimumRotatedSortedTest {
    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{2}, 2),
                Arguments.of(new int[]{3, 1, 2}, 1),
                Arguments.of(new int[]{11, 13, 15, 17}, 11),
                Arguments.of(new int[]{6, 7, 8, 1, 2, 3, 4, 5}, 1),
                Arguments.of(new int[]{4, 5, 1, 2, 3}, 1),
                Arguments.of(new int[]{3, 4, 5, 1, 2}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    void acceptance(int[] nums, int expected) {
        int result = new MinimumRotatedSorted().findMin(nums);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void benchmark() {
        int N = 100_000_000;
        int batches = 50;
        int batchSize = 100000;

        Supplier<int[]> batchDataGenerator = () -> {

            int[] nums = new int[N];
            int shift = (int) (Math.random() * N);
            for (int i = 0; i < N; i++) {
                nums[i] = (i + shift) % N;
            }
            return nums;
        };

        MinimumRotatedSorted instance = new MinimumRotatedSorted();
        new BenchmarkRunner(batches, batchSize).benchmark(batchDataGenerator, instance::findMin);

        int[] data = batchDataGenerator.get();
        assertThat(instance.findMin(data)).isEqualTo(Arrays.stream(data).min().orElseThrow());
    }
}
