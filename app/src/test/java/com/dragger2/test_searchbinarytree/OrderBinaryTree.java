package com.dragger2.test_searchbinarytree;

/**
 * Description :二叉排序树 又称查找树、搜索树
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/10
 */

public class OrderBinaryTree<Y extends Comparable> {
    //根节点
    public TreeNode<Y> root;
    //树的大小
    private int size;

    //获取大小
    public int size() {
        return size;
    }

    /**
     * 节点
     *
     * @param <Y>
     */
    public static class TreeNode<Y> {
        Y item; //值
        TreeNode<Y> leftChild;  //左孩子
        TreeNode<Y> rightChild; //右孩子
        TreeNode<Y> parent; //父节点

        public TreeNode(Y item) {
            this.item = item;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }
    }

    /**
     * 存放值
     *
     * @param item 要添加的值
     */
    public TreeNode<Y> put(Y item) {
        //如果根节点是null 就把刚插入的值new一个新节点 然后把根节点指向它
        if (root == null) {
            TreeNode<Y> newTreeNode = new TreeNode(item);
            root = newTreeNode;
            size++;
            return newTreeNode;
        }
        //定义一个父节点 这里作用是下面循环结束时 treeNode肯定为空 然后就可以根据父节点操作
        TreeNode<Y> parent = null;
        TreeNode<Y> treeNode = root;
        //先找到当前节点要插入的位置
        while (treeNode != null) {
            parent = treeNode;
            //item比treeNode.item小
            if (item.compareTo(treeNode.item) < 0) {
                treeNode = treeNode.leftChild;
            } else if (item.compareTo(treeNode.item) > 0) {
                treeNode = treeNode.rightChild;
            } else {
                return treeNode;
            }
        }
        //新建一个节点
        TreeNode<Y> newTreeNode = new TreeNode(item);
        //循环出来后根据parent判断newTreeNode的位置
        if (item.compareTo(parent.item) < 0) {
            parent.leftChild = newTreeNode;
        } else {
            parent.rightChild = newTreeNode;
        }
        //最后给自己的父节点指向一下
        newTreeNode.parent = parent;
        size++;
        return newTreeNode;
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     */
    public void midOrderTraverse(TreeNode<Y> root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.leftChild);
        System.out.print(root.item + " ");
        midOrderTraverse(root.rightChild);
    }

    /**
     * 根据输入的值 返回一个树节点
     *
     * @param item 值
     * @return 节点
     */
    public TreeNode<Y> searchTreeNode(Y item) {
        if (item == null) {
            return null;
        }
        TreeNode<Y> treeNode = root;
        //treeNode不为空 循环往下遍历
        while (treeNode != null) {
            //2个值相等 就直接返回
            if (item.compareTo(treeNode.item) == 0) {
                return treeNode;
            } else if (item.compareTo(treeNode.item) < 0) { //值比treeNode的值小 就让treeNode赋值为它的左孩子 再次往下判断
                treeNode = treeNode.leftChild;
            } else {
                treeNode = treeNode.rightChild;
            }
        }
        return null;
    }

    /**
     * 删除树节点
     *
     * @param treeNode 树节点
     */
    public void delTreeNode(TreeNode<Y> treeNode) {
        //如果node是null 就直接返回
        if (treeNode == null) {
            return;
        } else {
            //先定义出自己的父树 方便下面调用
            TreeNode<Y> parent = treeNode.parent;
            //这里有4种情况
            //1.被删除的节点是叶子节点（没有孩子）
            if (treeNode.leftChild == null && treeNode.rightChild == null) {
                //这里有一个特殊情况 如果parent为null 则说明当前节点就是根节点
                if (parent == null) {
                    root = null;
                } else {
                    //先判断自己是父节点的左孩子还是右孩子 然后断开他的父节点对他的指针
                    if (parent.leftChild == treeNode) { //左孩子
                        parent.leftChild = null;
                    } else {
                        parent.rightChild = null;
                    }
                }
                //最后在断开自己的父节点 这里是双向指针 所以两头都要指向null
                treeNode.parent = null;
            } else if (treeNode.leftChild != null && treeNode.rightChild == null) {    //2.有左孩子
                //这里判断下当前节点是不是根节点
                if (parent == null) {
                    //把根节点赋值为左孩子
                    root = treeNode.leftChild;
                    treeNode.leftChild.parent = null;
                } else {
                    //判断自己是父节点的左孩子还是右孩子
                    if (parent.leftChild == treeNode) {   //左孩子
                        //父节点的左孩子直接指向自己的左孩子
                        parent.leftChild = treeNode.leftChild;
                    } else {
                        parent.rightChild = treeNode.leftChild;
                    }
                    //把左孩子的父节点赋值为自己的父节点
                    treeNode.leftChild.parent = parent;
                    //最后把自己的父节点置空
                    treeNode.parent = null;
                }
            } else if (treeNode.leftChild == null && treeNode.rightChild != null) {    //3.有右孩子
                if (parent == null) {
                    root = treeNode.rightChild;
                    treeNode.rightChild.parent = null;
                } else {
                    if (parent.leftChild == treeNode) {
                        parent.leftChild = treeNode.rightChild;
                    } else {
                        parent.rightChild = treeNode.rightChild;
                    }
                    treeNode.rightChild.parent = parent;
                    treeNode.parent = null;
                }
            } else {  //4.有左右孩子
                //取自己右孩子的最小值 2种情况
                //一种是自己右孩子有左孩子 然后先找出自己右孩子下的最小值 进行登基··
                if (treeNode.rightChild.leftChild != null) {
                    //找出自己右孩子节点下最小的节点
                    TreeNode<Y> midTreeNode = getMinLeftTreeNode(treeNode.rightChild);
                    //直接将要删除的值赋值为最小孩子节点的值 然后对最小节点指针操作一波就ok了
                    treeNode.item = midTreeNode.item;
                    //操作最小节点时这里又分为2种情况
                    //1.最小节点有右孩子
                    if (midTreeNode.rightChild != null) {
                        //将最小节点右孩子的父节点指向自己的父节点
                        midTreeNode.rightChild.parent = midTreeNode.parent;
                        //最小节点的父节点的左孩子指向自己的右孩子
                        midTreeNode.parent.leftChild = midTreeNode.rightChild;
                    } else {  //2.最小节点没有右孩子
                        //最小节点的父节点的左孩子置空
                        midTreeNode.parent.leftChild = null;
                    }
                    //最后将自己断开
                    midTreeNode.parent = null;
                } else {  //一种是自己的右孩子没有左孩子了 （自己右孩子就是比自己大  然后所有右孩子里最小的值）
                    //定义出自己右孩子
                    TreeNode<Y> rightTreeNode = treeNode.rightChild;
                    //给要删除的值赋值为自己右孩子
                    treeNode.item = rightTreeNode.item;
                    //然后也是判断 2种情况
                    //1.右孩子还有右孩子
                    if (rightTreeNode.rightChild != null) {
                        //右孩子的右孩子的父节点指向要删除的节点（自己）
                        rightTreeNode.rightChild.parent = treeNode;
                        //自己的右孩子指向右孩子的右孩子
                        treeNode.rightChild = rightTreeNode.rightChild;
                    } else {  //右孩子是叶子节点
                        //自己的右孩子置空
                        treeNode.rightChild = null;
                    }
                    //最后把右孩子的父节点断掉
                    rightTreeNode.parent = null;
                }
            }
        }
        size--;
    }

    //找出当前节点下最小节点
    private TreeNode<Y> getMinLeftTreeNode(TreeNode<Y> treeNode) {
        TreeNode<Y> midTreeNode;
        if (treeNode == null) {
            return null;
        } else {
            midTreeNode = treeNode;
            //当当前节点的左孩子不为空 则说明还有比它更小的值，就往下循环 边循环边把midTreeNode赋值为它的左孩子
            while (midTreeNode.leftChild != null) {
                midTreeNode = midTreeNode.leftChild;
            }
        }
        return midTreeNode;
    }
}
