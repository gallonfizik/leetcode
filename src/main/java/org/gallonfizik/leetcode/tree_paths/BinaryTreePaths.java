package org.gallonfizik.leetcode.tree_paths;

import java.util.ArrayDeque;
import java.util.List;

public class BinaryTreePaths {
    private final GrowOnlyArray<String> paths = new GrowOnlyArray<>();

    public List<String> binaryTreePaths(TreeNode node) {
        paths.clear();

        String rootPath = "" + node.val;

        if (node.left == null && node.right == null) {
            return List.of(rootPath);
        }

        ArrayDeque<NodeWrapper> pending = new ArrayDeque<>();
        if (node.left != null) {
            pending.addLast(new NodeWrapper(node.left, rootPath));
        }

        if (node.right != null) {
            pending.addLast(new NodeWrapper(node.right, rootPath));
        }

        while (!pending.isEmpty()) {
            NodeWrapper visiting = pending.removeLast();
            TreeNode taskNode = visiting.node;
            String pathToHere = visiting.path + "->" + taskNode.val;

            if (taskNode.left == null && taskNode.right == null) {
                paths.push(pathToHere);
                continue;
            }

            if (taskNode.left != null) {
                pending.addLast(new NodeWrapper(taskNode.left, pathToHere));
            }

            if (taskNode.right != null) {
                pending.addLast(new NodeWrapper(taskNode.right, pathToHere));
            }
        }
        return paths.toList();
    }

    record NodeWrapper(TreeNode node, String path) {
    }
}
