package com.dragger2.test_huffmantree;

import java.util.ArrayList;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/18
 */
public class Test {

    @org.junit.Test
    public void huffmanTreeTest() {
        ArrayList<HuffmanTree.TreeNode<String>> list = new ArrayList<>();
        HuffmanTree.TreeNode node1 = new HuffmanTree.TreeNode("good", 50);
        list.add(node1);
        HuffmanTree.TreeNode node2 = new HuffmanTree.TreeNode("morning", 10);
        list.add(node2);
        HuffmanTree.TreeNode node3 = new HuffmanTree.TreeNode("afternoon", 20);
        list.add(node3);
        HuffmanTree.TreeNode node4 = new HuffmanTree.TreeNode("hello", 110);
        list.add(node4);
        HuffmanTree.TreeNode node5 = new HuffmanTree.TreeNode("hi", 200);
        list.add(node5);
        System.out.print("原   数   据 ：");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).item + " ");
        }
        HuffmanTree<String> huffmanTree = new HuffmanTree<>();
        //创建树
        HuffmanTree.TreeNode root = huffmanTree.createHuffmanTree(list);
        //展示树
        System.out.print("\nhuffmanTree里：");
        huffmanTree.showHuffmanTree(root);

        String node1Code = huffmanTree.getCode(node1);
        String node2Code = huffmanTree.getCode(node2);
        String node3Code = huffmanTree.getCode(node3);
        String node4Code = huffmanTree.getCode(node4);
        String node5Code = huffmanTree.getCode(node5);
        //根据节点查询相应编码
        System.out.println("\n" + node1.item + "      在huffmanTree里的编码：" + node1Code);
        System.out.println(node2.item + "   在huffmanTree里的编码：" + node2Code);
        System.out.println(node3.item + " 在huffmanTree里的编码：" + node3Code);
        System.out.println(node4.item + "     在huffmanTree里的编码：" + node4Code);
        System.out.println(node5.item + "        在huffmanTree里的编码：" + node5Code);
        String code = node1Code + node2Code + node3Code + node4Code + node5Code;
        System.out.println("list里所有值的组成编码：" + code);
        System.out.println("================================解 码================================");
        ArrayList<HuffmanTree.TreeNode<String>> resultList = huffmanTree.getTreeNodeList(code);
        if (resultList != null) {
            for (int i = 0; i < resultList.size(); i++) {
                System.out.print(resultList.get(i).item + " ");
            }
        }
    }
}
