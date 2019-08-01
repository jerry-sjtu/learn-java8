package org.dcharm.java.dl;

/**
 * Create by qiangwang on 2019/7/11
 */
public class A {
    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() throws Exception {
        try {
            int i = 0;
            throw new Exception("test ex");
        }
        catch (Exception e) {
            throw e;
        }
        finally {
            System.out.println("finally");
        }
    }
}
