package org.gallonfizik.leetcode.two_sum;

import java.util.HashMap;
import java.util.Map;

class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> matches = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            Integer match = matches.get(target - num);
            if (match != null) {
                return new int[]{match, i};
            }
            matches.put(num, i);
        }

        // should never happen
        return new int[0];
    }
}
