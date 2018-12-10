package com.dragger2.test_searchbinarytree;

/**
 * Description :二叉排序树（查找树、搜索树）
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/10
 */

public class SearchBinaryTree {
    //根节点
    TreeNode root;

    /**
     * 树节点 里面包含左右孩子，父节点，值
     */
    public static class TreeNode {
        int item;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode parent;

        private TreeNode(int item) {
            this.item = item;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }
    }

    /**
     * 插入值
     *
     * @param item
     * @return
     */
    public TreeNode put(int item) {
        //如果根节点为空，就把添加的值指向根节点
        if (root == null) {
            //新建一个树
            TreeNode newTreeNode = new TreeNode(item);
            root = newTreeNode;
            return newTreeNode;
        }
        //把父类定义出来
        TreeNode parent = null;
        TreeNode treeNode = root;
        //找到要插入的值的位置
        while (treeNode != null) {
            //先把父类定义成自己 这样当treeNode赋值时，parent就回跟着自己走了
            parent = treeNode;
            //规则：如果比根节点小，放左边，大就右边
            if (item < treeNode.item) {
                treeNode = treeNode.leftChild;
            } else if (item > treeNode.item) {
                treeNode = treeNode.rightChild;
            } else {  //这里相等就直接返回了
                return treeNode;
            }
        }
        //新建一个树
        TreeNode newTreeNode = new TreeNode(item);
        //再次判断这颗新树要存放的位置
        if (item < parent.item) {
            parent.leftChild = newTreeNode;
        } else {
            parent.rightChild = newTreeNode;
        }
        newTreeNode.parent = parent;
        return newTreeNode;
    }

    /**
     * 中序遍历
     *
     * @param root 树的根节点
     */
    public void midOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        //LDR
        midOrderTraverse(root.leftChild);
        System.out.print(root.item + " ");
        midOrderTraverse(root.rightChild);
    }

    /**
     * 查找值
     *
     * @param item
     * @return
     */
    public TreeNode searchNode(int item) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        while (node != null) {
            if (item == node.item) {
                return node;
            } else if (item < node.item) {
                node = node.leftChild;
            } else {
                node = node.rightChild;
            }
        }
        return null;
    }

    /**
     * 删除树节点
     *
     * @param node
     */
    public void delNode(TreeNode node) {
        //如果node是null 就直接返回
        if (node == null){
            return;
        } else{
            //先定义出自己的父树 方便下面调用
            TreeNode parent = node.parent;
            //1.被删除的节点是叶子
            if (node.leftChild == null && node.rightChild == null) {
                //这里如果自己的父节点是空 则说明自己就是根节点，同时下面没有东西
                if (parent == null) {
                    root = null;
                } else {
                    //判断自己是父节点的左孩子还是右孩子
                    if (parent.leftChild == node) {
                        parent.leftChild = null;
                    } else {
                        parent.rightChild = null;
                    }
                }
                //最后把自己的父节点指向空 这里是双向指针 所以两头都要指向null
                node.parent = null;
            } else if (node.leftChild != null && node.rightChild == null) {    //被删除的节点只有一个左孩子
                //如果自己是根节点 就把根节点指向自己左孩子就行了 然后左孩子父节点置空
                if (parent == null) {
                    root = node.leftChild;
                    node.leftChild.parent = null;
                } else {
                    //先判断自己是父节点的左孩子还是右孩子，然后把父节点的某孩子赋值为自己的左孩子
                    if (parent.leftChild == node) {   //左孩子
                        parent.leftChild = node.leftChild;
                    } else {
                        parent.rightChild = node.leftChild;
                    }
                    //孩子父节点重新指向
                    node.leftChild.parent = parent;
                    //断开自己
                    node.parent = null;
                }
            } else if (node.leftChild == null && node.rightChild != null) {    //被删除的节点只有一个右孩子
                if (parent == null) {
                    root = node.rightChild;
                    node.rightChild.parent = null;
                } else {
                    if (parent.leftChild == node) {
                        parent.leftChild = node.rightChild;
                    } else {
                        parent.rightChild = node.rightChild;
                    }
                    node.rightChild.parent = parent;
                    node.parent = null;
                }
            } else {    //被删除的节点有两个孩子
                //取自己右孩子的最小值 2种情况
                //一种是自己右孩子有左孩子 然后先找出自己右孩子下的最小值 进行登基··
                if (node.rightChild.leftChild != null) {
                    //获取自己下面最小的值
                    TreeNode midNode = getMinLeftTreeNode(node.rightChild);
                    //把要删除的item赋值为最小值的item
                    node.item = midNode.item;
                    //这里又有2个情况
                    //最小值还有右孩子
                    if (midNode.rightChild != null) {
                        //把最小值的右孩子的父节点指向自己的父节点
                        midNode.rightChild.parent = midNode.parent;
                        //最小值的父节点的左孩子指向自己的右孩子
                        midNode.parent.leftChild = midNode.rightChild;
                    } else {  //最小值是叶子了
                        //最小值的父节点的左孩子为null
                        midNode.parent.leftChild = null;
                    }
                    //最小值自己断开
                    midNode.parent = null;
                } else {  //一种是自己的右孩子没有左孩子了 （自己右孩子就是比自己大  然后所有右孩子里最小的值）
                    //自己的右孩子
                    TreeNode rightNode = node.rightChild;
                    //把要删除的item赋值为自己右孩子的item
                    node.item = rightNode.item;
                    //这里又有2个情况
                    //自己的右孩子还有右孩子
                    if (rightNode.rightChild != null) {
                        //把自己的右孩子的右孩子的父节点指向自己
                        rightNode.rightChild.parent = node;
                        //自己的右孩子指向自己的右孩子的右孩子
                        node.rightChild = rightNode.rightChild;
                    } else {  //自己右孩子是叶子了
                        //把自己的右孩子为null
                        node.rightChild = null;
                    }
                    //自己的右孩子断开
                    rightNode.parent = null;
                }
            }
        }
    }

    //获取当前节点下的最小值
    private TreeNode getMinLeftTreeNode(TreeNode node) {
        TreeNode midTreeNode;
        if (node == null) {
            return null;
        } else {
            midTreeNode = node;
            while (midTreeNode.leftChild != null) {
                midTreeNode = midTreeNode.leftChild;
            }
        }
        return midTreeNode;
    }
}
