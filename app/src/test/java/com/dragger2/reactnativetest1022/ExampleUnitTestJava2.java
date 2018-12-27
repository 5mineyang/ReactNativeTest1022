package com.dragger2.reactnativetest1022;

import org.junit.Test;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/7
 */

public class ExampleUnitTestJava2 {
    private int count = 0;      //汉诺塔移动步数

    @Test
    public void testSort() {
        //Arrays自带的排序 会花很多时间复杂度
        //        Arrays.sort(array);

        //1
        int array1[] = new int[] {3, 2, 5, 8, 1, 9, 4, 6, 7};
        System.out.print("原数据：");
        for (int i : array1) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        //顺序法:冒泡排序 3-5个数据可以用
        bubbleSort(array1);
        System.out.print("冒泡法：");
        for (int i : array1) {
            System.out.print(i + " ");
        }

        //2
        int array2[] = new int[] {3, 2, 5, 8, 1, 9, 4, 6, 7};
        //        for (int i : array2) {
        //            System.out.print(i + " ");
        //        }
        System.out.println("\n");
        //顺序法:选择排序 3-5个数据可以用
        selectSort(array2);
        System.out.print("选择法：");
        for (int i : array2) {
            System.out.print(i + " ");
        }
    }

    //冒泡
    private void bubbleSort(int array[]) {
        for (int i = array.length - 1; i >= 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                //前面的比后面大 就换位置
                if (array[j] > array[j + 1]) {
                    //互换位置
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            //优化循环次数
            if (flag) {
                break;
            }
        }
    }

    //选择
    private void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int index = i;  //待会比较位置
            //一轮下来最小的肯定在最左边了 然后再从1位置开始排。。。
            for (int j = i + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    //把下标指向它
                    index = j;
                }
                if (index != i) {
                    int temp = array[i];
                    array[i] = array[index];
                    array[index] = temp;
                }
            }
        }
    }

    @Test
    public void urlExtract() {
        //        recursioTest(3);
        //        System.out.println(face(4));
        hanoi(25, 1, 2, 3);
        System.out.println("一共需要" + count + "步");
    }

    //测试递归思想
    private void recursioTest(int i) {
        System.out.println(i);
        if (i < 0) {
            return;
        }
        recursioTest(i - 1);
        System.out.println(i);
    }


    private int face(int i) {
        if (i <= 1) {
            return 1;
        }
        return i * face(i - 1);
    }

    //汉诺塔
    private void hanoi(int i, int start, int mid, int end) {
        if (i <= 1) {
            System.out.println(start + "==>" + end);
            count = count + 1;
        } else {
            hanoi(i - 1, start, end, mid);
            System.out.println(start + "==>" + end);
            count = count + 1;
            hanoi(i - 1, mid, start, end);
        }
    }
}
