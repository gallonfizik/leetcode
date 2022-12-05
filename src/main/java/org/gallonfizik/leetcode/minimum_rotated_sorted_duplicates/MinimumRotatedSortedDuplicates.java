package org.gallonfizik.leetcode.minimum_rotated_sorted_duplicates;

class MinimumRotatedSortedDuplicates {
    public int findMin(final int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Side left = new Side(0, nums[0]);
        Side right = new Side(nums.length - 1, nums[nums.length - 1]);
        Side pivot = new Side(0, 0);

        if (left.value < right.value) {
            return left.value;
        }

        right.adjustRight(nums);
        while (right.index > left.index + 1) {
            pivot.index = (left.index + right.index) >> 1;
            pivot.value = nums[pivot.index];


            if (left.value > pivot.value) {
                right.from(pivot);
            } else {
                left.from(pivot);
            }

            if (pivot.index == left.index + 1) {
                return Math.min(pivot.value, left.value);
            }
        }

        return right.value;
    }

    static class Side {
        int index;
        int value;

        public Side(final int index, final int value) {
            this.index = index;
            this.value = value;
        }

        public void from(Side other) {
            this.index = other.index;
            this.value = other.value;
        }

        @Override
        public String toString() {
            return "[%d] -> %d".formatted(index, value);
        }

        public void adjustRight(int[] nums) {
            while (index > 0 && nums[index - 1] == value) {
                --index;
            }
        }
    }
}
