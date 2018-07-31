package org.dcharm.java.feature;

import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;
import java.io.*;
/**
 * Create by qiangwang on 2018/7/30
 */
public class FeatureStoreWithLevelDb {
    public static void main(String[] args) throws Exception {
        Options options = new Options();
        options.compressionType(CompressionType.NONE);
        options.cacheSize(100 * 1048576); // 100MB cache

        DB db = factory.open(new File("example"), options);
        db.put(bytes("Tampa"), bytes("rocks"));
        System.out.println("value: " + new String(db.get(bytes("Tampa"))));
        db.delete(bytes("Tampa"));


        WriteBatch batch = db.createWriteBatch();
        try {
            batch.delete(bytes("Denver"));
            batch.put(bytes("Tampa"), bytes("green"));
            batch.put(bytes("London"), bytes("red"));

            db.write(batch);
        } finally {
            // Make sure you close the batch to avoid resource leaks.
            batch.close();
        }

        DBIterator iterator = db.iterator();
        try {
            for(iterator.seekToFirst(); iterator.hasNext(); iterator.next()) {
                String key = asString(iterator.peekNext().getKey());
                String value = asString(iterator.peekNext().getValue());
                System.out.println(key+" = "+value);
            }
        } finally {
            // Make sure you close the iterator to avoid resource leaks.
            iterator.close();
        }

        long[] sizes = db.getApproximateSizes(new Range(bytes("a"), bytes("k")), new Range(bytes("k"), bytes("z")));
        System.out.println("Size: "+sizes[0]+", "+sizes[1]);

        String stats = db.getProperty("leveldb.stats");
        System.out.println(stats);

        db.close();

        factory.destroy(new File("example"), options);

    }
}
