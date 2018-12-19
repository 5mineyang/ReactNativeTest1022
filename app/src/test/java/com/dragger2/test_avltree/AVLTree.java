package com.dragger2.test_avltree;

import java.util.LinkedList;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/19
 */

public class AVLTree<Y extends Comparable<Y>> {
    TreeNode<Y> root;   //根节点
    int size = 0;
    //平衡因子
    private static final int LH = 1;    //左子树大
    private static final int RH = -1;   //右子树大
    private static final int EH = 0;    //左右一样大

    /**
     * 节点
     *
     * @param <Y>
     */
    public class TreeNode<Y extends Comparable<Y>> {
        Y item; //值
        int balance = 0;    //平衡因子
        TreeNode<Y> left;   //左子树
        TreeNode<Y> right;  //右子树
        TreeNode<Y> parent; //父节点

        private TreeNode(Y item, TreeNode<Y> parent) {
            this.item = item;
            this.parent = parent;
            this.balance = 0;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 插入数据
     *
     * @param item
     */
    public boolean addAVLTree(Y item) {
        TreeNode<Y> t = root;
        //根节点为空就直接赋值
        if (t == null) {
            root = new TreeNode<>(item, null);
            root.balance = 0;
            size = 1;
            return true;
        } else {
            TreeNode<Y> parent = null;
            //先找到要插入的位置
            while (t != null) {
                parent = t;
                //item比t的位置小
                if (item.compareTo(t.item) < 0) {
                    t = t.left;
                } else if (item.compareTo(t.item) > 0) {
                    t = t.right;
                } else {  //相等就直接返回false
                    return false;
                }
            }
            //现在parent就是你要插入的位置
            TreeNode<Y> treeNode = new TreeNode<>(item, parent);
            if (item.compareTo(parent.item) < 0) {
                parent.left = treeNode;
            } else {
                parent.right = treeNode;
            }
            //节点已经放到了树上 现在往上修复平衡因子
            while (parent != null) {
                if (item.compareTo(parent.item) < 0) {
                    parent.balance++;
                } else {
                    parent.balance--;
                }
                //如果平衡因子是0 则说明添加的树正好把位置补正了 直接退出
                if (parent.balance == 0) {
                    break;
                }
                //如果修复过后因子等于2或-2了 就去转树
                if (parent.balance == 2) {
                    leftBalance(parent);
                    break;
                } else if (parent.balance == -2) {
                    rightBalance(parent);
                    break;
                } else {  //继续往上走
                    parent = parent.parent;
                }
            }
        }
        size++;
        return true;
    }

    /**
     * 查询所有树
     *
     * @param root
     */
    public void showAllAVLTree(TreeNode<Y> root) {
        LinkedList<TreeNode<Y>> list = new LinkedList<>();
        //入队
        list.offer(root);
        while (!list.isEmpty()) {
            //直接出队 输出
            TreeNode<Y> node = list.pop();
            System.out.print(node.item + " ");
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }

    /**
     * 左边的平衡因子修复
     *
     * @param t
     */
    private void leftBalance(TreeNode<Y> t) {
        //先获取要转的节点的左孩子 根据它的因子判断是直接右转 还是先左转再右转
        TreeNode<Y> tl = t.left;
        switch (tl.balance) {
            case LH:    //直接右转
                rightRotate(t);
                t.balance = EH;
                tl.balance = EH;
                break;
            case RH:    //左转再右转
                //先获取tl的右孩子
                TreeNode<Y> tlr = tl.right;
                //再根据tlr的平衡因子判断他们要修改后的平衡因子
                switch (tlr.balance) {
                    case LH:
                        t.balance = RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case RH:
                        t.balance = EH;
                        tl.balance = LH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    default:
                        break;
                }
                //先左转t的左孩子 在右转t
                leftRotate(t.left);
                rightRotate(t);
                break;
        }
    }

    /**
     * 右边的平衡因子修复
     *
     * @param t
     */
    private void rightBalance(TreeNode<Y> t) {
        TreeNode<Y> tr = t.right;
        switch (tr.balance) {
            case RH:
                leftRotate(t);
                t.balance = EH;
                tr.balance = EH;
                break;
            case LH:
                TreeNode<Y> trl = tr.left;
                switch (trl.balance) {
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        trl.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    default:
                        break;
                }
                rightRotate(t.right);
                leftRotate(t);
                break;
        }
    }

    /**
     * 左旋 分为下面三种情况
     * 注：这里其实不要管y的右子树 只要管他有没有左子树就行了
     * <p>
     * /         x          x          x
     * /       /  \       /  \       /  \
     * /     a    y     a    y     a    y
     * /        /  \        /            \
     * /       b    c      b              c
     *
     * @param x
     */
    private void leftRotate(TreeNode<Y> x) {
        if (x != null) {
            //先取到y
            TreeNode<Y> y = x.right;
            //把x的右孩子指向y的左孩子
            x.right = y.left;
            //如果这里y有左孩子 就把y的左孩子父节点赋值
            if (y.left != null) {
                y.left.parent = x;
            }
            //再把y的父节点赋为x
            y.parent = x.parent;
            //如果x的父节点是null 就说明x是根节点
            if (x.parent == null) {
                root = y;
            } else {
                //这里判断x是父节点的左孩子还是右孩子
                if (x.parent.left == x) {
                    x.parent.left = y;
                } else if (x.parent.right == x) {
                    x.parent.right = y;
                }
            }
            //x作为y左孩子
            y.left = x;
            x.parent = y;
        }
    }

    /**
     * 右旋转
     * <p>
     * /         y          y          y
     * /       /  \       /  \       /  \
     * /     z    a     z    a     z    a
     * /   /  \        /            \
     * /  b    c      b              c
     *
     * @param y
     */
    private void rightRotate(TreeNode<Y> y) {
        if (y != null) {
            TreeNode<Y> z = y.left;
            //step1
            y.left = z.right;
            if (z.right != null) {
                z.right.parent = y;
            }
            //step2
            z.parent = y.parent;
            if (y.parent == null) {
                root = z;
            } else {
                if (y.parent.left == y) {
                    y.parent.left = z;
                } else if (y.parent.right == y) {
                    y.parent.right = z;
                }
            }
            //step3
            z.right = y;
            y.parent = z;
        }
    }
}
