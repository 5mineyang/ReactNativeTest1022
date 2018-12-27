package com.dragger2.test_dynamicprogramming;

/**
 * Description :弗洛伊德算法 求矩阵最短路径
 * <p>
 * Author:yang
 * <p>
 * Email:1318392199@qq.com
 * <p>
 * Date: 2018/12/27
 */

public class Floyd {
    private static int I = Integer.MAX_VALUE;

    //邻接距阵
    public static int[][] d = new int[][] {
        {0, 2, 1, 5},
        {2, 0, 4, I},
        {1, 4, 0, 3},
        {5, I, 3, 0}
    };

    //记录路径
    public static int[][] p = new int[][] {
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3},
        {0, 1, 2, 3}
    };

    @org.junit.Test
    public void test() {
        floyd();
        printArray(d);
        System.out.println("=======================");
        printArray(p);
        printShortPath();
    }

    private void printArray(int[][] d) {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[i].length; j++) {
                System.out.print(d[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    private void floyd() {
        for (int k = 0; k < d.length; k++) {
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d.length; j++) {
                    if (d[i][j] > d[i][k] + d[k][j]) {
                        d[i][j] = d[i][k] + d[k][j];
                        //再记录到p路径矩阵里
                        p[i][j] = p[i][k];
                    }
                }
            }
        }
    }

    //输出路径
    private void printShortPath() {
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d.length; j++) {
                if (i != j) {
                    System.out.print("v" + i + " -> v" + j + " weight：" + d[i][j] + " path：v" + i);
                    int k = p[i][j];
                    while (k != j) {
                        System.out.print(" -> v" + k);
                        k = p[k][j];
                    }
                    System.out.print(" -> v" + j + "\n");
                }
            }
        }
    }
}
