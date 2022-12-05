<a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array">Find minimum in rotated sorted array</a>

Finding min/max on an array without extra information obviously takes linear time. However, if we know the array is sorted, we can do it in log<sub>2</sub>N time, searching for
an element that violates the definition of a sorted array, i.e. `array[i+1] < array[i]`.

Extra optimizations:
* use bit shift `>>` instead of `/2` to speed up computing mean