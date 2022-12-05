package org.gallonfizik.leetcode.tree_paths;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.gallonfizik.leetcode.BenchmarkRunner;
import org.junit.jupiter.api.Test;

class BinaryTreePathsTest {
    private BinaryTreePaths instance() {
        return new BinaryTreePaths();
    }

    @Test
    void binaryTreePathsSmallTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(7);

        BinaryTreePaths instance = instance();
        new BenchmarkRunner(100, 100000).benchmark(() -> instance.binaryTreePaths(root));

        assertThat(instance.binaryTreePaths(root)).containsExactlyInAnyOrder(
                "1->2->3->5",
                "1->2->4",
                "1->6->7"
        );
    }

    @Test
    void binaryTreePathsLargeTree() {
        TreeNode root = new TreeNode(0);
        for (int i = 1; i < 100; i++) {
            add(root, i);
            add(root, -i);
        }

        BinaryTreePaths instance = instance();
        new BenchmarkRunner(20, 10000).benchmark(() -> instance.binaryTreePaths(root));

        assertThat(instance.binaryTreePaths(root)).hasSize(100);
    }


    private static void add(TreeNode root, int value) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.removeFirst();
            if (node.left == null) {
                node.left = new TreeNode(value);
                return;
            } else {
                queue.addLast(node.left);
            }
            if (node.right == null) {
                node.right = new TreeNode(value);
                return;
            } else {
                queue.addLast(node.right);
            }
        }
    }
}
