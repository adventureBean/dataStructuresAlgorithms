package com.atguigu.dataStructures.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: adventureBean
 * @Date： 2020/2/17
 */
public class SelectSort {

    public static void main(String[] args) {

        //测试选择排序的速度,给80000个随机数
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 999999);
        }

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String date1 = formatter1.format(LocalDateTime.now());
        System.out.println("排序前:" + date1);

        selectSort(arr);

        String date2 = formatter1.format(LocalDateTime.now());
        System.out.println("排序后:" + date2);

    }

    /**
     * 选择排序
     * 时间复杂度O(n^2)
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//大于假定的最小值
                    min = arr[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }
            //与最小值交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
