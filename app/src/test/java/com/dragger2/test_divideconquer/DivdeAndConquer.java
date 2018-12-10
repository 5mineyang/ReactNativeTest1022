package com.dragger2.test_divideconquer;

import org.junit.Test;

/**
 * Description :分治法练习
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/6
 */

public class DivdeAndConquer {
    @Test
    public void Test() {
        int arrayTest[] = new int[] {10, 17, 8, 11, 13, 6, 14, 11, 4, 17, 10};
        //快排法
        quickSort(arrayTest, 0, arrayTest.length - 1);
        System.out.print("快排法：");
        for (int i = 0; i < arrayTest.length; i++) {
            System.out.print(arrayTest[i] + " ");
        }
        int key = 13;
        System.out.println("\n二分查找：" + key + "在arrayTest数组里的第 " + twoPointsSelect(arrayTest, 0, arrayTest.length - 1, key) + " 个下标");

        int arrayTest2[] = new int[] {10, 17, 8, 11, 13, 6, 14, 11, 4, 17, 10};
        mergeSort(arrayTest2, 0, arrayTest2.length - 1);
        System.out.print("\n归并法：");
        for (int i = 0; i < arrayTest2.length; i++) {
            System.out.print(arrayTest2[i] + " ");
        }
    }

    /**
     * 二分查找 前提，必须顺序的
     *
     * @param array     数组
     * @param fromIndex 低位置
     * @param toIndex   高位置
     * @param key       要查找的key
     */
    private int twoPointsSelect(int array[], int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int heigh = toIndex;
        while (low <= heigh) {
            //取中间
            int mid = (low + heigh) >> 1;
            //如果key小于中间值，就把heigh赋值为mid-1
            if (key < array[mid]) {
                heigh = mid - 1;
            } else if (key > array[mid]) {   //如果key大于中间值，就把heigh赋值为mid+1
                low = mid + 1;
            } else {    //否则就直接返回 说明当前的key==中间值
                return mid;
            }
        }
        return -(low + 1);
    }

    /**
     * 快速排序法
     * 思想：用2个指标分别移动 一个指向最前面，一个指向最后面，然后比较2个指标，前面的比后面的小，就把后面的指标往前移
     * 如果比后面大，就把大的值赋值给前面指标位置的值，然后前指标向后移动，依次类推。。（前序排序）
     *
     * @param array 数组
     * @param begin 开始位置
     * @param end   终点位置
     */
    private void quickSort(int array[], int begin, int end) {
        if (end - begin <= 0) {
            return;
        }
        int low = begin;
        int heigh = end;
        int x = array[begin];
        boolean direction = true;  //定义一个变量判断前面的移动还是后面的移动
        //goto 循环移到相应位置
        L:
        while (low < heigh) {
            if (direction) {
                for (int i = heigh; i > low; i--) {
                    if (x >= array[i]) {
                        //把当前low指针位置的值赋值为大于x的值 然后把heigh指针前移
                        array[low++] = array[i];
                        heigh = i;
                        direction = !direction;
                        continue L;
                    }
                }
                heigh = low;
            } else {
                for (int j = low; j < heigh; j++) {
                    if (x < array[j]) {
                        array[heigh--] = array[j];
                        low = j;
                        direction = !direction;
                        continue L;
                    }
                }
                low = heigh;
            }
        }
        //最后把2个指针重合的位置赋值为x
        array[low] = x;
        //递归根据x分2个位置分别调用自己
        quickSort(array, begin, low - 1);
        quickSort(array, low + 1, end);
    }

    /**
     * 归并法排序
     * 思想：这里先把得到的数组用递归一遍遍分成2组，然后分到最后不能分的时候再调用merge方法 这时候再慢慢出栈
     * 这时候出栈的数组肯定是被分成2个顺序的数组出栈的，循环调用merge方法排序就行了（后续排序）
     *
     * @param array 数组
     * @param left  左边位置
     * @param right 右边位置
     */
    private void mergeSort(int array[], int left, int right) {
        if (left == right) {
            return;
        } else {
            int mid = (left + right) / 2;
            //利用递归的后续法排序
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid + 1, right);
        }
    }

    /**
     * 归并法（这里必须2边从小到大）
     *
     * @param array 数组
     * @param left  左边位置
     * @param mid   中间位置
     * @param right 右边位置
     */
    private void merge(int array[], int left, int mid, int right) {
        int leftSize = mid - left;
        int rightSize = right - mid + 1;
        //定义2个数组
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];
        //分别放值
        for (int i = left; i < mid; i++) {
            leftArray[i - left] = array[i];
        }
        for (int j = mid; j <= right; j++) {
            rightArray[j - mid] = array[j];
        }
        //合并
        int l = 0;
        int r = 0;
        int k = left;   //array数组的起始下标
        while (l < leftSize && r < rightSize) {
            if (leftArray[l] < rightArray[r]) {
                array[k++] = leftArray[l++];
            } else {
                array[k++] = rightArray[r++];
            }
        }
        //如果上面循环过后另一边的l或r还小于自己相应的位置 就把它们分别放入array数组的后面
        while (l < leftSize) {
            array[k++] = leftArray[l++];
        }
        while (r < rightSize) {
            array[k++] = rightArray[r++];
        }
    }
}
