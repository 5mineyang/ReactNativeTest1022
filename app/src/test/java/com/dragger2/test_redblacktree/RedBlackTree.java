package com.dragger2.test_redblacktree;

import java.util.LinkedList;

/**
 * Description :红黑树
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/21
 */

public class RedBlackTree<Y extends Comparable<Y>> {
    private static final boolean RED = true;   //红黑树颜色
    private static final boolean BLACK = false;
    TreeNode<Y> root;   //根节点
    public int size;

    /**
     * 节点（里面有二叉排序树的一些东西，还有一个变量color，用来代表红色或黑色）
     *
     * @param <Y>
     */
    public class TreeNode<Y extends Comparable<Y>> {
        Y item;
        TreeNode<Y> left;
        TreeNode<Y> right;
        TreeNode<Y> parent;
        boolean color = BLACK;  //创建的时候默认黑色，添加就修改为红色

        public TreeNode(Y item, TreeNode<Y> parent) {
            this.item = item;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * 添加数据
     *
     * @param item
     * @return
     */
    public boolean addRedBlackTree(Y item) {
        TreeNode<Y> t = root;
        if (item == null) {
            throw new NullPointerException("Dare to enter null value?");
        }
        //根节点为空 插入的值直接赋值为根节点
        if (t == null) {
            root = new TreeNode<>(item, null);
            size = 1;
            return true;
        } else {
            TreeNode<Y> parent = null;
            //先找到要插入的位置
            while (t != null) {
                parent = t;
                //如果插入的item比现在位置的值小
                if (item.compareTo(t.item) < 0) {
                    t = t.left;
                } else if (item.compareTo(t.item) > 0) {
                    t = t.right;
                } else {  //相等就直接返回
                    return false;
                }
            }
            //现在的parent就是你要插入的节点
            TreeNode<Y> treeNode = new TreeNode<>(item, parent);
            if (item.compareTo(parent.item) < 0) {
                parent.left = treeNode;
            } else {
                parent.right = treeNode;
            }
            //到这里就插入完毕了 然后执行红黑树平衡操作
            fixAfterInsertion(treeNode);
            size++;
            return true;
        }
    }

    /**
     * 删除值
     *
     * @param item
     */
    public boolean removeRedBlackTree(Y item) {
        //根据值找出节点
        TreeNode<Y> treeNode = searchTreeNode(item);
        if (treeNode == null) {
            return false;
        }
        //这里有3种情况
        //1.如果node的左右孩子都不为空
        if (treeNode.left != null && treeNode.right != null) {
            //先找出右孩子下最小的节点
            TreeNode<Y> midTreeNode = getNextTreeNode(treeNode);
            //要删除的节点的值赋值为右孩子最小节点
            treeNode.item = midTreeNode.item;
            //把指针指向最小孩子
            treeNode = midTreeNode;
        }
        TreeNode<Y> child = treeNode.left != null ? treeNode.left : treeNode.right;
        //2.如果node只有一个孩子（随便左右）
        if (child != null) {
            //删除节点是根节点
            if (treeNode.parent == null) {
                root = child;
            } else if (treeNode.parent.left == treeNode) {  //删除节点是父节点的左孩子
                treeNode.parent.left = child;
            } else {
                treeNode.parent.right = child;
            }
            child.parent = treeNode.parent;
            //彻底清除node
            treeNode.parent = treeNode.left = treeNode.right = null;
            //如果删除节点是黑色 就要进行节点修复了
            if (treeNode.color == BLACK) {
                fixAfterDeletion(child);
            }
        } else if (treeNode.parent == null) {  //删除节点是根节点
            root = null;
        } else {  //3.node没有孩子
            if (treeNode.color == BLACK) {
                //删除平衡修复
                fixAfterDeletion(treeNode);
            }
            //平衡修复后当前节点的父节点不是空
            if (treeNode.parent != null) {
                if (treeNode.parent.left == treeNode) {
                    treeNode.parent.left = null;
                } else {
                    treeNode.parent.right = null;
                }
                treeNode.parent = null;
            }
        }
        size--;
        return true;
    }

    /**
     * 中序遍历（从小到大查找）
     *
     * @param root
     */
    public void midOrderTraverse(TreeNode<Y> root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.left);
        if(root.color){
            System.out.print("[");
        }
        System.out.print(root.item);
        if(root.color){
            System.out.print("] ");
        }else{
            System.out.print(" ");
        }
        midOrderTraverse(root.right);
    }

    /**
     * 横向查询所有树
     *
     * @param root
     */
    public void showAllRedBlackLTree(TreeNode<Y> root) {
        LinkedList<TreeNode<Y>> list = new LinkedList<>();
        //入队
        list.offer(root);
        while (!list.isEmpty()) {
            //直接出队 输出
            TreeNode<Y> node = list.pop();
//            System.out.print(node.color);
            if (node.color){
                System.out.print("[");
            }
            System.out.print(node.item);
            if (node.color){
                System.out.print("] ");
            }else{
                System.out.print(" ");
            }
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }

    /**
     * ============================================================== 修复大法 ==============================================================
     */

    /**
     * 红黑树插入平衡修复
     *
     * @param node
     */
    private void fixAfterInsertion(TreeNode<Y> node) {
        //插入的节点都调为红色的
        node.color = RED;
        //如果插入的父节点是黑的，就不管它 直接插入即可
        //否则一直进来调整
        while (node != null && node != root && node.parent.color == RED) {
            //如果父节点是爷爷节点的左孩子
            if (node.parent.parent.left == node.parent) {
                //先把你的叔叔节点定义出来 下面要用
                TreeNode<Y> u = node.parent.parent.right;
                //这里有三种情况
                //1.爷爷节点的右孩子是红色的 即你的叔叔（当然了不能为空）
                if (u != null && u.color == RED) {
                    //把你的父亲和叔叔变黑，然后爷爷变红
                    node.parent.color = BLACK;
                    u.color = BLACK;
                    node.parent.parent.color = RED;
                    //再把节点node移到爷爷身上，这里出循环后下次就会进第二步了
                    node = node.parent.parent;
                } else {  //叔叔变黑了。。。
                    //2.当前节点是父节点的右孩子（有可能一开始插入就成立，有可能经过上面的变幻之后会成立！）
                    if (node.parent.right == node) {
                        //这里讲node移到父亲身上，然后根据自己（原来的父亲）进行左移大法
                        node = node.parent;
                        leftRotate(node);
                    }
                    //3.当前节点是父亲的左孩子（有可能一开始就成立，有可能经过上面的第一步和第二部转换而成！）
                    //将父节点涂黑，爷爷节点涂红，然后根据爷爷节点进行右旋
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rightRotate(node.parent.parent);
                }
            } else {  //父节点是爷爷节点的右孩子 维鲁斯反向q见过没？ 这里也反向就o了！
                TreeNode<Y> u = node.parent.parent.left;
                //1.叔叔是红色
                if (u != null && u.color == RED) {
                    node.parent.color = BLACK;
                    u.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                } else {
                    //2.当前节点是父节点的左孩子
                    if (node.parent.left == node) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    //3.当前节点是父节点的右孩子
                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    leftRotate(node.parent.parent);
                }
            }
        }
        //防止进行上面第一步之后node==root了 这时root会变成红色，让它要再变回黑色
        root.color = BLACK;
    }

    /**
     * 红黑树删除平衡修复
     *
     * @param node
     */
    private void fixAfterDeletion(TreeNode<Y> node) {
        while (node != root && node.color == BLACK) {
            //被删除节点是父节点的左孩子
            if (node.parent.left == node) {
                //node的兄弟节点
                TreeNode<Y> b = node.parent.right;
                //1.如果兄弟节点是红色的就先进行换色左旋下
                if (b != null && b.color == RED) {
                    b.color = BLACK;
                    node.parent.color = RED;
                    leftRotate(node.parent);
                    b = node.parent.right;
                }
                //2.如果兄弟节点的左右孩子都是黑色的话
                if ((b == null || b.left == null || b.left.color == BLACK) && (b == null || b.right == null || b.right.color == BLACK)) {
                    if (b != null) {
                        b.color = RED;
                    }
                    node = node.parent;
                } else {
                    //3.如果兄弟节点右孩子是黑的（此时兄弟节点左孩子肯定是红的）
                    if (b.right == null || b.right.color == BLACK) {
                        b.left.color = BLACK;
                        b.color = RED;
                        rightRotate(b);
                        b = node.parent.right;
                    }
                    //4.兄弟节点右孩子是红的
                    //把兄弟节点颜色换成node父节点颜色，父节点变黑，兄弟节点右孩子变黑，然后根据node的父节点进行左旋，node指针指向root
                    b.color = node.parent.color;
                    node.parent.color = BLACK;
                    b.right.color = BLACK;
                    leftRotate(node.parent);
                    node = root;
                }
            } else {    //反之
                //node的兄弟节点
                TreeNode<Y> b = node.parent.left;
                //1.如果兄弟节点是红色的就先进行换色右旋下
                if (b != null && b.color == RED) {
                    b.color = BLACK;
                    node.parent.color = RED;
                    rightRotate(node.parent);
                    b = node.parent.left;
                }
                //2.如果兄弟节点的左右孩子都是黑色的话
                if ((b == null || b.left == null || b.left.color == BLACK) && (b == null || b.right == null || b.right.color == BLACK)) {
                    if (b != null) {
                        b.color = RED;
                    }
                    node = node.parent;
                } else {
                    //3.如果兄弟节点左孩子是黑的（此时兄弟节点右孩子肯定是红的）
                    if (b.left == null || b.left.color == BLACK) {
                        b.right.color = BLACK;
                        b.color = RED;
                        leftRotate(b);
                        b = node.parent.left;
                    }
                    //4.兄弟节点左孩子是红的
                    //把兄弟节点颜色换成node父节点颜色，父节点变黑，兄弟节点左孩子变黑，然后根据node的父节点进行右旋，node指针指向root
                    b.color = node.parent.color;
                    node.parent.color = BLACK;
                    b.left.color = BLACK;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.color = BLACK;
    }

    /**
     * ============================================================== 旋转大法 ==============================================================
     */

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

    /**
     * ============================================================== 查询大法 ==============================================================
     */

    /**
     * 找出node的后继（仅比node大一位的节点）
     *
     * @param node
     * @return
     */
    private TreeNode<Y> getNextTreeNode(TreeNode<Y> node) {
        if (node == null) {
            return null;
        } else if (node.right != null) {    //如果node有右孩子
            TreeNode<Y> nextTreeNode = node.right;
            //一直往左下找 如果有就赋值为左孩子，没有了就返回
            while (nextTreeNode.left != null) {
                nextTreeNode = nextTreeNode.left;
            }
            return nextTreeNode;
        } else {    //没有右孩子就找它的父节点（如果它是父节点的左孩子，那么比它大一位的节点就是父节点）
            TreeNode<Y> parent = node.parent;
            TreeNode<Y> ch = node;
            //当自己的父节点不为空和是父节点的右孩子，就进去往左上走
            while (parent != null && parent.right == ch) {
                ch = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    /**
     * 根据输入的值 返回一个树节点
     *
     * @param item
     * @return
     */
    private TreeNode<Y> searchTreeNode(Y item) {
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
                treeNode = treeNode.left;
            } else {
                treeNode = treeNode.right;
            }
        }
        return null;
    }
}
