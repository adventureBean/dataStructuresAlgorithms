package com.atguigu.dataStructures.recuresion;

/**
 * @Author: adventureBean
 * @Date： 2020/2/16
 */
public class Queen8 {


    //定义一个max表示共有几个皇后
    int max = 8;
    //定义数组array，保存皇后放置的结果，比如arr={0, 4, 7, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {

        //测试一把 ， 8皇后是否正确
        Queen8 queue8 = new Queen8();
        queue8.check(0);
        System.out.printf("一共有%d种解法", count);
        System.out.printf("\n一共判断冲突%d次", judgeCount); // 1.5w


    }

    /**
     * 放置第n个皇后
     * @param n
     */
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //把当前皇后n置于第一列
            array[n] = i;
            //判断当前放置的第n个皇后到i列时，是否冲突
            if (judge(n)) {//不冲突
                //接着放n+1个皇后，开始递归
                check(n + 1);
            }

        }
    }

    /**
     * 检测放置第n个皇后是否与前面已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //1.array[i] == array[n] 判断第n个皇后是否和前面的n-1个皇后在同一列
            //2.Math.abs(n - i) == Math.abs(array[n] - array[i]) 判断第n个皇后是否和第i个皇后斜率相同(x1-x2=y1-y2)
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将皇后摆放的位置输出
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


}
