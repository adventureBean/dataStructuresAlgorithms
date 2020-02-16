package src.com.atguigu.sparsearray;

import org.junit.Test;

import java.io.*;

/**
 * @Author: adventureBean
 * @Date： 2020/2/10
 */
public class SparseArray {

    @Test
    public void test() {
        //创建一个原的二维数组 11*11
        //0：表示没有棋子，1表示黑子 2表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 1;
        //输出原始的二维数组
        System.out.println("原始的二维数组(chessArr1)");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转稀疏数组
        //1.先遍历二维数组 得到非0的数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr1[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr1[0][0] = 11;
        sparseArr1[0][1] = 11;
        sparseArr1[0][2] = sum;

        //遍历二维数组，将非0的值存放到sparseArr中
        int count = 0;//count用于记录是第几个非0数据
        for (int row = 0; row < 11; row++) {
            for (int column = 0; column < 11; column++) {
                if (chessArr1[row][column] != 0) {
                    count++;
                    sparseArr1[count][0] = row;
                    sparseArr1[count][1] = column;
                    sparseArr1[count][2] = chessArr1[row][column];
                }
            }
        }
        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为(sparseArr1)：");
        for (int i = 0; i < sparseArr1.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr1[i][0], sparseArr1[i][1], sparseArr1[i][2]);
        }

        //3.将稀疏数组写入到磁盘
        ObjectOutputStream oos = null;
        try {
            //1.提供FileWriter的对象，用于数据的写出
            oos = new ObjectOutputStream(new FileOutputStream("sparseArr.dat"));

            //2.写出的操作
            oos.writeObject(sparseArr1);
            oos.flush();//刷新操作
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流资源的关闭
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //4.将稀疏数组读入到内存中
        ObjectInputStream ois = null;
        int[][] sparseArr2 = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("sparseArr.dat"));

            Object obj = ois.readObject();
            sparseArr2 = (int[][]) obj;

            System.out.println("\n将稀疏数组读入到内存中(sparseArr2):");

            for (int i = 0; i <= sum; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.printf("%d\t", sparseArr2[i][j]);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        //5.将稀疏数组恢复成原始的二维数组
        //5.1先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //5.2 读取稀疏数组后几行的数据(从第二行开始)，
        for (int i = 1; i < sparseArr2.length; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        //恢复后的二维数组
        System.out.println("\n恢复后的二维数组(chessArr2)");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


    }

}
