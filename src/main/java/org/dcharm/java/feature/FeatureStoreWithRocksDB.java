package org.dcharm.java.feature;

import org.apache.commons.io.IOUtils;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.rocksdb.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by qiangwang on 2018/7/31
 */
public class FeatureStoreWithRocksDB {

    public static void main(String[] args) throws Exception {
//        testWrite();
        testRead();
//        Map<String, INDArray> m = test();
//        Thread.sleep(1000 * 300);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(3 * 4);
//        byteBuffer.putFloat(1f);
//        byteBuffer.putFloat(2f);
//        byteBuffer.putFloat(3f);
//        byte[] bytes = byteBuffer.array();
//
//        byteBuffer = ByteBuffer.wrap(bytes);
//        while (byteBuffer.hasRemaining()) {
//            System.out.println(byteBuffer.getFloat());
//        }
    }

    private static Map<String, INDArray> test() {
        String url = "file:///Users/qiangwang/Data/embedding_32.csv";
        InputStream in = null;
        int itemEmbeddingDim = 32;
        Map<String, INDArray> embeddingMap = new HashMap<>(3000000);
        try {
            in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(" ");
                if (arr.length < itemEmbeddingDim + 1) {
                    continue;
                }
                String key = arr[0];

                float[] e = new float[itemEmbeddingDim];
                for (int i = 1; i < arr.length; i++) {
                    e[i - 1] = Float.parseFloat(arr[i]);
                }
                embeddingMap.put(key, Nd4j.create(e));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return embeddingMap;
    }

    private static void testRead() throws Exception{
        try (final Options options = new Options().setCreateIfMissing(true)) {
//            BlockBasedTableConfig o = new BlockBasedTableConfig().setblca
            // a factory method that returns a RocksDB instance
            try (final RocksDB db = RocksDB.open(options, "feature1")) {
                long t1 = System.currentTimeMillis();
                for(int i = 0; i < 10; i++) {
                    byte[] key = "1_15_1999990".getBytes("UTF-8");
                    ByteBuffer byteBuffer = ByteBuffer.wrap(db.get(key));
                    float[] array = new float[32];
                    int k = 0;
                    while (byteBuffer.hasRemaining()) {
                        array[k++] = byteBuffer.getFloat();
                    }
                    System.out.println(Arrays.toString(array));
                }
                System.out.println(System.currentTimeMillis() - t1);
                System.out.println("-----------------------------");
            }
        }
    }

    private static void testWrite() throws Exception{
        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();

        // the Options class contains a set of configurable DB options
        // that determines the behaviour of the database.
        try (final Options options = new Options().setCreateIfMissing(true)) {
//            BlockBasedTableConfig o = new BlockBasedTableConfig().setblca
            // a factory method that returns a RocksDB instance
            try (final RocksDB db = RocksDB.open(options, "feature1")) {
                long t1 = System.currentTimeMillis();

                String url = "file:///Users/qiangwang/Data/embedding_32.csv";
                InputStream in = null;
                int itemEmbeddingDim = 32;
                String line;
                in = new URL(url).openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                while ((line = reader.readLine()) != null) {
                    String[] arr = line.split(" ");
                    if (arr.length < itemEmbeddingDim + 1) {
                        continue;
                    }
                    String key = arr[0];

                    ByteBuffer byteBuffer = ByteBuffer.allocate(itemEmbeddingDim * 4);
                    for (int i = 1; i < arr.length; i++) {
                        byteBuffer.putFloat(Float.parseFloat(arr[i]));
                    }
                    db.put(key.getBytes("UTF-8"), byteBuffer.array());
                }
                System.out.println(System.currentTimeMillis() - t1);
                System.out.println("-----------------------------");
//                byte[] key2 = "key2".getBytes();
//                final byte[] value = db.get(key1);
//                db.put(key1, "hello".getBytes());
//                db.put(key2, "world".getBytes());
//                db.delete(key1);
//                Cache cache = new LRUCache(1000000L);
//                byte[] key1 = ByteBuffer.allocate(4).putInt(100).array();
//                int result = ByteBuffer.wrap(db.get(key1)).getInt();
//                System.out.println(result);
//
//                System.out.println("-----------------------------");
//
//                t1 = System.currentTimeMillis();
//                for(int i = 0; i < 1000000; i++) {
//                    byte[] key = ByteBuffer.allocate(4).putInt(i).array();
//                    db.get(key);
//                }
//                System.out.println(System.currentTimeMillis() - t1);
//                System.out.println("-----------------------------");
            }
        }
    }
}
