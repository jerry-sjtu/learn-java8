package org.dcharm.java.math;

/**
 * Create by qiangwang on 2018/1/12
 */
import com.alibaba.fastjson.JSONObject;

import java.util.*;
public class A{
    private static final String KEY_IMP = "imp";
    private static final String KEY_CLICK = "click";
    private static final String KEY_BIZ_ID = "bizid";
    private static final String KEY_ITEM_ID = "itemid";

    public static void quickSort(int arr[], int left, int right){
        if(arr == null  || right -left <2)
            return;
//        int middle = partition(arr, left, right-1);
//        quickSort(arr, left, middle);
//        quickSort(arr, middle+1, right-1);
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
        String s = " {\"v\":\"1.0\",\"info\":\"20:145897:347,19:1578789:39475\"}";
//        s = null;
//        s = ""
        s = " {\"v\":\"1.0\",\"info\":\"20:145897:347\"}";
        Map<String, Integer> m = parseImpClick(s);
        System.out.println(m);
    }
}
