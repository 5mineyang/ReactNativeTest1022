package com.dragger2.test_divideconquer;

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

public class DivdeAndConquer2 {
    @Test
    public void Test() {
        int arrayTest[] = new int[] {10, 17, 8, 11, 13, 6, 14, 7, 4, 20, 9};
        //快排法
        quickSort(arrayTest, 0, arrayTest.length);
        System.out.print("快排法：");
        for (int i = 0; i < arrayTest.length; i++) {
            System.out.print(arrayTest[i] + " ");
        }
        int key = 13;
        System.out.println("\n二分查找：" + key + "在arrayTest数组里的第 " + twoPointsSelect(arrayTest, key) + " 个下标");

        int arrayTest2[] = new int[] {10, 17, 8, 11, 13, 6, 14, 7, 4, 20, 9};
        //归并法
        mergeSort(arrayTest2, 0, arrayTest2.length - 1);
        System.out.print("\n归并法：");
        for (int i = 0; i < arrayTest2.length; i++) {
            System.out.print(arrayTest2[i] + " ");
        }
    }

    /**
     * 快排法
     * 思想：定义2个指针和一个前指针值  一个指向最前面，一个指向最后面，然后前指针的值和后指针指向的值进行比较，先那值和后面的进行比，如果前面的小
     * 就把后面的指针向前移动一位，如果大，就把大指针位置的值给小指针位置，然后把小指针向后移动，最后，当2个指针合并后，就把当前选中的值赋值给指针...
     * 然后进行前序递归查询
     */
    private void quickSort(int array[], int start, int end) {
        if (start >= end) {
            return;
        }
        int low = start;
        int heigh = end - 1;
        int x = array[start];   //进行比较的值
        boolean direction = true;   //确定方向的 前指针移动还是后指针移动
        L:
        while (low < heigh) {
            if (direction) {
                for (int i = heigh; i > low; i--) {
                    //如果x比右边的大
                    if (x > array[i]) {
                        array[low] = array[i];
                        low++;
                        heigh = i;
                        direction = !direction;
                        continue L;
                    }
                }
                heigh = low;
            } else {
                for (int j = low; j < heigh; j++) {
                    if (x < array[j]) {
                        array[heigh] = array[j];
                        heigh--;
                        low = j;
                        direction = !direction;
                        continue L;
                    }
                }
                low = heigh;
            }
        }
        //循环结束结束一遍结束 然后把重合的low赋值为x
        array[low] = x;
        //递归调用自己左边
        quickSort(array, start, low);
        //递归调用自己右边
        quickSort(array, low + 1, end);
    }

    //二分查找(前提是顺序)
    private int twoPointsSelect(int array[], int key) {
        int low = 0;
        int heigh = array.length - 1;
        while (low <= heigh) {
            //找出中间位置
            int mid = (low + heigh) / 2;
            if (key < array[mid]) { //左边再次进行二分查找
                heigh = mid - 1;
            } else if (key > array[mid]) { //右边再次进行二分查找
                low = mid + 1;
            } else {  //返回位置
                return mid;
            }
        }
        return -1;
    }

    //归并法
    private void mergeSort(int array[], int left, int right) {
        if (left == right) {
            return;
        } else {
            int mid = (right + left) >> 1;
            //后序递归
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid + 1, right);
        }
    }

    /**
     * 归并法 （这里必须条件是：start到mid的位置是从小到大顺序，mid到end位置是从小到大顺序）
     * 思想：通过起始位置、中间位置、结束位置 分为左数组和右数组，然后分别给左右数组进行比较，最后把array数组排序
     *
     * @param array 数组
     * @param left  起始位置
     * @param mid   中间位置
     * @param right 结束位置
     */
    private void merge(int array[], int left, int mid, int right) {
        int leftSize = mid - left;
        int rightSize = right - mid + 1;
        //定义左右数组
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];
        //分别放值
        for (int i = left; i < mid; i++) {
            leftArray[i - left] = array[i];
        }
        for (int j = mid; j <= right; j++) {
            rightArray[j - mid] = array[j];
        }
        //把左右数组起始下标定义出来
        int l = 0;
        int r = 0;
        //数组起始下标
        int k = left;
        //先进行一遍循环 只要一边超过当前数组长度了 就退出
        while (l < leftSize && r < rightSize) {
            //左边的比右边的小
            if (leftArray[l] < rightArray[r]) {
                array[k] = leftArray[l];
                k++;
                l++;
            } else {  //左边的比右边的大
                array[k] = rightArray[r];
                k++;
                r++;
            }
        }
        //如果上面循环过后另一边的l或r还小于自己相应的位置 就把它们分别放入array数组的后面
        while (l < leftSize) {
            array[k] = leftArray[l];
            k++;
            l++;
        }
        while (r < rightSize) {
            array[k] = rightArray[r];
            k++;
            r++;
        }
    }
}
