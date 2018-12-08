package com.dragger2.mahjong;


import com.dragger2.test_linkedlist.LinkedList;

/**
 * Description :麻将练习2
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/7
 */

public class Test2 {

    @org.junit.Test
    public void testRadixSort() {
        LinkedList<Mahjong> list = new LinkedList();
        list.add(new Mahjong(3, 1));
        list.add(new Mahjong(2, 3));
        list.add(new Mahjong(3, 7));
        list.add(new Mahjong(1, 1));
        list.add(new Mahjong(3, 8));
        list.add(new Mahjong(2, 2));
        list.add(new Mahjong(3, 2));
        list.add(new Mahjong(1, 3));
        list.add(new Mahjong(3, 9));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        radixSort(list);
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    private void radixSort(LinkedList<Mahjong> list) {
        //先建9个链表 然后初始化，再把相同的数组放入相应的链表里
        LinkedList randList[] = new LinkedList[9];
        for (int i = 0; i < randList.length; i++) {
            randList[i] = new LinkedList<Mahjong>();
        }
        //分别移除list里的值 添加到相应下标的randList里去
        while (list.size() > 0) {
            Mahjong m = list.remove();
            randList[m.rank - 1].add(m);
        }
        //添加完了之后在依次添加到list里
        for (int i = 0; i < randList.length; i++) {
            list.addAll(randList[i]);
        }

        //再根据花色分类
        LinkedList sultList[] = new LinkedList[3];
        for (int i = 0; i < sultList.length; i++) {
            sultList[i] = new LinkedList<Mahjong>();
        }
        while (list.size() > 0) {
            Mahjong m = list.remove();
            sultList[m.suit - 1].add(m);
        }
        //和上面一样 把拍好序的sultList分别添加到list里
        for (int i = 0; i < sultList.length; i++) {
            list.addAll(sultList[i]);
        }
    }
}
