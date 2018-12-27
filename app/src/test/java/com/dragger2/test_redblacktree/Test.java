package com.dragger2.test_redblacktree;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/21
 */
public class Test {

    @org.junit.Test
    public void redBlackTreeTest() {
        int list[] = new int[] {6, 3, 2, 1, 4, 5, 7, 8, 9, 15, 13, 14};
        RedBlackTree<Integer> redBlackTree = new RedBlackTree<>();
        for (int i : list) {
            redBlackTree.addRedBlackTree(i);
        }
        System.out.print("横向遍历树：");
        redBlackTree.showAllRedBlackLTree(redBlackTree.root);
        System.out.print("\n中序遍历树：");
        redBlackTree.midOrderTraverse(redBlackTree.root);
        System.out.println("\n树大小：" + redBlackTree.size);

        //删除值
        redBlackTree.removeRedBlackTree(8);

        System.out.print("横向遍历树：");
        redBlackTree.showAllRedBlackLTree(redBlackTree.root);
        System.out.println("\n");
    }
}
