package org.dcharm.java;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);

    public static void main(String[] args) throws Exception {
        System.out.println(NumberUtils.isDigits("1212"));
        System.out.println(NumberUtils.isCreatable("12.12"));
        System.out.println(NumberUtils.isCreatable("0.1212"));
        System.out.println(NumberUtils.isCreatable("0ad"));

        try {
            System.out.println("------------------");
            ExecutorService service = Executors.newSingleThreadExecutor();
            SumTask sumTask = new SumTask(20);
            Future<Integer> future = service.submit(sumTask);
            Integer result = future.get();
            Object obj = future.get();
            System.out.println(result);
        }
        catch (Exception e) {
            logger.info(e.getMessage(), e);
        }
    }

    static class SumTask implements Callable<Integer> {
        private int num = 0;
        public SumTask(int num){
            this.num = num;
        }
        @Override
        public Integer call() throws Exception {
            Map<String,Integer> m = new HashMap<String, Integer>();
            return m.get("a") + 1;
//            int result = 0;
//            for(int i=1;i<=num;i++){
//                result+=i;
//            }
//            return result;
        }
    }
}
