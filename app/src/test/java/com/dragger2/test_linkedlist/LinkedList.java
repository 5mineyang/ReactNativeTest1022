package com.dragger2.test_linkedlist;

/**
 * Description :手写双向链表LinkedList
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/11/29
 */

public class LinkedList<Y> {
    private final static String TAG = "LinkedList";
    //头节点
    private Node<Y> first;
    //尾节点
    private Node<Y> last;
    //大小
    int size = 0;

    /**
     * 节点
     * LinkedList都是以一个个节点构成的 单向只有一个指针域（指向某位置） 双向则为两个指针域（分别指向前后）
     *
     * @param <Y> 泛型
     */
    private static class Node<Y> {
        Y item;
        Node<Y> prev;
        Node<Y> next;

        //构造方法
        public Node(Node<Y> prev, Y item, Node<Y> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 添加数据到最后
     *
     * @param item 值
     */
    public void add(Y item) {
        linkedLast(item);
    }

    /**
     * 插入指定位置值
     *
     * @param index 位置
     * @param item  值
     */
    public void add(int index, Y item) {
        //如果输入位置在最后一个
        if (index == size) {
            linkedLast(item);
        } else {
            //当前插入位置的后继
            Node<Y> target = node(index);
            //当前插入位置的前驱
            Node<Y> pre = target.prev;
            //当前准备插的值
            Node<Y> newNode = new Node<>(pre, item, target);
            //pre为null则插入的位置是0 第一个
            if (pre == null) {
                first = newNode;
                //它后面的前驱指定一下
                target.prev = newNode;
            } else {
                //newNode new出来的时候就已经指定了自己的前驱和后继了 现在只要指定它之前的后继和他之后的前驱就行了
                pre.next = newNode;
                target.prev = newNode;
            }
            size++;
        }
    }

    /**
     * 添加一个linkedList对象进来
     *
     * @param linkedList
     */
    public void addAll(LinkedList<Y> linkedList) {
        Y item;
        for (int i = 0; i < linkedList.size; i++) {
            item = linkedList.get(i);
            //一个个添加
            linkedLast(item);
        }
    }

    /**
     * 删除所有值
     */
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 删除某位置值
     *
     * @param index 位置
     */
    public void remove(int index) {
        Node<Y> target = node(index);
        unlinkNode(target);
    }

    /**
     * 修改指定位置值
     *
     * @param index 要修改的位置
     * @param item  修改的值
     */
    public void update(int index, Y item) {
        Node<Y> node = node(index);
        node.item = item;
    }

    /**
     * 查询指定位置数据
     *
     * @param index 下标
     * @return 值
     */
    public Y get(int index) {
        return node(index).item;
    }

    /**
     * 查询某值的下标
     *
     * @param item 值
     * @return 下标
     */
    public int find(Y item) {
        Node<Y> node = first;
        for (int i = 0; i < size; i++) {
            if (node.item == item) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    /**
     * 返回第一个
     *
     * @return 值
     */
    public Y getFirst() {
        return first.item;
    }

    /**
     * 返回最后一个
     *
     * @return 值
     */
    public Y getLast() {
        return last.item;
    }

    //往最后面添加数据
    private void linkedLast(Y item) {
        Node<Y> newNode = new Node<>(last, item, null);
        //之前的最后一个值
        Node<Y> l = last;
        //把last指向当前插入的值
        last = newNode;

        //里面没有数据
        if (l == null) {
            //当前插入的值就是第一个值
            first = newNode;
        } else {
            //之前的值的后继指向插入的值
            l.next = newNode;
        }
        size++;
    }

    //移除某值
    private void unlinkNode(Node<Y> item) {
        //分别定义出当前值的前驱和后继
        Node<Y> pre = item.prev;
        Node<Y> next = item.next;

        //删除分3种情况 一种删除的当前值是第一个 一种是中间 一种是最后一个
        //第一个
        if (pre == null) {
            first = item.next;
        } else {
            //让前一个的后继指向自己的后继 然后自己就被跳过了
            pre.next = item.next;
        }
        //最后一个
        if (next == null) {
            last = item.prev;
        } else {
            //让后一个的前驱指向自己的前驱 然后自己就被跳过了（配合上面的一行代码，相当于删除）
            next.prev = item.prev;
        }
        size--;
    }

    //返回指定位置的值
    private Node<Y> node(int index) {
        //如果输入下标不符合要求就返回null
        if (index < 0 || index >= size) {
            try {
                throw new Exception("yang say the index is wrong! Do you have any comments?");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        //一遍遍遍历 当前传入第几个就返回当前第几个,可以折中查找 如果index小于一半 就从前往后找
        if (index < size >> 1) {
            Node<Y> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<Y> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }
}
