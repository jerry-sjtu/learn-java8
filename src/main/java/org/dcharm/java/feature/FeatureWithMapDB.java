package org.dcharm.java.feature;

import com.carrotsearch.hppc.IntFloatHashMap;
import com.carrotsearch.hppc.IntLongHashMap;
import com.sun.xml.internal.fastinfoset.util.StringIntMap;
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
//        IntFloatHashMap map = mapWrite2();
        System.out.println(System.currentTimeMillis() - t1);
        System.out.println("----------");

        t1 = System.currentTimeMillis();
        mapRead(map);
//        mapRead2(map);
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

    private static Map<Integer, Float> mapWrite1() {
        //in memory hash map
//        DB db = DBMaker.memoryDB().make();
//        DB db = DBMaker.heapDB().make();
        //off-heap store
//        ConcurrentMap<String, Long> map = db.hashMap("map", Serializer.STRING, Serializer.LONG).createOrOpen();
        Map<Integer, Float> map = new HashMap<>(1000000);
//        Map<String, Long> map = new TreeMap<>();
        for(int i = 0; i < 1000000; i++) {
            map.put(i, (float)i);
        }

//        db.close();
        return map;
    }

    private static IntFloatHashMap mapWrite2() {
        IntFloatHashMap map = new IntFloatHashMap(1000000);
        for(int i = 0; i < 1000000; i++) {
            map.put(i, i);
        }

//        db.close();
        return map;
    }


    private static void mapRead(Map<Integer, Float> map) {
//        DB db = DBMaker.memoryDB().make();
//        //off-heap store
//        ConcurrentMap map = db.hashMap("map", Serializer.STRING, Serializer.LONG).open();
        for(int i = 0; i < 1000000L; i++) {
            map.get(i);
        }
        System.out.println(100);
//        db.close();
    }


    private static void mapRead2(IntFloatHashMap map) {
//        DB db = DBMaker.memoryDB().make();
//        //off-heap store
//        ConcurrentMap map = db.hashMap("map", Serializer.STRING, Serializer.LONG).open();
        for(int i = 0; i < 1000000; i++) {
            map.get(i);
        }
        System.out.println(100);
//        db.close();
    }



}
