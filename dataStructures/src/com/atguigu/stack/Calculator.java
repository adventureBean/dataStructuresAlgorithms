package com.atguigu.stack;

/**
 * @Author: adventureBean
 * @Date： 2020/2/13
 */
public class Calculator {

    public static void main(String[] args) {

        String expression = "30+2*6-2";
        //创建数栈
        ArrayStack2 numberStack = new ArrayStack2(10);
        //创建符号栈
        ArrayStack2 operatorStack = new ArrayStack2(10);
        //定义相关的变量
        int index = 0;//用于扫描
        int number1 = 0;
        int number2 = 0;
        int operator = 0;
        int result = 0;
        char ch = ' ';//将每次扫描到的char保存到ch
        String keepNumber = "";//用于拼接多位数
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if (operatorStack.isOperator(ch)) {//是运算符
                //判断当前的符号栈是否为空
                if (!operatorStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就从数栈中pop出连个数
                    //再从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        number1 = numberStack.pop();
                        number2 = numberStack.pop();
                        operator = operatorStack.pop();
                        result = numberStack.calculate(number1, number2, operator);
                        //把运算的结果入数栈
                        numberStack.push(result);
                        //然后将当前的操作符入符号栈
                        operatorStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级大于栈中的操作符，直接入符号栈
                        operatorStack.push(ch);
                    }
                } else {
                    //如果为空直接入栈
                    operatorStack.push(ch);
                }
            } else {//如果是数，直接入数栈
                //多位数
                keepNumber += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numberStack.push(Integer.parseInt(keepNumber));
                } else {
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        //如果后一位是操作符，入栈
                        numberStack.push(Integer.parseInt(keepNumber));
                        //重要的!!!!!!, keepNum清空
                        keepNumber = "";
                    }
                }
            }
            //让index + 1，并判断是否扫描到expression最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕，就顺序从数栈和符号栈中pop出相应的数和符号，并运行
        while (true) {
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operatorStack.isEmpty()) {
                break;
            }
            number1 = numberStack.pop();
            number2 = numberStack.pop();
            operator = operatorStack.pop();
            result = numberStack.calculate(number1, number2, operator);
            //把运算的结果入数栈
            numberStack.push(result);
        }
        System.out.printf("表达式:%s = %d", expression, numberStack.pop());
    }

}

//定义一个ArrayStack表示栈
class ArrayStack2 {
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈，存放数据
    private int top = -1;//top表示栈顶，初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    //查看当前栈顶的值
    public int peek() {
        return stack[top];
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

    //返回运算符的优先级，优先级使用数字表示
    //数字越大，优先级越高
    public int priority(int operator) {
        if (operator == '*' || operator == '/') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否为运算符
    public boolean isOperator(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //计算方法
    public int calculate(int number1, int number2, int operator) {
        int result = 0;//计算的结果
        switch (operator) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number2 - number1;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                result = number2 / number1;
                break;
            default:
                break;
        }
        return result;
    }


}