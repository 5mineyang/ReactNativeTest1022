package com.dragger2.test_dynamicprogramming;

import java.util.Stack;

/**
 * Description :动态规划
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/25
 */

public class Test {

    @org.junit.Test
    public void DynamicTest() {
        //斐波拉契数列练习
        //        System.out.print(f(50) + " ");
        //        System.out.println();
        //        System.out.print(f1(50));

        //LCS算法
        getLCS("abcbdab", "bdcaba");

        //KMP算法
        System.out.println("\n" + getKMP("ababcabcbababcabacaba", "ababcaba"));
    }

    /**
     * 动态规划的思想
     */
    //1  1  2  3  5  8  13  21  ...... f(n)=f(n-1)+f(n-2)   斐波拉契数列
    //递归完成
    public static double f(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return f(n - 1) + f(n - 2);
        }
    }

    //数组完成
    public static double f1(int n) {
        double result[] = new double[n];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i < result.length; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n - 1];
    }

    /**
     * LCS算法 找出两个字符串里的子串
     *
     * @param str1
     * @param str2
     */
    public void getLCS(String str1, String str2) {
        char s1[] = str1.toCharArray();
        char s2[] = str2.toCharArray();
        int array[][] = new int[str1.length() + 1][str2.length() + 1];
        //使用动态规划填入数据（从1开始 第一行和第一列都为0）
        for (int i = 1; i < array.length; i++) {
            for (int j = 1; j < array[i].length; j++) {
                //数据相等就取左上角一格数据+1
                if (s1[i - 1] == s2[j - 1]) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                } else {  //否则就取左边一格和上边一格里最大的
                    array[i][j] = max(array[i - 1][j], array[i][j - 1]);
                }
            }
        }

        //测试输出
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        //从后往前找到结果
        Stack result = new Stack();
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        while (i >= 0 && j >= 0) {
            if (s1[i] == s2[j]) {
                result.push(s1[i]);
                i--;
                j--;
            } else {
                if (array[i + 1][j] > array[i][j + 1]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        System.out.println("=============");
        //输出
        while (!result.isEmpty()) {
            System.out.print(result.pop() + " ");
        }
    }

    //LCS里返回数组两个里最大的
    private int max(int i1, int i2) {
        return (i1 > i2) ? i1 : i2;
    }

    /**
     * KMP算法 根据dest查找str数据
     *
     * @param str
     * @param dest
     */
    public int getKMP(String str, String dest) {
        //先根据dest推出一个next数组
        int next[] = KMPNext(dest);
        //往后推
        for (int i = 0, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (str.charAt(i) == dest.charAt(j)) {
                j++;
            }
            if (j == dest.length()) {
                return i - j + 1;
            }
        }
        return 0;
    }

    private int[] KMPNext(String dest) {
        int next[] = new int[dest.length()];
        for (int i = 1, j = 0; i < next.length; i++) {
            //3
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            //1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            //2
            next[i] = j;
        }
        return next;
    }
}
