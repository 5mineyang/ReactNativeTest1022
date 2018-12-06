package com.dragger2.reactnativetest1022;

import org.junit.Test;

/**
 * Description :
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/11/27
 */

public class ExampleUnitTestJava {
    private int count = 0;      //汉诺塔移动步数

    @Test
    public void changePosition() {
        int a = 10;
        int b = 20;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ",b=" + b);
    }

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
        for (int i : array2) {
            System.out.print(i + " ");
        }

        //3
        int array3[] = new int[] {3, 2, 5, 8, 1, 9, 4, 6, 7};
        //        for (int i : array3) {
        //            System.out.print(i + " ");
        //        }
        System.out.println("\n");
        //先冒泡排序后选择 3-5个数据可以用
        nubbleAfterSelectSort(array3);
        for (int i : array3) {
            System.out.print(i + " ");
        }

        //4
        int array4[] = new int[] {3, 2, 5, 8, 1, 9, 4, 6, 7};
        //        for (int i : array3) {
        //            System.out.print(i + " ");
        //        }
        System.out.println("\n");
        //先选择排序后冒泡 3-5个数据可以用
        selectAfterBubbleSort(array4);
        for (int i : array4) {
            System.out.print(i + " ");
        }
    }

    //冒泡排序
    public void bubbleSort(int array[]) {
        //看看for循环一共走了多少遍
        int numOut = 0;
        //看看满足条件的走了多少遍
        int numIn = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                numOut++;
                //进行比较 前面的比后面的大就换位置
                if (array[j] > array[j + 1]) {
                    numIn++;
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

        System.out.println("冒泡法：for循环一共走了" + numOut + "遍，实际换位的次数为：" + numIn);
    }

    //选择排序
    public void selectSort(int array[]) {
        //看看for循环一共走了多少遍
        int numOut = 0;
        //看看满足条件的走了多少遍
        int numIn = 0;
        //起始下标
        int index;

        for (int i = 0; i < array.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < array.length; j++) {
                numOut++;
                //进行比较 当前数组第一个和后面的比较 后面的比它小 就记录下标
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            if (index != i) {
                numIn++;
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            }
        }

        System.out.println("选择法：for循环一共走了" + numOut + "遍，实际换位的次数为：" + numIn);
    }

    //先冒泡排序后选择
    public void nubbleAfterSelectSort(int array[]) {
        //看看for循环一共走了多少遍
        int numOut = 0;
        //看看满足条件的走了多少遍
        int numIn = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            boolean flag = true;
            for (int j = 0; j < i; j++) {
                numOut++;
                //进行比较 前面的比后面的大就换位置
                if (array[j] > array[j + 1]) {
                    numIn++;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = false;
                }
            }
            //优化循环次数
            if (flag) {
                break;
            } else {
                //进行选择排法排序
                //起始下标
                int index;
                for (int ii = 0; ii < array.length - 1; ii++) {
                    index = ii;
                    for (int jj = ii + 1; jj < array.length; jj++) {
                        numOut++;
                        //进行比较 当前数组第一个和后面的比较 后面的比它小 就记录下标
                        if (array[index] > array[jj]) {
                            index = jj;
                        }
                    }
                    if (index != ii) {
                        numIn++;
                        int temp = array[ii];
                        array[ii] = array[index];
                        array[index] = temp;
                    }
                }
            }
        }

        System.out.println("先冒泡排序后选择法：for循环一共走了" + numOut + "遍，实际换位的次数为：" + numIn);
    }

    //先选择排序后冒泡
    public void selectAfterBubbleSort(int array[]) {
        //看看for循环一共走了多少遍
        int numOut = 0;
        //看看满足条件的走了多少遍
        int numIn = 0;
        //起始下标
        int index;

        for (int i = array.length - 1; i >= 0; i--) {
            index = i;
            for (int j = i - 1; j >= 0; j--) {
                numOut++;
                //进行比较 当前数组最后一个和前面的比较 前面的比它大 就记录下标
                if (array[index] < array[j]) {
                    index = j;
                }
            }
            if (index != i) {
                numIn++;
                int temp = array[i];
                array[i] = array[index];
                array[index] = temp;
            } else {
                //进行冒泡排法排序
                for (int ii = i; ii >= 0; ii--) {
                    boolean flag = true;
                    for (int jj = 0; jj < ii; jj++) {
                        numOut++;
                        //进行比较 前面的比后面的大就换位置
                        if (array[jj] > array[jj + 1]) {
                            numIn++;
                            int temp = array[jj];
                            array[jj] = array[jj + 1];
                            array[jj + 1] = temp;
                            flag = false;
                        }
                    }
                    //优化循环次数
                    if (flag) {
                        break;
                    }
                }
            }
        }

        System.out.println("先选择排序后冒泡法：for循环一共走了" + numOut + "遍，实际换位的次数为：" + numIn);
    }

    @Test
    public void urlExtract() {
        //        String getUrl = "<p><img src='https://www.eshikao.com/ueditor/php/upload/image/20181122/1542880503205195.jpg'/></p>";
        //        try {
        //            URL url = new URL(getUrl);
        //            System.out.println(url.toString());
        //        } catch (MalformedURLException e) {
        //            e.printStackTrace();
        //        }

        //        recursioTest(3);
        //        System.out.println(face(5));
        hanoi(16, 1, 2, 3);
        System.out.println("一共需要" + count + 1 + "步");
    }

    //测试递归思想
    public void recursioTest(int i) {
        System.out.println(i);
        if (i < 0) {
            return;
        }
        recursioTest(i - 1);
        System.out.println(i);
    }

    //return i*i-1*i-2...
    public int face(int i) {
        if (i <= 1) {
            return 1;
        }
        return i * face(i - 1);
    }

    //fibonacciSequence数列 斐波那契数列
    public int fibonacciSequence(int i) {
        if (i == 1 || i == 2) {
            return 1;
        }
        return fibonacciSequence(i - 1) * fibonacciSequence(i - 2);
    }

    /**
     * 汉诺塔原则
     *
     * @param num    从多少个开始
     * @param start  第一个柱子
     * @param middle 第二个柱子
     * @param end    第三个柱子
     */
    public void hanoi(int num, int start, int middle, int end) {
        if (num <= 1) {
            System.out.println(start + "柱--->" + end + "柱");
        } else {
            hanoi(num - 1, start, end, middle);
            System.out.println(start + "柱--->" + end + "柱");
            count = count + 1;
            hanoi(num - 1, middle, start, end);
        }
    }
}
