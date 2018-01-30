package org.dcharm.java.math;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * Create by qiangwang on 2017/11/21
 */
public class ListDemo {
    public static void main(String[] args) {
//        System.out.println("afsdf".contains(null));
        System.out.println("sdfs".concat("---"));
        System.out.println(String.format("bf%sd%sv%s", "a", "b", "c"));
        test2();
    }

    private static void test2() {
        List<String> list = new ArrayList<>();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";

        list.add(a);
        list.add(b);
        list.add(c);
        System.out.println(list);

//        List<String> backedList = new ArrayList<>(list.subList(0, 2));
        List<String> backedList = list.subList(0, 2);
        list.add(0, d);
        System.out.println(list.size());
        System.out.println("2b: " + backedList.size());

        Map<Integer,Record> toInsertMap =new HashMap<>();

        Record record = new Record();
        toInsertMap.put(1,record);

        toInsertMap.put(3,record);

        toInsertMap.put(9,record);

        toInsertMap.put(6,record);

        toInsertMap.put(4,record);

        List<Map.Entry<Integer, Record>> toInsertList = Lists.newArrayList(toInsertMap.entrySet());
        Collections.sort(toInsertList, new Comparator<Map.Entry<Integer, Record>>() {
            @Override
            public int compare(Map.Entry<Integer, Record> o1, Map.Entry<Integer, Record> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        System.out.println(toInsertList);
    }

    private static void test1() {
        List<Integer> recordArray = new LinkedList<>();
        for(int i = 0; i < 20; i++) {
            recordArray.add(i);
        }

        for(Integer i : recordArray) {
            if(i == 5) {
                recordArray.remove(i);
            }
        }

        for(int i = 0; i < recordArray.size(); i++) {
            if(i == 8) {
                recordArray.remove(i);
            }
        }

        while (recordArray.size() > 20)
        {
                int index = (int) (Math.random() * Integer.MAX_VALUE) % recordArray.size();
                index = Math.min(index, recordArray.size() - 1);
                if(index % 2 == 0) {
                    recordArray.remove(index);
                }
        }

        System.out.println(recordArray.size());
    }

    public static class Record {

    }
}
