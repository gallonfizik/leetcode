<a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii">Find minimum in rotated sorted array with duplicates</a>

Same as [Finding min in a rotated sorted array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii), but with duplicates. After each partitioning, right 
side must be adjusted to skip any adjacent duplicate elements, if any. Probably something like 4-pointer QuickSort algorithm may be implemented; similarly, linear performance is 
possible in worst case, as opposed to log<sub>2</sub>N in average/best case.

It's possible to search for the end of a contiguous area of duplicates with binary search. Worth investigating.
