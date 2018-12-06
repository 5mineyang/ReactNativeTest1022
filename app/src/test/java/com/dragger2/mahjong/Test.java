package com.dragger2.mahjong;

import java.util.LinkedList;

/**
 * Description :麻将排序练习
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/11/29
 */

public class Test {

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
        System.out.println(list);
        radixSort(list);
        System.out.println(list);
    }

    //麻将排序
    public void radixSort(LinkedList<Mahjong> list) {
        //先创建9个链表
        LinkedList randList[] = new LinkedList[9];
        //分别初始化
        for (int i = 0; i < randList.length; i++) {
            randList[i] = new LinkedList<Mahjong>();
        }
        //当list里size大于0 就一直循环删除
        while (list.size() > 0) {
            Mahjong m = list.remove();
            //装进相应的数字链表里 也相当于排序
            randList[m.rank - 1].add(m);
        }
        //排列好在把9个链表添加到list里去
        for (int i = 0;i<randList.length;i++){
            list.addAll(randList[i]);
        }

        //现在里面数据已经按从小到大排列好了 再把它们按颜色分类
        //创建3个链表
        LinkedList suitList[] = new LinkedList[3];
        for (int i = 0; i < suitList.length; i++) {
            suitList[i] = new LinkedList();
        }
        //和上面操作一样 按suit进行分类 分别装进每个链表中
        while (list.size() > 0) {
            Mahjong m = list.remove();
            suitList[m.suit - 1].add(m);
        }
        //排列好在把3个链表添加到list里去
        for (int i = 0;i<suitList.length;i++){
            list.addAll(suitList[i]);
        }
    }
}
