package org.dcharm.java.math;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Create by qiangwang on 2018/1/12
 */
public class B extends A {
    public static void main(String[] args) {
        System.out.println(11 + "_");
        B b = new B();
        b.m1();
        ((A)b).m1();

        List<Integer> l = new LinkedList<>();
        l.add(1);
        l.add(-1);
        l.add(2);
        System.out.println(l);
        Collections.sort(l, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(l);
    }

    @Override
    public void m1() {
        System.out.println("B");
    }
}
