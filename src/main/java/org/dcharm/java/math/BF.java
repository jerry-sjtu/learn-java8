package org.dcharm.java.math;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;
import com.google.common.net.UrlEscapers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.security.CodeSource;
import java.util.ArrayList;

/**
 * Create by qiangwang on 2017/11/7
 */
public class BF {
    public static void main(String[] args) throws Exception {
        System.out.println(" sfa" + null);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(0, "a");
        System.out.println(arrayList);

        BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), 12);
        bf.put(1);
        bf.put(2);
        System.out.println(bf.mightContain(1));
        System.out.println(bf.mightContain(3));

        Feed f1 = new Feed(1, "headline");
        Feed f2 = new Feed(2, "video");
        Feed f3 = new Feed(3, "tuan");

        BloomFilter<Feed> bloomFilter1 = BloomFilter.create(new FeedFunnel(), 100);
        bloomFilter1.put(f1);
        bloomFilter1.put(f2);
        System.out.println(bloomFilter1.mightContain(f1));
        System.out.println(bloomFilter1.mightContain(f2));
        System.out.println(bloomFilter1.mightContain(f3));

        String urlEscaped = UrlEscapers.urlPathSegmentEscaper().escape("兰蔻");
        System.out.println(urlEscaped);

        CodeSource src = BloomFilter.class.getProtectionDomain().getCodeSource();
        if (src != null) {
            URL jar = src.getLocation();
            System.out.println(jar);
        }

//        ByteArrayOutputStream bo = new ByteArrayOutputStream();
//        bloomFilter1.writeTo(bo);
//        byte[] bytes = bo.toByteArray();
//        ByteArrayInputStream io = new ByteArrayInputStream(bytes);
//        BloomFilter<Feed> bloomFilter3 = BloomFilter.readFrom(io, new FeedFunnel());
//        System.out.println(bloomFilter3.mightContain(f1));
//        System.out.println(bloomFilter3.mightContain(f2));
//        System.out.println(bloomFilter1.mightContain(f3));

//        URL[] urls = { new URL("file:/Users/qiangwang/.m2/repository/com/google/guava/guava/20.0/guava-20.0.jar") };
//        URLClassLoader classLoader = URLClassLoader.newInstance(urls);
//        Class<?> bf1 = classLoader.loadClass("com.google.common.hash.BloomFilter");
//        Method method = bf1.getMethod("readFrom", InputStream.class, Funnel.class);
//        BloomFilter<Feed> bloomFilter2 = (BloomFilter<Feed>)method.invoke(null, new ByteArrayInputStream(bytes), new FeedFunnel());
//        src = BloomFilter.class.getProtectionDomain().getCodeSource();
//        if (src != null) {
//            URL jar = src.getLocation();
//            System.out.println(jar);
//        }
//
//        System.out.println(bloomFilter2.mightContain(f1));
//        System.out.println(bloomFilter2.mightContain(f2));
//        System.out.println(bloomFilter2.mightContain(f3));
    }

    static class Feed {
        int id;
        String type;

        public Feed(int id, String type) {
            this.id = id;
            this.type = type;
        }
    }

    static class FeedFunnel implements Funnel<Feed> {

        @Override
        public void funnel(Feed feed, PrimitiveSink primitiveSink) {
            try {
                primitiveSink.putString(feed.type, Charset.forName("UTF-8")).putInt(feed.id);
            }
            catch (Exception e) {
                //do nothing
            }
        }
    }
}
