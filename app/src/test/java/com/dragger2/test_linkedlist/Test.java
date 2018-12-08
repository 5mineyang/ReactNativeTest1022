package com.dragger2.test_linkedlist;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/11/29
 */

public class Test {

    @org.junit.Test
    public void customLinkedTest() {
        LinkedList<Integer> linkedList = new LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(0, 3);                       //3 1 2 3
        linkedList.add(0, 3);                       //3 3 1 2 3
        linkedList.add(1, 66);                      //3 66 3 1 2 3
        //        linkedList.remove(0);                       //66 3 1 2 3
        //        linkedList.remove(2);                       //3 66 1 2 3
        //        linkedList.remove(linkedList.size - 1);     //3 66 3 1 2

        for (int i = 0; i < linkedList.size(); i++) {
            if (i != linkedList.size() - 1) {
                System.out.print(linkedList.get(i) + ",");
            } else {
                System.out.println(linkedList.get(i));
            }
        }

        //测试
        System.out.println("size=" + linkedList.size());
        int testNum = 3;
        System.out.println("first= " + linkedList.getFirst());
        //        System.out.println("第"+testNum+"个="+linkedList.get(testNum));
        //        System.out.println(testNum+"在linkedLIst的第 "+linkedList.find(testNum)+" 个!");

        LinkedList<Integer> linkedList2 = new LinkedList();
        linkedList2.add(1);
        linkedList2.add(2);
        linkedList2.add(333);
        linkedList.addAll(linkedList2);
        System.out.print("\naddAll后的值：");
        for (int i = 0; i < linkedList.size(); i++) {
            if (i != linkedList.size() - 1) {
                System.out.print(linkedList.get(i) + ",");
            } else {
                System.out.println(linkedList.get(i));
            }
        }

        linkedList.clear();
        System.out.println("clear()后size=" + linkedList.size());

        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(7);
        System.out.print("\n重现添加后的值：");
        for (int i = 0; i < linkedList.size(); i++) {
            if (i != linkedList.size() - 1) {
                System.out.print(linkedList.get(i) + ",");
            } else {
                System.out.println(linkedList.get(i));
            }
        }

        linkedList.update(1,6);
        System.out.print("\n修改后的值：");
        for (int i = 0; i < linkedList.size(); i++) {
            if (i != linkedList.size() - 1) {
                System.out.print(linkedList.get(i) + ",");
            } else {
                System.out.println(linkedList.get(i));
            }
        }
    }
}
