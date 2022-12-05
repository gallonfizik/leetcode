# leetcode

## Disclaimer and restrictions

* done for own fun
* all production code is vanilla Java (see [build.gradle](build.gradle))

## Solutions

1. <a href="https://leetcode.com/problems/valid-sudoku">Valid sudoku</a>
    1. [Straightforward solution](src/main/java/org/gallonfizik/leetcode/valid_sudoku/Straightforward.java)

       Iterate over cells/views; in each view keep track of seen digits.
       If current digits has already been seen, sudoku is invalid. Throughput on my
       machine ~2200 kilo solutions/sec in worst case(s).
    2. [Optimized solution](src/main/java/org/gallonfizik/leetcode/valid_sudoku/Bitmask.java)

       For each digit, keep track of the views it's been seen in. There are 27 views in
       total, which fits into integer as bits. Row/column/square are used
       to map the bit. Masks need to be computed just once, or even set as literals. Also, it uses the fact that `1..9` characters have contiguous values as `char`s, and can be
       translated to 0..8 to be used as array indices. Pros:
        * only `&, |, +, *, <, !=  ` operations
        * single pass over input data

       Throughput on my machine: ~1100 kilo solutions/sec in worst case(s).
2. <a href="https://leetcode.com/problems/two-sum/">Two sum</a>

   Pretty trivial hash map [implementation](src/main/java/org/gallonfizik/leetcode/two_sum/TwoSum.java). O(N^2) approach could be viable for problems of size <~ 5.
3. <a href="https://leetcode.com/problems/binary-tree-paths">Binary tree paths</a> –
    [readme](src/main/java/org/gallonfizik/leetcode/tree_paths/readme.md)
4. <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array">Find minimum in rotated sorted array</a> – 
    [readme](src/main/java/org/gallonfizik/leetcode/minimum_rotated_sorted/readme.md)
5. <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array">Find minimum in rotated sorted array</a> –
    [readme](src/main/java/org/gallonfizik/leetcode/minimum_rotated_sorted/MinimumRotatedSorted.java)
6. <a href="https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii">Find minimum in rotated sorted array with duplicates</a> – 
   [readme](src/main/java/org/gallonfizik/leetcode/minimum_rotated_sorted_duplicates/MinimumRotatedSortedDuplicates.java)