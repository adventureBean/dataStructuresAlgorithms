package com.atguigu.dataStructures.sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: adventureBean
 * @Date： 2020/2/17
 */
public class ShellSort {

    public static void main(String[] args) {

        //测试希尔排序的速度,给80000个随机数
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 999999);
        }
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String date1 = formatter1.format(LocalDateTime.now());
        System.out.println("排序前:" + date1);

        shellSort2(arr);

        String date2 = formatter1.format(LocalDateTime.now());
        System.out.println("排序后:" + date2);
    }


    /**
     * 希尔排序 针对有序序列在插入时采用交换法
     * @param arr
     */
    public static void shellSort1(int []arr){
        int temp = 0;
        int count = 0;
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序 针对有序序列在插入时采用移动法。
     * @param arr
     */
    public static void shellSort2(int []arr){
        //增量gap，并逐步缩小增量
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i=gap;i<arr.length;i++){
                int j = i;
                int temp = arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0 && temp<arr[j-gap]){
                        //移动法
                        arr[j] = arr[j-gap];
                        j-=gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
}
