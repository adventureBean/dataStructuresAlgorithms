package com.atguigu.dataStructures.stack;


import java.util.Scanner;

/**
 * @Author: adventureBean
 * @Date： 2020/2/13
 */
public class LinkedStackDemo {

    public static void main(String[] args) {

        LinkedStack stack = new LinkedStack();
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
                    StackFrame stackFrame = new StackFrame(value);
                    stack.push(stackFrame);
                    break;
                case "pop":
                    try {
                        stack.pop();
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

//定义栈(链表实现)
class LinkedStack {
    //初始化一个头节点，不存放具体数据
    private StackFrame head = new StackFrame(0);
//    private StackFrame top = head;//top表示栈顶，初始化为head

    public StackFrame getHead() {
        return head;
    }

    //栈空
    public boolean isEmpty() {
        if (head.next == null) {
            return true;
        }
        return false;
    }

    //入栈-push
    public void push(StackFrame value) {
        StackFrame top = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (top.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            top = top.next;
        }
        //将最后这个节点的next指向新的节点
        top.next = value;
    }

    //出栈
    public void pop() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空！没有数据！");
        }
        StackFrame temp = head.next;
        while (true) {
            //当栈中只有一个栈帧时
            if (head.next.next == null) {
                System.out.printf("出栈的数据为 %d\n", head.next.value);
                head.next = null;
                break;
            }
            //当栈中有多的栈帧时
            if (temp.next.next == null) {
                System.out.printf("出栈的数据为 %d\n", temp.next.value);
                temp.next = null;
                break;
            }
        }
    }

    //显示栈的数据
    public void list() {
        if (isEmpty()) {
            //抛出异常
            throw new RuntimeException("栈空！没有数据！");
        }
        //创建一个变量来辅助遍历
        StackFrame temp = head.next;
        int count = 0;
        while (true) {
            //输出节点的信息
            //判断是否到链表最后
            System.out.printf("stack[%d]=%d\n", count++, temp.value);
            if (temp.next == null) {
                break;
            }
            //将temp后移
            temp = temp.next;
        }
    }
}


//定义栈帧
class StackFrame {
    public int value;
    public StackFrame next;

    public StackFrame() {
    }

    public StackFrame(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public StackFrame getNext() {
        return next;
    }

    public void setNext(StackFrame next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackFrame{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}


