package org.dcharm.java.math;

/**
 * Create by qiangwang on 2018/1/12
 */
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
public class A{
    private static final Logger logger = LoggerFactory.getLogger(A.class);
    private static final String KEY_IMP = "imp";
    private static final String KEY_CLICK = "click";
    private static final String KEY_BIZ_ID = "bizid";
    private static final String KEY_ITEM_ID = "itemid";

    public static void quickSort(int arr[], int left, int right){
//        if(arr == null  || right -left <2)
//            return;
//        int middle = partition(arr, left, right-1);
//        quickSort(arr, left, middle);
//        quickSort(arr, middle+1, right-1);


//        String s = FilenameUtils.concat(System.getProperty("java.io.tmpdir"), "dl4j_w2vSentiment/");
//        System.out.println(s);

    }

    public static float product(float[] a, float[] b) {
        float sum = 0;
        for(int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public static float dotProduct_Float(float[] a, int aoff, float[] b, int boff, int length) {
        int UNROLL_LENGTH = 8;
        int extra = length % UNROLL_LENGTH;
        int loops = length / UNROLL_LENGTH;
        float sum0 = 0.0f;
        float sum1 = 0.0f;
        float sum2 = 0.0f;
        float sum3 = 0.0f;
        float sum4 = 0.0f;
        float sum5 = 0.0f;
        float sum6 = 0.0f;
        float sum7 = 0.0f;

        for(int i = 0; i < extra; i++) {
            sum0 += a[aoff + i] * b[boff + i];
        }
        aoff += extra;
        boff += extra;

        for(int i = 0; i < loops; i++, aoff += UNROLL_LENGTH, boff += UNROLL_LENGTH) {
            sum0 += a[aoff + 0] * b[boff + 0];
            sum1 += a[aoff + 1] * b[boff + 1];
            sum2 += a[aoff + 2] * b[boff + 2];
            sum3 += a[aoff + 3] * b[boff + 3];
            sum4 += a[aoff + 4] * b[boff + 4];
            sum5 += a[aoff + 5] * b[boff + 5];
            sum6 += a[aoff + 6] * b[boff + 6];
            sum7 += a[aoff + 7] * b[boff + 7];
        }

        return sum0 + sum1 + sum2 + sum3 + sum4 + sum5 + sum6 + sum7;
    }

    private static Map<String, Integer> parseImpClick(String s) {

        HashMap<String, Integer> m = new HashMap<>();
            JSONObject obj = JSONObject.parseObject(s);
            String daysinfo = obj.getString("info");
            if(daysinfo == null) {
                return null;
            }
            String[] dayinfos = daysinfo.split(",");
            String[] arr = dayinfos[0].split(":");
            if(arr.length != 3) {
                return null;
            }
            m.put(KEY_IMP, Integer.parseInt(arr[1]));
            m.put(KEY_CLICK, Integer.parseInt(arr[2]));
        return m;
    }


    public static int partition(int arr[], int left, int right){
        int randomIndex = (int)((right -left) * Math.random()) + left;
        swap(arr, randomIndex, right);
        int small = left-1;
        for(int i= left; i< right; i++){
            if(arr[i] < arr[right]){
                small++;
                if(small != i){
                    swap(arr, small, i);
                }
            }
        }
        swap(arr, small+1, right);
        return small+1;
    }
    public static  void swap(int arr[], int x, int y){
        if(arr == null || x == y)
            return;
        arr[x] ^= arr[y];
        arr[y] ^= arr[x];
        arr[x] ^= arr[y];
    }

    public static void main (String args[]){
        long t1 = System.currentTimeMillis();
        for(int i = 0 ; i < 100000; i++) {
            float[] a = {1, 2, 3, 4, 5, 6, 6, 8, 1, 2, 3, 4, 5, 6, 6, 8, 1, 2, 3, 4, 5, 6, 6, 8};
            float[] b = {1, 2, 3, 4, 5, 6, 6, 8, 1, 2, 3, 4, 5, 6, 6, 8, 1, 2, 3, 4, 5, 6, 6, 8};
//            float product = dotProduct_Float(a,0, b, 0, a.length);
            float product = product(a, b);
        }
        System.out.println(System.currentTimeMillis() - t1);
//        System.out.println(dotProduct_Float(a,0, b, 0, a.length));
//        System.out.println(product(a, b));

    }
}
