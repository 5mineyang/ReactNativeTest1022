package com.dragger2.test_divideconquer;

import org.junit.Test;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/8
 */

public class DivdeAndConquer3 {
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

    //快排法
    private void quickSort(int array[], int begin, int end) {
        if (begin >= end) {
            return;
        }
        //指出2个指针
        int low = begin;
        int heigh = end - 1;
        //进行比较的值
        int x = array[begin];
        //默认高指针往左移动
        boolean direction = true;
        L:
        while (low < heigh) {
            //高指针往左移动
            if (direction) {
                //从高向低遍历
                for (int i = heigh; i > low; i--) {
                    if (x > array[i]) {
                        array[low] = array[i];
                        low++;
                        heigh = i;
                        direction = !direction;
                        continue L;
                    }
                }
                //最后把高指针赋值为低指针
                heigh = low;
            } else {  //低指针向右移动
                //从低向高遍历
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
        //重合指针赋值
        array[low] = x;
        //前序递归
        quickSort(array, begin, low);
        quickSort(array, low + 1, end);
    }

    //快排法
    private void quickSort1(int array[], int begin, int end) {
        if (begin >= end) {
            return;
        }
        //高低指针
        int low = begin;
        int heigh = end - 1;
        //起始比较值
        int x = array[begin];
        //低指针移动还是高指针移动（默认高指针先向左移动）
        boolean direction = true;
        //goto while循环可以回到这边
        L:
        while (low < heigh) {
            //高指针向左移动
            if (direction) {
                //从高到低遍历
                for (int i = heigh; i > low; i--) {
                    //如果比较值x比高指针大 就把低指针的值赋值为高指针的值
                    if (x > array[i]) {
                        array[low] = array[i];
                        low++;
                        heigh = i;
                        direction = !direction;
                        continue L;
                    }
                }
                //最后把高指针指向低指针
                heigh = low;
            } else {  //低指针向右移动
                //从低到高遍历
                for (int j = low; j < heigh; j++) {
                    //如果比较值x比低指针小 就把高指针的值赋值为低指针的值
                    if (x < array[j]) {
                        array[heigh] = array[j];
                        heigh--;
                        low = j;
                        direction = !direction;
                        continue L;
                    }
                }
                //最后把低指针指向高指针
                low = heigh;
            }
        }
        //最后把重合的指针赋值为比较值x
        array[low] = x;
        //前序递归调用自己 按刚才的low中间值 进行左右递归
        quickSort(array, begin, low);
        quickSort(array, low + 1, end);
    }

    //二分查找（前提：顺序）
    private int twoPointsSelect(int array[], int key) {
        int low = 0;
        int heigh = array.length - 1;
        while (low <= heigh) {
            int mid = (low + heigh) >> 1;
            if (key < array[mid]) {
                heigh = mid - 1;
            } else if (key > array[mid]) {
                low = mid + 1;
            } else {
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
            int mid = (left + right) >> 1;
            //后序递归遍历(先把数组分成左右，左右的分，分到一组只有1个时候，依次调用merge方法排序)
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid + 1, right);
        }
    }

    //这里array数组如果是奇数，mid默认会往后取
    private void merge(int array[], int left, int mid, int right) {
        int leftSize = mid - left;
        int rightSize = right - mid + 1;
        //定义左右数组 赋值
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];
        for (int i = left; i < mid; i++) {
            leftArray[i - left] = array[i];
        }
        for (int j = mid; j <= right; j++) {
            rightArray[j - mid] = array[j];
        }
        //定义左右起始位置
        int l = 0;
        int r = 0;
        //array数组起始位置
        int k = left;
        while (l < leftSize && r < rightSize) {
            if (leftArray[l] < rightArray[r]) {
                array[k] = leftArray[l];
                k++;
                l++;
            } else {
                array[k] = rightArray[r];
                k++;
                r++;
            }
        }
        //一边数组装满了，另一边循环装进去就行了
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
