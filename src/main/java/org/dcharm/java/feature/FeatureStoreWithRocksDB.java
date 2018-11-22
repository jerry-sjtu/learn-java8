package org.dcharm.java.feature;

import org.rocksdb.*;

import java.nio.ByteBuffer;

/**
 * Create by qiangwang on 2018/7/31
 */
public class FeatureStoreWithRocksDB {
    public static void main(String[] args) throws Exception {
        // a static method that loads the RocksDB C++ library.
        RocksDB.loadLibrary();

        // the Options class contains a set of configurable DB options
        // that determines the behaviour of the database.
        try (final Options options = new Options().setCreateIfMissing(true)) {
//            BlockBasedTableConfig o = new BlockBasedTableConfig().setblca
            // a factory method that returns a RocksDB instance
            try (final RocksDB db = RocksDB.open(options, "feature1")) {
                long t1 = System.currentTimeMillis();
                for(int i = 0; i < 1000000; i++) {
                    byte[] key1 = ByteBuffer.allocate(4).putInt(i).array();
                    db.put(key1, key1);
                }
                System.out.println(System.currentTimeMillis() - t1);
                System.out.println("-----------------------------");
//                byte[] key2 = "key2".getBytes();
//                final byte[] value = db.get(key1);
//                db.put(key1, "hello".getBytes());
//                db.put(key2, "world".getBytes());
//                db.delete(key1);
//                Cache cache = new LRUCache(1000000L);
                byte[] key1 = ByteBuffer.allocate(4).putInt(100).array();
                int result = ByteBuffer.wrap(db.get(key1)).getInt();
                System.out.println(result);

                System.out.println("-----------------------------");

                t1 = System.currentTimeMillis();
                for(int i = 0; i < 1000000; i++) {
                    byte[] key = ByteBuffer.allocate(4).putInt(i).array();
                    db.get(key);
                }
                System.out.println(System.currentTimeMillis() - t1);
                System.out.println("-----------------------------");
            }
        }

    }
}
