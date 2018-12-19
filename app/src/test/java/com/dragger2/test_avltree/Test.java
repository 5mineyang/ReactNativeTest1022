package com.dragger2.test_avltree;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/19
 */

public class Test {

    @org.junit.Test
    public void AVLTreeTest() {
        String list[] = new String[] {"f", "c", "b", "d", "a", "e", "g", "h"};

        AVLTree<String> avlTree = new AVLTree<>();
        for (String s : list) {
            avlTree.addAVLTree(s);
        }
        System.out.print("树：");
        avlTree.showAllAVLTree(avlTree.root);
        System.out.println("\n树大小：" + avlTree.size);
    }
}
