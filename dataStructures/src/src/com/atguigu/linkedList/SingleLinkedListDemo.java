package src.com.atguigu.linkedList;

import java.util.Stack;

/**
 * @Author: adventureBean
 * @Date： 2020/2/10
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {

        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "李逵", "黑旋风");
        HeroNode hero6 = new HeroNode(6, "宋江", "及时雨");
        HeroNode hero7 = new HeroNode(7, "卢俊义", "玉麒麟");
        HeroNode hero8 = new HeroNode(8, "吴用", "智多星");
        HeroNode hero9 = new HeroNode(9, "林冲", "豹子头");
        HeroNode hero10 = new HeroNode(10, "李逵", "黑旋风");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero5);
//        singleLinkedList.add(hero3);
//        singleLinkedList.list();

        //按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero8);
        singleLinkedList.addByOrder(hero6);
        singleLinkedList.addByOrder(hero5);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero9);
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero10);
        singleLinkedList2.addByOrder(hero7);
        System.out.println("链表原始数据");
        singleLinkedList.list();
        singleLinkedList2.list();

        System.out.println("\n测试合并链表");
        HeroNode mergeHead = mergeSingleLinkedList(singleLinkedList.getHead(), singleLinkedList2.getHead());
        SingleLinkedList mergeSingleLinkedList = new SingleLinkedList();
        mergeSingleLinkedList.add(mergeHead);
        mergeSingleLinkedList.list();


        System.out.println("\n测试逆序打印链表！");
        reversePrint(singleLinkedList.getHead());


        //反转链表数据
        System.out.println("\n反转后的链表数据");
        reverseLis(singleLinkedList.getHead());
        singleLinkedList.list();

        //修改节点
        System.out.println("\n修改后！");
        HeroNode newHeroNode = new HeroNode(1, "卢本伟", "伞兵一号");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();

        //删除节点
        System.out.println("\n删除后！");
        singleLinkedList.del(1);
        singleLinkedList.del(5);
        singleLinkedList.list();

        //测试求单链表
        System.out.println("有效的节点个数=：" + getLength(singleLinkedList.getHead()));

        //测试得到倒数第k个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(), 2);
        System.out.println("res = " + res);
    }

    /**
     * 5.合并两个有序的单链表，合并之后的链表依然有序【课后练习.】
     *
     * @param head1
     * @param head2
     */
    public static HeroNode mergeSingleLinkedList(HeroNode head1, HeroNode head2) {
        if(head1.next == null || head2.next == null) {
            return head1.next == null ? head2 : head1;
        }
        HeroNode mergeHead = new HeroNode(0, "", "");
        head1 = head1.next; // 头节点没有数据我们不要
        head2 = head2.next; // 头节点没有数据我们不要
        HeroNode current = mergeHead;

        while (head1 != null && head2 != null) {
            if (head1.no <= head2.no) {
                current.next = head1;
                current = current.next;
                head1 = head1.next;
            } else {
                current.next = head2;
                current = current.next;
                head2 = head2.next;
            }
        }
        if (head1 == null) {
            current.next= head2; // 连接剩余节点
        }
        if (head2 == null) {
            current.next= head1; // 连接剩余节点
        }
        return mergeHead;
    }

    /**
     * 借助栈从头到尾打印单链表
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode current = head.next;
        //将链表的所有节点压入栈
        while (current != null) {
            stack.push(current);
            current = current.next;//current后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 将单链表反转
     *
     * @param head
     */
    public static void reverseLis(HeroNode head) {
        //如果当前列表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode current = head.next;//定义一个辅助的变量，遍历原来的链表
        HeroNode next = null; //指向当前节点的下一个节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (current != null) {
            next = current.next;//先暂时保存当前节点的下一个节点
            current.next = reverseHead.next;//将反转链上的第一个节点指向current的下一个节点
            reverseHead.next = current;//将current节点连接到反转链上
            current = next;//让current后移
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 查找单链表中的倒数第k个节点
     * <p>
     * 1.编写一个方法，接收head节点，同时接收一个index
     * 2.index表示倒数第index个节点
     * 3.先把链表从头到尾遍历，得到链表的总长度getLength
     * 4.得到size后，从链表的第一个遍历(size-index)个，就可以得到
     * 5.如果找到了，则返回该接节点，否则返回null
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点的个数)
        int size = getLength(head);
        //第二次遍历size-index位置，就是倒数第k个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义一个辅助变量， for循环定位到倒数的index
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;

    }


    /**
     * 获取到单链表的节点的个数(如果时带头节点的链表，不统计头节点)
     *
     * @param head 链表的头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }


}

//定义SingleLinkedList管理英雄
class SingleLinkedList {
    //初始化一个头节点，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表 第一种方式：直接添加到链表的尾部
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //创建辅助变量temp遍历链表
        HeroNode temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //第二中方式在添加英雄时，根据排名将英雄插入到指定的位置
    public void addByOrder(HeroNode heroNode) {
        //通过一个辅助变量来找到添加的位置,位于添加位置的前一个位置
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {//找到插入的位置
                break;
            } else if (temp.next.no == heroNode.no) {//编号已经存在
                System.out.printf("准备插入的英雄的编号：%d已经存在了，不能加入！\n", heroNode.no);
                return;
            }
            temp = temp.next;//后移，继续遍历当前列表
        }
        //插入到链表中，temp的后面
        heroNode.next = temp.next;
        temp.next = heroNode;
    }

    //修改节点的信息，根据no编号来修改
    //1.根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; //已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else { //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除节点
    //1.创建一个辅助节点temp指向代删除节点的前一个节点
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;//标志是否找到代删除节点
        while (true) {
            if (temp.next == null) {//已经到节点的最后
                break;
            }
            if (temp.next.no == no) {
                //找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag) {//找到
            //可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的节点：%d\t不存在!", no);
        }
    }


    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //创建一个变量来辅助遍历
        HeroNode temp = head.next;
        while (true) {
            //输出节点的信息
            //判断是否到链表最后
            System.out.println(temp);
            if (temp.next == null) {
                break;
            }
            //将temp后移
            temp = temp.next;
        }
    }

}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    public HeroNode() {
    }

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}






