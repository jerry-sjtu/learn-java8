package org.dcharm.java.feature;

import org.mapdb.*;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Create by qiangwang on 2018/7/30
 */
public class FeatureWithMapDB {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        Map map = mapWrite1();
        System.out.println(System.currentTimeMillis() - t1);
        System.out.println("----------");

        t1 = System.currentTimeMillis();
        mapRead(map);
        System.out.println(System.currentTimeMillis() - t1);
        System.out.println("----------");
    }

    private static ConcurrentMap mapWrite() {
        //in memory hash map
        DB db = DBMaker.memoryDB().make();
//        DB db = DBMaker.heapDB().make();
        //off-heap store
        ConcurrentMap<String, Long> map = db.hashMap("map", Serializer.STRING, Serializer.LONG).createOrOpen();
        for(long i = 0; i < 1000000L; i++) {
            map.put(String.valueOf(i), i);
        }

//        db.close();
        return map;
    }

    private static Map<String, Long> mapWrite1() {
        //in memory hash map
//        DB db = DBMaker.memoryDB().make();
//        DB db = DBMaker.heapDB().make();
        //off-heap store
//        ConcurrentMap<String, Long> map = db.hashMap("map", Serializer.STRING, Serializer.LONG).createOrOpen();
        Map<String, Long> map = new HashMap<>(1000000);
//        Map<String, Long> map = new TreeMap<>();
        for(long i = 0; i < 1000000L; i++) {
            map.put(String.valueOf(i), i);
        }

//        db.close();
        return map;
    }



    private static void mapRead(Map<String, Long> map) {
//        DB db = DBMaker.memoryDB().make();
//        //off-heap store
//        ConcurrentMap map = db.hashMap("map", Serializer.STRING, Serializer.LONG).open();
        for(long i = 0; i < 1000000L; i++) {
            map.get(String.valueOf(i));
        }
        System.out.println(map.get("100"));
//        db.close();
    }

}
