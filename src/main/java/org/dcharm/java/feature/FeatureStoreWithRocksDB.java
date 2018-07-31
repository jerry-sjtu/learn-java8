package org.dcharm.java.feature;

import org.rocksdb.*;

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
                byte[] key1 = "key1".getBytes();
                byte[] key2 = "key2".getBytes();
//                final byte[] value = db.get(key1);
//                db.put(key1, "hello".getBytes());
//                db.put(key2, "world".getBytes());
//                db.delete(key1);

                System.out.println(new String(db.get(key2)));
                System.out.println(db.get(key1));
//                Cache cache = new LRUCache(1000000L);
            }
        }

    }
}
