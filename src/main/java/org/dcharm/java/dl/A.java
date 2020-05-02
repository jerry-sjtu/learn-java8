package org.dcharm.java.dl;

/**
 * Create by qiangwang on 2019/7/11
 */
public class A {
    private int numBlocks = 500;
    private int numLocalIndexBits =
            Math.min(java.lang.Long.numberOfLeadingZeros(numBlocks - 1), 63);
    private long localIndexMask = (1L << numLocalIndexBits) - 1L;

    public static void main(String[] args) throws Exception {

        System.out.println(1 / 2);

////        test();
//        int numLocalIndexBits1 = Math.min(java.lang.Long.numberOfLeadingZeros(600 - 1), 31);
//        int numLocalIndexBits2 = Math.min(java.lang.Long.numberOfLeadingZeros(600 - 1), 63);
//        int localIndex = 30000;
//        long localIndexMask = (1L << numLocalIndexBits2) - 1L;
//        long x1 = 1 << numLocalIndexBits1 | localIndex;
//        long x2 = 1 << numLocalIndexBits2 | localIndex;
//        long x3 = (long)1 << numLocalIndexBits2 | ((long)localIndex);
//        System.out.println(numLocalIndexBits1);
//        System.out.println(numLocalIndexBits2);
//        System.out.println(x1);
//        System.out.println(x2);
//        System.out.println(x3);
//        System.out.println(localIndex & ~localIndexMask);
        A a = new A();

//        System.out.println(Integer.numberOfLeadingZeros(600 - 1));
//
//        long id1 = a.encode(1, 2);
//        System.out.println(id1);
//        System.out.println(a.blockId(id1));
//        System.out.println(a.localIndex(id1));
//
//        long id2 = a.encode(100, 2234345);
//        System.out.println(id2);
//        System.out.println(a.blockId(id2));
//        System.out.println(a.localIndex(id2));
//
//        long id3 = a.encode(300, 223433545);
//        System.out.println(id3);
//        System.out.println(a.blockId(id3));
//        System.out.println(a.localIndex(id3));
    }

    private long encode(int blockId, int localIndex){
        System.out.println((localIndex & ~localIndexMask) == 0);
        return ((long)blockId << numLocalIndexBits) | (long)localIndex;
    }

    /** Gets the block id from an encoded index. */
    private int blockId(long encoded) {
        return (int)(encoded >>> numLocalIndexBits);
    }

    /** Gets the local index from an encoded index. */
    private int localIndex(long encoded){
        return (int)(encoded & localIndexMask);
    }
}
