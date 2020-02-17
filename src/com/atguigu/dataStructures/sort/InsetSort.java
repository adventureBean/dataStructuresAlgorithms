package com.atguigu.dataStructures.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: adventureBean
 * @Date： 2020/2/17
 */
public class InsetSort {

    public static void main(String[] args) {

        //测试插入排序的速度,给80000个随机数
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 999999);
        }

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String date1 = formatter1.format(LocalDateTime.now());
        System.out.println("排序前:" + date1);

        insertSort(arr);

        String date2 = formatter1.format(LocalDateTime.now());
        System.out.println("排序后:" + date2);
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];//定义待插入的数
            int insertIndex = i - 1;//insertValue前一个数的下标
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//将大于被插入的数后移
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;//while循环退出，找到插入的位置
            }
        }
    }

}
