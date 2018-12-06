package com.dragger2.test_binarytree;

/**
 * Description :二叉树
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/4
 */

public class BinaryTree {
    Node<String> root;

    /**
     * 节点
     *
     * @param <Y>
     */
    public class Node<Y> {
        Y item;
        Node<Y> leftChild;
        Node<Y> rightChild;

        public Node(Y item, Node<Y> leftChild, Node<Y> rightChild) {
            this.item = item;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    public BinaryTree(String data) {
        //创建一个Root根节点
        root = new Node<>(data, null, null);
    }

    //创建几个树节点
    public void createTree() {
        Node<String> nodeB = new Node<>("B", null, null);
        Node<String> nodeC = new Node<>("C", null, null);
        Node<String> nodeD = new Node<>("D", null, null);
        Node<String> nodeE = new Node<>("E", null, null);
        Node<String> nodeF = new Node<>("F", null, null);
        Node<String> nodeG = new Node<>("G", null, null);
        Node<String> nodeH = new Node<>("H", null, null);
        Node<String> nodeJ = new Node<>("J", null, null);
        Node<String> nodeI = new Node<>("I", null, null);

        root.leftChild = nodeB;
        root.rightChild = nodeC;

        nodeB.leftChild = nodeD;

        nodeC.leftChild = nodeE;
        nodeC.rightChild = nodeF;

        nodeD.leftChild = nodeG;
        nodeD.rightChild = nodeH;

        nodeE.rightChild = nodeJ;

        nodeH.leftChild = nodeI;
    }

    //遍历所有树，LDR 中序遍历
    public void middleOrderTree(Node node) {
        if (node == null) {
            return;
        }
        //先查自己的左树
        middleOrderTree(node.leftChild);
        //输出自己
        System.out.print(node.item + " ");
        //再查自己的右树
        middleOrderTree(node.rightChild);
    }
}
