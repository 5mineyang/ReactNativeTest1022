package com.dragger2.test_searchbinarytree;

public class Test {

    @org.junit.Test
    public void addition_isCorrect() {
        SearchBinaryTree tree = new SearchBinaryTree();
        //测试数组
        int[] array = new int[] {5, 2, 7, 3, 4, 1, 6};
        System.out.print("初始化数组：");
        for (int i : array) {
            tree.put(i);
            System.out.print(i + " ");
        }
        System.out.print("\n树中序遍历：");
        tree.midOrderTraverse(tree.root);

        System.out.println();
        for (int i = 0; i < array.length; i++) {
            SearchBinaryTree.TreeNode node = tree.searchNode(array[i]);
            if (node != null) {
                System.out.print("删  除：" + node.item + "\n");
                tree.delNode(node);
                System.out.print("删除后：");
                tree.midOrderTraverse(tree.root);
                System.out.println();
            }
        }
    }

    @org.junit.Test
    public void orderBinaryTreeTest() {
        OrderBinaryTree tree = new OrderBinaryTree();
        //测试数组
        String[] stringArray = new String[] {"F", "K", "G", "A", "D", "B", "V", "C", "F", "E", "B", "S"};
        System.out.print("初始化数组：");
        for (String i : stringArray) {
            tree.put(i);
            System.out.print(i + " ");
        }
        System.out.print("\n树中序遍历：");
        tree.midOrderTraverse(tree.root);
        System.out.println("\n树的数量是：" + tree.size());

        System.out.println();
        for (int i = 0; i < stringArray.length; i++) {
            OrderBinaryTree.TreeNode treeNode = tree.searchTreeNode(stringArray[i]);
            if (treeNode != null) {
                System.out.print("删  除：" + treeNode.item + "\n");
                tree.delTreeNode(treeNode);
                System.out.print("删除后：");
                tree.midOrderTraverse(tree.root);
                System.out.println();
            }
        }
    }
}