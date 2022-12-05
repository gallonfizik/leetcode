package org.gallonfizik.leetcode.minimum_rotated_sorted;

public class MinimumRotatedSorted {
    public int findMin(final int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Side left = new Side(0, nums[0]);
        Side right = new Side(nums.length - 1, nums[nums.length - 1]);

        // means shift = 0
        if (left.value < right.value) {
            return left.value;
        }

        while (right.index > left.index + 1) {
            int pivotIndex = (left.index + right.index) >> 1;
            int pivotValue = nums[pivotIndex];

            if (left.value > pivotValue) {
                right.move(pivotIndex, pivotValue);
                if (pivotIndex == left.index + 1) {
                    return Math.min(pivotValue, left.value);
                }
            } else {
                left.move(pivotIndex, pivotValue);
            }
        }

        return right.value;
    }

    static class Side {
        private int index;
        private int value;

        public Side(final int index, final int value) {
            this.index = index;
            this.value = value;
        }

        public void move(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[%d] -> %d".formatted(index, value);
        }
    }
}
