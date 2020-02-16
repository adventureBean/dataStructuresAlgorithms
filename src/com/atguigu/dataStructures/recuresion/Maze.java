package com.atguigu.dataStructures.recuresion;

/**
 * @Author: adventureBean
 * @Date： 2020/2/16
 */
public class Maze {

    public static void main(String[] args) {

        //创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        System.out.println("地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        boolean setWay = setWay(map, 1, 1);
        System.out.println("\n"+setWay);
        //输出新的地图，小球的路径
        System.out.println("小球走过的路径：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯小球找路
     * 如果小球能到map[6][5]表示找到出口
     * 约定：当map[i][j]为0表示该点没有走过，1表示墙，2表示通路可以走，3表示该点已经走过但不通
     * 策略：下->右->上->左，如果不通，再回溯
     * @param  map  地图
     * @param i 所在位置(行)
     * @param j 所在位置(列)
     * @return  找到出口返回true，否则false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][1] == 2) {//找到出口
            return true;
        } else {
            if (map[i][j] == 0) {//当前点没有走过
                //策略：下->右->上->左
                map[i][j] = 2;//假定该点可以走通
                if (setWay(map,i+1,j)){
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map,i-1, j)){
                    return true;
                } else if (setWay(map,i,j-1)){
                    return true;
                } else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }

        }
    }

    /**
     * 使用递归回溯小球找路
     * 如果小球能到map[6][5]表示找到出口
     * 约定：当map[i][j]为0表示该点没有走过，1表示墙，2表示通路可以走，3表示该点已经走过但不通
     * 策略：上->右->下->左，如果不通，再回溯
     * @param  map  地图
     * @param i 所在位置(行)
     * @param j 所在位置(列)
     * @return  找到出口返回true，否则false
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][1] == 2) {//找到出口
            return true;
        } else {
            if (map[i][j] == 0) {//当前点没有走过
                //策略：上->右->下->左
                map[i][j] = 2;//假定该点可以走通
                if (setWay2(map,i-1,j)){
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map,i+1, j)){
                    return true;
                } else if (setWay2(map,i,j-1)){
                    return true;
                } else {
                    //说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }

        }
    }
}
