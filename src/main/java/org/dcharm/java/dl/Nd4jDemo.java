package org.dcharm.java.dl;

import org.nd4j.linalg.api.memory.MemoryWorkspace;
import org.nd4j.linalg.api.memory.conf.WorkspaceConfiguration;
import org.nd4j.linalg.api.memory.enums.AllocationPolicy;
import org.nd4j.linalg.api.memory.enums.LearningPolicy;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.accum.distances.CosineSimilarity;
import org.nd4j.linalg.cpu.nativecpu.NDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create by qiangwang on 2018/8/30
 */
public class Nd4jDemo {
    private static final Logger logger = LoggerFactory.getLogger(Nd4jDemo.class);

    public static void main(String[] args) {

//        INDArray labels = Nd4j.zeros(1, LEARNSTRING_CHARS_LIST.size(), LEARNSTRING.length);
        INDArray array1 = Nd4j.zeros(1, 10);
        INDArray array2 = Nd4j.zeros(1, 10);
        Transforms.cosineSim(array1, array2);
        System.out.println(array1.isAttached());
        System.out.println(array2.isAttached());

        // we create config with 10MB memory space preallocated
        WorkspaceConfiguration initialConfig = WorkspaceConfiguration.builder()
                .initialSize(10 * 1024L * 1024L)
                .policyAllocation(AllocationPolicy.STRICT)
                .policyLearning(LearningPolicy.NONE)
                .build();


//        INDArray arrays = Nd4j.mean();
//        INDArray arrays = Nd4j.create(10, 20);

        INDArray result = null;

        System.out.println("---------------------------");

        INDArray array12 = Nd4j.create(new float[]{2,2});
        System.out.println(array12.mmul(array12.transpose()));

        System.out.println("---------------------------");

        // we use
        try(MemoryWorkspace ws = Nd4j.getWorkspaceManager().getAndActivateWorkspace(initialConfig, "SOME_ID")) {
            // now, every INDArray created within this try block will be allocated from this workspace pool
            INDArray array = Nd4j.rand(1, 10);
            INDArray array3 = Nd4j.rand(2, 10);
            INDArray vStack = Nd4j.vstack(array, array3);
            INDArray mean1 = Nd4j.mean(vStack, 0);
            INDArray mean2 = Nd4j.mean(vStack, 1);
            System.out.println(vStack);
            System.out.println(mean1);
            System.out.println(mean2);

            System.out.println("------------------------------");

            System.out.println(array.mmul(array.transpose()));


            // easiest way to see if this array is attached to some workspace. We expect TRUE printed out here.
            logger.info("Array attached? {}", array.isAttached());

            // please note, new array mean will be also attached to this workspace
            INDArray mean = array.mean(1);

            /**
             * PLEASE NOTE: if after doing some operations on the workspace, you want to bring result away from it, you should either leverage it, or detach
             */

            result = mean.detach();
        }

        // Since we've detached array, we expect FALSE printed out here. So, result array is managed by GC now.
        logger.info("Array attached? {}", result.isAttached());
    }
}
