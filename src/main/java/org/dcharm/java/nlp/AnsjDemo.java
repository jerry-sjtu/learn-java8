package org.dcharm.java.nlp;

import org.ansj.splitWord.analysis.DicAnalysis;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Create by qiangwang on 2019/7/19
 */
public class AnsjDemo {
    public static void main(String[] args) {
        List<String> sentences = new LinkedList<>();
        sentences.add("【吃货推荐】北京吃货看这里：江边城外正宗烤鱼53折吃到饱！");
        sentences.add("也许是北京最地道的抹茶冰淇淋！");
        sentences.add( "京城糯米鸡排行榜:咸香与软嫩的完美结合");
        sentences.add("【北京吃货】北京最火的撸串地，这八家串儿店必须给满分！");
        sentences.add("【吃货揭秘】北京离月亮最近的酒吧，究竟多牛逼？");

        Random random = new Random();
        random.nextGaussian();

        for(String s : sentences) {
            System.out.println(s);
            System.out.println(ToAnalysis.parse(s));
            System.out.println(DicAnalysis.parse(s));
            System.out.println("---------------------------------");
        }
    }
}
