package org.dcharm.java.dl;

import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;

import java.io.File;
import java.io.IOException;

public class KerasExample {
    public static void main(String[] args) {
//        String modelFile = "/Users/qiangwang/.keras/datasets/mnist_model/model.json";
//        String weightFile = "/Users/qiangwang/.keras/datasets/mnist_model/weight.h5";
        String modelFile = "/Users/qiangwang/Downloads/DL4JHeadlineMixParalleldeep_model_weights";
        MultiLayerNetwork nn = loadModel(modelFile);
        System.out.println(nn);
    }

    private static MultiLayerNetwork loadModel(String modelFilename) {
        MultiLayerNetwork model = null;
        try {
//            model = KerasModelImport.importKerasSequentialModelAndWeights(modelFile.getAbsolutePath());
            model = KerasModelImport.importKerasSequentialModelAndWeights(modelFilename);
        } catch(IOException | InvalidKerasConfigurationException | UnsupportedKerasConfigurationException e) {
            e.printStackTrace();
        }

        return model;
    }
}
