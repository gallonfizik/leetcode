package org.gallonfizik.leetcode.tree_paths;

/**
 * Provided by LeetCode.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {this.val = val;}

    TreeNode(int val, org.gallonfizik.leetcode.tree_paths.TreeNode left, org.gallonfizik.leetcode.tree_paths.TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
