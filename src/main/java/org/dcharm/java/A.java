package org.dcharm.java;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);

    public static void main(String[] args) throws Exception {
        List<String> l = new LinkedList<>();
        l.add("dff");
        l.add(null);
        l.add("s");
        l.add("s");
        System.out.println(l);

        List<String> sl = l.subList(0,1);
        l.add(1,"sdfds");
        System.out.println(sl);
        Collections.unmodifiableList(l);


//        System.out.println(null + "");
//        System.out.println("" + null);
//        String s = "" + null;
//        System.out.println(s);
//
//        double a = 0;
//        System.out.println(a == 0.0);
//
//
//        System.out.println(isNumeric("5"));
//        System.out.println(isNumeric("0.5"));
//        System.out.println(isNumeric("-100"));
//        System.out.println(isNumeric("5"));
//        System.out.println(isNumeric("5df"));
//        System.out.println("------------------");
//
//
//        int i = 0;
//        for(int j = 0; j < 100; j++) {
//            i++;
//        }
//        System.out.println(i);
//        System.out.println("------------------");
//
//
//        System.out.println(NumberUtils.isDigits("1212"));
//        System.out.println(NumberUtils.isCreatable("12.12"));
//        System.out.println(NumberUtils.isCreatable("0.1212"));
//        System.out.println(NumberUtils.isCreatable("0ad"));
//
//        try {
//            System.out.println("------------------");
//            ExecutorService service = Executors.newSingleThreadExecutor();
//            SumTask sumTask = new SumTask(20);
//            Future<Integer> future = service.submit(sumTask);
//            Integer result = future.get();
//            Object obj = future.get();
//            System.out.println(result);
//        }
//        catch (Exception e) {
//            logger.info(e.getMessage(), e);
//        }
    }

    static boolean isNumeric(String cs) {
        boolean isNum = true;
        if(cs == null) {
            return false;
        }

        try {
            double x = Double.parseDouble(cs);
        }
        catch (Exception e) {
            isNum = false;
        }
        return isNum;
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
