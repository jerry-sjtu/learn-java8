package org.dcharm.java.math;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.util.ArrayUtil;
import static org.nd4j.linalg.ops.transforms.Transforms.*;

import java.util.ArrayList;

public class NDDemo {
    public static void main(String[] args) {
//        arrayOp();
        float[] array = new float[10000];
        for(int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        INDArray nd = Nd4j.create(array, new int[]{1, array.length});

        int round = 100000;
        long t1 = System.currentTimeMillis();
//        for(int i = 0; i < round; i++) {
//            javaOp(array);
//        }
//        System.out.println(System.currentTimeMillis() - t1);

        t1 = System.currentTimeMillis();
        for(int i = 0; i < round; i++) {
            ndOp(nd);
        }
        System.out.println(System.currentTimeMillis() - t1);

        t1 = System.currentTimeMillis();
        for(int i = 0; i < round; i++) {
            ndOp(nd);
        }
        System.out.println(System.currentTimeMillis() - t1);

        float[][] matrix1 = new float[100][2];
        for(int i = 0; i < matrix1.length; i++) {
            for(int j = 0; j < matrix1[i].length; j++) {
                matrix1[i][j] = i + j;
            }
        }
        float[][] matrix2 = new float[2][100];
        for(int i = 0; i < matrix2.length; i++) {
            for(int j = 0; j < matrix2[i].length; j++) {
                matrix2[i][j] = i + j;
            }
        }
    }

//    private static void jmOp(float[][] m1, float[][] m2) {
//        for(int i)
//    }

    private static void javaOp(float[] arr) {
        float[] na = new float[arr.length];
        for(int i = 0; i < arr.length; i++) {
            na[i] = 1 / ( 1  + (float)Math.pow(Math.E, -arr[i]));
        }
    }

    private static void ndOp(INDArray nd) {
        INDArray ndv = sigmoid(nd, false);
    }

    private static void arrayOp() {
        INDArray arr1 = Nd4j.create(new float[]{1,2,3,4},new int[]{2,2});
        System.out.println(arr1);
        arr1.addi(1);
        System.out.println(arr1);
        INDArray arr2 = Nd4j.create(new float[]{5,6,7,8},new int[]{2,2});
        arr1.addi(arr2);
        System.out.println(arr1);
        arr1.getRow(0).addi(1);
        System.out.println(arr1);

        INDArray arr3 = Nd4j.zeros(3,5).addi(10);
        System.out.println(arr3);

        INDArray arr4 = Nd4j.rand(3,5);
        System.out.println(arr4);

        INDArray arr5 = Nd4j.randn(3,5);
        System.out.println(arr5);

        ArrayList<Double> l1 = new ArrayList<Double>();
        l1.add(1.0);
        l1.add(2.0);
        l1.add(3.0);
        l1.add(4.0);
        double[] flat = ArrayUtil.flattenDoubleArray(l1);
        INDArray arr6 = Nd4j.create(flat, new int[]{2,2}, 'c');
        System.out.println(arr6);
    }
}
