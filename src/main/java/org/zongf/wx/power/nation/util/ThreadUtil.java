package org.zongf.wx.power.nation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ThreadUtil {

    private static Logger logger = LoggerFactory.getLogger(ThreadUtil.class);

    public static void sleep(float seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (Exception e) {
            logger.info("休眠被中断");
        }
    }
}
