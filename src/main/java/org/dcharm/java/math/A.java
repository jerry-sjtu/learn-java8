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
//        String s = FilenameUtils.concat(System.getProperty("java.io.tmpdir"), "dl4j_w2vSentiment/");
//        System.out.println(s);
//        float a = 0.055364f;
//        System.out.println(a * a);

        int x = 1;
        try {
            if(x > 0) {
                return;
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        finally {
            logger.info("here--------");
        }

    }
}
