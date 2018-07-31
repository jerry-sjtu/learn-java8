package org.dcharm.java.math;

import org.apache.commons.lang3.ArrayUtils;
import org.deeplearning4j.nn.conf.WorkspaceMode;
import org.nd4j.linalg.api.memory.MemoryWorkspace;
import org.nd4j.linalg.api.memory.conf.WorkspaceConfiguration;
import org.nd4j.linalg.api.memory.enums.AllocationPolicy;
import org.nd4j.linalg.api.memory.enums.LearningPolicy;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.nd4j.linalg.util.ArrayUtil;
import static org.nd4j.linalg.ops.transforms.Transforms.*;

import java.util.ArrayList;

public class NDDemo {
    private static INDArray itemArray;

    public static void main(String[] args) {

//        arrayOp();
//        compare();
//
//        for(int i = 0; i < 1000; i++) {
//            concatOp();
//        }

        int itemNum = 300;
        int featureNum = 100;
        float[] userVec = new float[featureNum];
        for(int i = 0; i < featureNum; i++) {
            userVec[i] = 0.01f;
        }
        float[][] itemMatrix = new float[featureNum][itemNum];
        for(int i = 0; i < itemMatrix.length; i++) {
            for (int j = 0; j < itemMatrix[i].length; j++) {
                itemMatrix[i][j] = i;
                System.out.print(i + " ");
            }
            System.out.println("");
        }
        float[] score = new float[itemNum];
        for(int i = 0; i < score.length; i++) {
            score[i] = i * 0.003f;
        }

        WorkspaceConfiguration initialConfig = WorkspaceConfiguration.builder()
                .initialSize(100 * 1024L * 1024L)
                .policyAllocation(AllocationPolicy.STRICT)
                .policyLearning(LearningPolicy.NONE)
                .build();

        INDArray uv = Nd4j.create(userVec);


        try(MemoryWorkspace ws = Nd4j.getWorkspaceManager().getAndActivateWorkspace(initialConfig, "SOME_ID")) {
            long t1 = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                    bestList1(userVec, itemMatrix, score);
            }
            System.out.println(System.currentTimeMillis() - t1);
            System.out.println("------------------------");
        }
        long t2 = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) {
            bestList2(userVec, itemMatrix, score);
        }
        System.out.println(System.currentTimeMillis() - t2);
        System.out.println("------------------------");

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

    private static void bestList1(float[] userVec, float[][] itemMatrix, float[] utilityVec) {
        INDArray uv = Nd4j.create(userVec);
        INDArray sv = Nd4j.create(utilityVec);
        if(itemArray == null) {
            itemArray = Nd4j.create(itemMatrix);
        }

//        System.out.println(itemArray);
//        itemArray.addi(1f);
        itemArray.sum(0);
//        INDArray sum = itemArray.sum(1).addi(1);
//        System.out.println(sum);
//        INDArray logV = Transforms.log(sum);
//        System.out.println(logV);
//        INDArray result = uv.mmul(logV);
//        System.out.println(uv);
//        System.out.println(result);
//        float r = sv.sumNumber().floatValue() + result.getFloat(0);
//        System.out.println(sv.sumNumber().floatValue() + result.getFloat(0));
    }

    private static void bestList2(float[] userVec, float[][] itemMatrix, float[] utilityVec) {
        float[] v1 = new float[itemMatrix.length];
        for(int i = 0; i < itemMatrix.length; i++) {
            v1[i] = 1;
        }
        for(int i = 0; i < itemMatrix.length; i++) {
            for(int j = 0; j < itemMatrix[i].length; j++) {
                v1[i] += itemMatrix[i][j];
            }
        }
//        for(int i = 0; i < v1.length; i++) {
//            System.out.print(v1[i] + " ");
//        }
//        System.out.println();
        float sum = 0;
        for(int i = 0; i < itemMatrix.length; i++) {
            sum += userVec[i] * Math.log(v1[i]);
        }
//        System.out.println(sum);
        for(int i = 0; i < utilityVec.length; i++) {
            sum += utilityVec[i];
        }
//        System.out.println(sum);
    }


    private static void concatOp() {
        INDArray a1 = Nd4j.zeros(10, 10);
        INDArray a2 = Nd4j.zeros(10, 20);
        a2.put(0, 1, 1);
        INDArray a3 = Nd4j.concat(1, a1, a2);
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
