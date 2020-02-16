package com.atguigu.dataStructures.linkedList;

/**
 * @Author: adventureBean
 * @Date： 2020/2/12
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        System.out.println("双向链表的测试！");

        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "李逵", "黑旋风");
        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero5);
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero4);
//        doubleLinkedList.add(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("\n修改后的链表！");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("\n删除修改后的链表！");
        doubleLinkedList.list();
    }
}


//创建一个双向
class DoubleLinkedList {
    //初始化一个头节点，不存放具体数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    //显示链表
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //创建一个变量来辅助遍历
        HeroNode2 temp = head.next;
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

    /**
     * 添加一个节点到双向链表最后
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        //创建辅助变量temp遍历链表
        HeroNode2 temp = head;
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
        heroNode.pre = temp;

    }

    /**
     * 根据编号添加
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode) {
        //通过一个辅助变量来找到添加的位置,位于添加位置的前一个位置
        HeroNode2 temp = head;
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
        // 插入到链表中
        heroNode.next = temp.next;
        heroNode.pre = temp;
        if (temp != null) { // 判断是否在链表尾
            temp.next = heroNode;
            temp.next.pre = heroNode;
        }
    }

    /**
     * 修改一个节点的内容
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        //判断是否空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点, 根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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


    /**
     * 从双向链表中删除一个节点
     *
     * @param no
     */
    public void del(int no) {
        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除！");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;//标志是否找到代删除节点
        while (true) {
            if (temp == null) {//已经到节点的最后
                break;
            }
            if (temp.no == no) {
                //找到了待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag) {//找到
            //可以删除
            temp.pre.next = temp.next;
            //如果是最后一个节点，不需要执行下面这句话，
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的节点：%d\t不存在!", no);
        }
    }

}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    public HeroNode2() {
    }

    public HeroNode2(int no, String name, String nickname) {
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