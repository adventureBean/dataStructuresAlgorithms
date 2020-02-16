package com.atguigu.linkedList;

import java.util.Stack;

/**
 * //演示Stack的基本使用
 * @Author: adventureBean
 * @Date： 2020/2/11
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        //出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }

    }

}
