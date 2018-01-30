package org.dcharm.java.math;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create by qiangwang on 2017/12/18
 */
public class LogDemo {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogDemo.class);
        logger.info("can't get strategy result {}", new Object[]{"dv", new Exception("sdf")});
    }
}
