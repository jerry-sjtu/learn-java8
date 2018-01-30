package org.dcharm.java.math;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import java.util.*;

/**
 * Create by qiangwang on 2017/12/20
 */
public class JsonDemo {
    public static void main(String[] args) {
        Map<String, Object> m = new HashMap<>();
        m.put("a", 1);
        m.put("b", "c");
        System.out.println(JSONObject.toJSON(m));

        List<String> tags = Lists.newArrayList();
        tags.add("已关注");
        HashMap map = new HashMap();
        map.put("servicetag", tags);
        String serviceTag = new Gson().toJson(map);
        System.out.println(serviceTag);


        List<String> l = new ArrayList<>();
        l.add("b");
        l.add("c");
        l.add("a");
        l.add("sdfsdf");
        System.out.println(l);

        String v2 = l.get(0);
        String v1 = l.get(3);
        l.set(0, v1);
        l.set(3, v2);
        System.out.println(l);

        String s = "di:%s";
        System.out.println(String.format("%d is a number", 1));

        List<String> sl = new LinkedList<>();
        sl.add("b");
        System.out.println(sl);
        test(sl);
        System.out.println(sl);
    }

    private static void test(List<String> a) {
//        a = new LinkedList<>();
        a.add("test");
    }
}
