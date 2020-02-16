package com.atguigu.stack;


import java.util.Scanner;

/**
 * @Author: adventureBean
 * @Date： 2020/2/13
 */
public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;//控制是否退出
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("请输入你的选择！");
            System.out.println("show:表示显示栈！");
            System.out.println("exit:退出程序！");
            System.out.println("push:表示添加数到栈！");
            System.out.println("pop:表示从栈取出！");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数！");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int result = stack.pop();
                        System.out.printf("出栈的数据为 %d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + key);
            }
        }
        System.out.println("\n程序退出！");
    }


}

//定义一个ArrayStack表示栈
class ArrayStack {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，存放数据
    private int top = -1;//top表示栈顶，初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //栈空
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈-push
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满！");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空！没有数据！");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //显示栈的数据
    public void list() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空！没有数据！");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}






