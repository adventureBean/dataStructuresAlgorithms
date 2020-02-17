package com.atguigu.dataStructures.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: adventureBean
 * @Date： 2020/2/17
 */
public class BubbleSort {

    public static void main(String[] args) {


        //测试冒泡排序的速度,给80000个随机数
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 999999);
        }

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String date1 = formatter1.format(LocalDateTime.now());
        System.out.println("排序前:" + date1);

        bubbleSort(arr);

        String date2 = formatter1.format(LocalDateTime.now());
        System.out.println("排序后:" + date2);

    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;//临时变量
        boolean flag = false;//标识变量，标识是否进行过交换
        //时间复杂度为O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if (!flag) {//在一趟排序中，一次都没有交换过
                break;
            } else {
                flag = false;
            }
        }
    }

}
