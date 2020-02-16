package com.atguigu.dataStructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: adventureBean
 * @Date： 2020/2/14
 */
public class polandNotation {

    public static void main(String[] args) {

        //中缀表达式转后缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中序表达式对应的List=" + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后序表达式对应的List=" + suffixExpressionList);
        System.out.printf("expression=%d", calculate(suffixExpressionList)); // ?




        //定义一个逆波兰表达式
//        String suffixExpression = "30 4 + 5 * 6 - ";
//
//        List<String> list = getListString(suffixExpression);
//        System.out.println("list:" + list);
//
//        int result = calculate(list);
//        System.out.println("计算的结果是=" + result);

    }


    /**
     * 将得到的中缀表达式对应的List => 后缀表达式对应的List
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<>();//符号栈
        //使用List替代Stack
        List<String> s2 = new ArrayList<>();//存储中间的结果
        //遍历ls
        for (String item : ls) {
            //如果是一个数，入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                //如果是右括号")",则依次弹出s1栈顶的运算符，并压入s2直到遇到右括号为止，并将这对括号丢弃
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();//将"("弹出s1栈，消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中
                //进行运算符优先级比较
                while (s1.size() != 0 && Operation.getPriority(s1.peek()) >= Operation.getPriority(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }

        }
        //将s1中剩余的运算符依次弹出并加入到s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//因为是存放到List中，按顺序输出即可
    }


    /**
     * 将中缀表达式转存到List中方便操作
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个list存放中缀表达式对应的内容
        ArrayList<String> list = new ArrayList<>();
        int i = 0;//指针用来遍历中缀表达式字符串
        String str;//多位数的拼接
        char c;
        do {
            //如果c非数字
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;//指针后移
            } else {//如果是数(考虑多位数)
                str = "";//
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;

    }

    //将逆波兰表达式的每一个数据放入到ArrayList中
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String element : split) {
            list.add(element);
        }
        return list;
    }

    /**
     * 1.从左到右扫描，将数压入栈
     * 2.遇到运算符，弹出两个数，并计算值，再压入栈
     * 3.继续扫描将数压入栈
     * 4.扫描到运算符，弹出两个数与运算符操作，计算出的值压入栈
     * 5.继续扫描将数压入栈
     * 6.最后扫描到运算符，弹出两个数与运算符操作，计算出的值压入栈的到最终结果
     *
     * @param list
     * @return
     */
    public static int calculate(List<String> list) {
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历list
        for (String item : list) {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配多位数
                //入栈
                stack.push(item);
            } else {
                //pop出两个数并运算，再运算
                int number2 = Integer.parseInt(stack.pop());
                int number1 = Integer.parseInt(stack.pop());
                int result = 0;
                if ("+".equals(item)) {
                    result = number1 + number2;
                } else if ("-".equals(item)) {
                    result = number1 - number2;
                } else if ("*".equals(item)) {
                    result = number1 * number2;
                } else if ("/".equals(item)) {
                    result = number1 / number2;
                } else {
                    throw new RuntimeException("运算符有误！");
                }
                //把result入栈
                stack.push("" + result);
            }
        }

        return Integer.parseInt(stack.pop());
    }


}

//编写一个类Operation
class Operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    //返回对应的优先级的数字
    public static int getPriority(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符！");
                break;
        }
        return result;
    }

}