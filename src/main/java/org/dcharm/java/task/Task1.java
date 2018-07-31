package org.dcharm.java.task;

import java.util.*;
import java.util.concurrent.*;


/**
 * Create by qiangwang on 2018/4/16
 */
public class Task1 {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(64, 64, 5,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(128, false),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i = 0; i < 100; i++) {
            test1(executor);
        }
    }

    private static void test1(ThreadPoolExecutor executor) throws Exception{
        List<String> recordList = new ArrayList<>();
        for(int i = 0; i < 1000; i++) {
            recordList.add(String.valueOf(i));
        }

        int start = 0, end = 0;
        int length = recordList.size();
        int limit = 200;
        List<Future<List<String>>> futures = new LinkedList<>();
        while (start < length) {
            end = start + limit < length ? start + limit : length;
            CubeTask t = new CubeTask(recordList.subList(start, end));
            Future<List<String>> cubeRankFuture = executor.submit(t);
            futures.add(cubeRankFuture);
            start += limit;
        }

        Set<String> set = new HashSet<>();
        for (Future<List<String>> future : futures) {
            set.addAll(future.get());
        }
        System.out.println(set.size());
    }

    static class CubeTask implements Callable<List<String>> {

        private List<String> list;

        public CubeTask(List<String> list) {
            this.list = list;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> nl = new ArrayList<>();
            for(String i : list)
                nl.add(i);
            Thread.sleep(20);
            return nl;
        }


    }


    private static void test() throws Exception{
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 5,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(2, false),
                new ThreadPoolExecutor.CallerRunsPolicy());

        Callable<String> t1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                String s = null;
                int i = Integer.parseInt("sdfff");
                return s.toString();
            }
        };

        Future<String> f1 =  executor.submit(t1);
        System.out.println(f1.get());
    }


}
