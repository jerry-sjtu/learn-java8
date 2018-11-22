package org.dcharm.java.dl;

import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.parallelism.ParallelInference;
import org.deeplearning4j.parallelism.inference.InferenceMode;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class KerasExample {
    MultiLayerNetwork deepModel = null;
    ParallelInference deepPi = null;
    DpParallelInference dpPi = null;

    public KerasExample() {

    }

    public static void main(String[] args) throws Exception {
        KerasExample obj = null;
        for(int i = 0; i < 10000; i++) {
            if(obj != null) {
                obj.shutdown();
            }
            obj = new KerasExample();
            obj.loadModel();
            Thread.sleep(10000L);
            System.out.println(i);
        }
    }

    private INDArray mpredict(float[] arr) {
        INDArray ia = Nd4j.create(arr);
        return deepModel.output(ia);
    }

    public void shutdown() {
        if(dpPi != null) {
            dpPi.shutdown();
        }
    }

    private INDArray predict(float[] arr) {
        return deepPi.output(arr);
    }

    private INDArray predict2(float[] arr) {
        return dpPi.output(arr);
    }

    private void loadModel() {
        String modelFile = "/Users/qiangwang/Downloads/DL4JHeadlineMixParalleldeep_model_weights";
        try {
            deepModel = KerasModelImport.importKerasSequentialModelAndWeights(modelFile);
//            deepPi = new ParallelInference.Builder(deepModel)
//                    .inferenceMode(InferenceMode.SEQUENTIAL)
//                    .workers(2)
//                    .build();
            if(dpPi != null) {
                dpPi.shutdown();
            }
            dpPi = new DpParallelInference.Builder(deepModel)
                    .inferenceMode(InferenceMode.SEQUENTIAL)
                    .workers(2)
                    .build();
        } catch(Exception  e) {
            e.printStackTrace();
        }
    }
}
