package com.dragger2.test_huffmantree;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Description :哈夫曼树
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/18
 */

public class HuffmanTree<Y> {
    public TreeNode<Y> root;   //根节点
    private String defaultParentItem = "Y";

    /**
     * 节点
     *
     * @param <Y> 值
     */
    public static class TreeNode<Y> implements Comparable<TreeNode<Y>> {
        Y item; //值
        int weight; //权重
        TreeNode<Y> left;   //左孩子
        TreeNode<Y> right;  //右孩子
        TreeNode<Y> parent; //父节点

        public TreeNode(Y item, int weight) {
            this.item = item;
            this.weight = weight;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

        @Override
        public int compareTo(@NonNull TreeNode<Y> o) {
            if (this.weight > o.weight) {
                return -1;
            } else if (this.weight < o.weight) {
                return 1;
            }
            return 0;
        }
    }

    /**
     * 传一个数组进来 创建哈夫曼树
     *
     * @param list 集合
     * @return 根节点
     */
    public TreeNode<Y> createHuffmanTree(ArrayList<TreeNode<Y>> list) {
        //这里就当list里超过2个 就一直循环，知道删了只剩一个时出来 那时候这个节点就是根节点
        while (list.size() > 1) {
            //用Collections进行排序
            Collections.sort(list);
            //取list的最后2个当做左右孩子树
            TreeNode<Y> left = list.get(list.size() - 1);
            TreeNode<Y> right = list.get(list.size() - 2);
            //根据左右权重创建父节点
            TreeNode<Y> parent = new TreeNode<>((Y) defaultParentItem, left.weight + right.weight);
            //把左右树和父节点连接
            parent.left = left;
            left.parent = parent;
            parent.right = right;
            right.parent = parent;
            //从list里删除左右树
            list.remove(left);
            list.remove(right);
            //再把父节点添加进去
            list.add(parent);
        }
        root = list.get(0);
        return list.get(0);
    }

    /**
     * 横向依次输出树
     *
     * @param root 根节点
     */
    public void showHuffmanTree(TreeNode<Y> root) {
        //这里用LinkedList队列可以依次取出值
        LinkedList<TreeNode<Y>> list = new LinkedList<>();
        //入队
        list.offer(root);
        while (!list.isEmpty()) {
            //出队 直接输出
            TreeNode<Y> node = list.pop();
            if (node.item != defaultParentItem) {
                System.out.print(node.item + " ");
            }
            //如果左右还有孩子 就把左右孩子也入队，到下一个循环排在后面依次输出
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }

    /**
     * 根据节点获取编码
     *
     * @param node 节点
     * @return 编码
     */
    public String getCode(TreeNode<Y> node) {
        String str = "";
        TreeNode<Y> treeNode = node;
        //用栈装 待会拿出来就是正序了
        Stack<String> stack = new Stack<>();
        while (treeNode != null && treeNode.parent != null) {
            if (treeNode.parent.left == treeNode) {
                stack.push("0");
            } else if (treeNode.parent.right == treeNode) {
                stack.push("1");
            }
            treeNode = treeNode.parent;
        }
        //把stack里的值出栈
        while (!stack.isEmpty()) {
            str = str + stack.pop();
        }
        return str;
    }

    /**
     * 根据传进来的编码返回一个list
     *
     * @param code 编码字符串
     * @return 集合
     */
    public ArrayList<TreeNode<Y>> getTreeNodeList(String code) {
        ArrayList<TreeNode<Y>> resultList = new ArrayList<>();
        TreeNode node = root;
        //依次循环 是0就往左边走 1右边走
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '0') {
                //如果左孩子不为空 就移到左孩子
                if (node.left != null) {
                    node = node.left;
                    //如果移动过后它的左孩子和右孩子都为空 则说明自己就是要取的值了 然后把node再次赋值为根节点，重新查
                    if (node.left == null && node.right == null) {
                        resultList.add(node);
                        node = root;
                    }
                }
            } else if (code.charAt(i) == '1') {
                if (node.right != null) {
                    node = node.right;
                    if (node.left == null && node.right == null) {
                        resultList.add(node);
                        node = root;
                    }
                }
            }
        }
        return resultList;
    }
}
