package org.dcharm.java.gbdt;

import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import ml.dmlc.xgboost4j.java.XGBoostError;

import java.util.HashMap;
import java.util.Map;


/**
 * Create by qiangwang on 2018/10/8
 */
public class Tree {
    public static void main(String[] args) throws Exception {
        train();
        test();
    }

    private static void spark() {
    }

    private static void train () throws XGBoostError {
        DMatrix trainMat = new DMatrix("/Users/qiangwang/workspace/xgboost/demo/data/agaricus.txt.train");
        DMatrix testMat = new DMatrix("/Users/qiangwang/workspace/xgboost/demo/data/agaricus.txt.test");

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("eta", 1.0);
        params.put("max_depth", 2);
        params.put("silent", 1);
        params.put("objective", "binary:logistic");

        HashMap<String, DMatrix> watches = new HashMap<String, DMatrix>();
        watches.put("train", trainMat);
        watches.put("test", testMat);


        int nround = 2;
        Booster booster = XGBoost.train(trainMat, params, nround, watches, null, null);
        booster.saveModel("xgb.model");

    }

    private static void test() throws XGBoostError {
        Booster booster = XGBoost.loadModel("xgb.model");
        //3:1 10:1 11:1 21:1 30:1 34:1 36:1 40:1 41:1 53:1 58:1 65:1 69:1 77:1 86:1 88:1 92:1 95:1 102:1 105:1 117:1 124:1
        float[] data = new float[] {1, 0, 1, 0, 1, 0, 1, 1};
        long[] headers = new long[] {1};
        int[] indices = new int[] {3, 10, 11, 21, 30, 34, 36};
//        DMatrix m = new DMatrix(headers, indices, data, DMatrix.SparseType.CSR, headers.length);
//        DMatrix m = new DMatrix("/Users/qiangwang/workspace/xgboost/demo/data/agaricus.txt.test");
        DMatrix m = new DMatrix(data, 2, 4);
        System.out.println(m);
        float[][] predicts = booster.predict(m);
        for(int i = 0; i < predicts.length; i++) {
            for(int j = 0; j < predicts[i].length; j++) {
                System.out.print(predicts[i][j] + " ");
            }
            System.out.println();
        }
    }

}
