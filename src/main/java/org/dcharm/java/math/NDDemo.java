package org.dcharm.java.math;

import org.apache.commons.lang3.ArrayUtils;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.util.ArrayUtil;
import static org.nd4j.linalg.ops.transforms.Transforms.*;

import java.util.ArrayList;

public class NDDemo {
    public static void main(String[] args) {
//        arrayOp();
        compare();

//        for(int i = 0; i < round; i++) {
//            javaOp(array);
//        }
//
//        compare();
//
//        float[][] matrix1 = new float[10000][50];
//        for(int i = 0; i < matrix1.length; i++) {
//            for(int j = 0; j < matrix1[i].length; j++) {
//                matrix1[i][j] = i + j;
//            }
//        }
//        float[][] matrix2 = new float[50][10000];
//        for(int i = 0; i < matrix2.length; i++) {
//            for(int j = 0; j < matrix2[i].length; j++) {
//                matrix2[i][j] = i + j;
//            }
//        }
//
//        long t1 = System.currentTimeMillis();
//        float[][] r = jmOp(matrix1, matrix2);
//        System.out.println(System.currentTimeMillis() - t1);
//
//        float[] tmp = new float[10000 * 50];
//        for(int i = 0; i < tmp.length; i++) {
//            tmp[i] = i;
//        }
//        INDArray nd4 = Nd4j.create(tmp, new int[]{10000, 50});
//        INDArray nd5 = Nd4j.create(tmp, new int[]{50, 10000});
//        t1 = System.currentTimeMillis();
//        nd4.mmul(nd5);
//        System.out.println(System.currentTimeMillis() - t1);
    }

    private static float[][] jmOp(float[][] m1, float[][] m2) {
        float[][] r = new float[m1.length][m2[0].length];
        for(int i = 0; i < m1.length; i++) {
            for(int j  = 0; j < m2[0].length; j++) {
                for(int k = 0; k < m1[i].length; k++) {
                    r[i][j] +=  m1[i][k] * m2[k][j];
                }
//                r[i][j] = 1 / ( 1  + (float)Math.pow(Math.E, -r[i][j]));
            }
        }
        return r;
    }

    private static void compare() {
        float[] array = new float[10000];
        for(int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        INDArray nd = Nd4j.create(array, new int[]{1, array.length});
//        System.out.println(sigmoid(nd));

        long t1 = System.currentTimeMillis();
        int round = 100000;
        for(int i = 0; i < round; i++) {
            ndOp(nd);
        }
        System.out.println(System.currentTimeMillis() - t1);

        t1 = System.currentTimeMillis();
        for(int i = 0; i < round; i++) {
            ndOp(nd);
        }
        System.out.println(System.currentTimeMillis() - t1);
    }

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
