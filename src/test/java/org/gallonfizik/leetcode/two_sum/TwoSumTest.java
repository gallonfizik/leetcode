package org.gallonfizik.leetcode.two_sum;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LongSummaryStatistics;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class TwoSumTest {
    static Stream<Arguments> source() {
        return Stream.of(
                Arguments.of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
                Arguments.of(new int[]{3, 2, 4}, 6, new int[]{1, 2})
        );
    }

    @ParameterizedTest
    @MethodSource("source")
    void twoSumTes(int[] nums, int target, int[] expected) {
        int[] result = new TwoSum().twoSum(nums, target);
        assertThat(result).containsExactlyInAnyOrder(expected);
    }

    @Test
    void benchmark() {
        final int batches = 10;
        final int batchSize = 1000;
        final int N = 10_000;
        final LongSummaryStatistics statistics = new LongSummaryStatistics();
        final TwoSum instance = new TwoSum();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i;
        }
        int target = 1_500_000;
        nums[N - 2] = 1_000_000;
        nums[N - 1] = 500_000;
        int[] expected = new int[]{N - 2, N - 1};

        for (int batch = 0; batch < batches; batch++) {
            long tic = System.currentTimeMillis();
            for (int i = 0; i < batchSize; i++) {
                instance.twoSum(nums, target);
            }
            statistics.accept(System.currentTimeMillis() - tic);
            assertThat(instance.twoSum(nums, target)).containsExactlyInAnyOrder(expected);
        }

        System.out.println(statistics);
        System.out.printf("Average throughput: %.1f K ops/s.", 1.0 * batchSize / statistics.getAverage());
    }
}
