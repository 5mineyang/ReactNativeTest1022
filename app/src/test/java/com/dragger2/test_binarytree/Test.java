package com.dragger2.test_binarytree;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/4
 */

public class Test {

    @org.junit.Test
    public void customLinkedTest() {
        BinaryTree binaryTree = new BinaryTree("A");
        //创建树
        binaryTree.createTree();
        binaryTree.middleOrderTree(binaryTree.root);
    }
}
