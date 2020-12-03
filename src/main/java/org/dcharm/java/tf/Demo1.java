package org.dcharm.java.tf;

/**
 * Create by qiangwang on 5/2/20
 */
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Arrays;
import java.util.List;

import org.tensorflow.framework.DataType;
import org.tensorflow.framework.TensorProto;
import org.tensorflow.framework.TensorShapeProto;

import tensorflow.serving.Model;
import tensorflow.serving.Predict;
import tensorflow.serving.PredictionServiceGrpc;

public class Demo1 {
    public static void main(String[] args) {
        //按照文档可以快速部署本地服务：https://www.tensorflow.org/tfx/serving/docker
        //可以看到模型的签名 http://localhost:8501/v1/models/half_plus_two/metadata
        List<Float> floatList =Arrays.asList(1.0f,2.0f,0.5f);
        ManagedChannel channel = ManagedChannelBuilder.forAddress("0.0.0.0", 8500).usePlaintext(true).build();
        //这里还是先用block模式
        PredictionServiceGrpc.PredictionServiceBlockingStub stub = PredictionServiceGrpc.newBlockingStub(channel);
        //创建请求
        Predict.PredictRequest.Builder predictRequestBuilder = Predict.PredictRequest.newBuilder();
        //模型名称和模型方法名预设
        Model.ModelSpec.Builder modelSpecBuilder = Model.ModelSpec.newBuilder();
        modelSpecBuilder.setName("half_plus_two");
        modelSpecBuilder.setSignatureName("regress_x2_to_y3");
//        modelSpecBuilder.setSignatureName("regress");
        predictRequestBuilder.setModelSpec(modelSpecBuilder);
        //设置入参,访问默认是最新版本，如果需要特定版本可以使用tensorProtoBuilder.setVersionNumber方法
        TensorProto.Builder tensorProtoBuilder = TensorProto.newBuilder();
        tensorProtoBuilder.setDtype(DataType.DT_FLOAT);
        TensorShapeProto.Builder tensorShapeBuilder = TensorShapeProto.newBuilder();

        tensorShapeBuilder.addDim(TensorShapeProto.Dim.newBuilder().setSize(3));
        tensorShapeBuilder.addDim(TensorShapeProto.Dim.newBuilder().setSize(1));
        tensorProtoBuilder.setTensorShape(tensorShapeBuilder.build());
        tensorProtoBuilder.addAllFloatVal(floatList);
        predictRequestBuilder.putInputs("inputs", tensorProtoBuilder.build());
//        predictRequestBuilder.putInputs("x2:0", tensorProtoBuilder.build());
        //访问并获取结果
        Predict.PredictResponse predictResponse = stub.predict(predictRequestBuilder.build());
        org.tensorflow.framework.TensorProto result=predictResponse.toBuilder().getOutputsOrThrow("outputs");
        System.out.println("预测值是:"+result.getFloatValList());

    }
}
