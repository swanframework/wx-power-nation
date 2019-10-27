package org.zongf.wx.power.nation.thread;

import org.zongf.wx.power.nation.po.ImagePO;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class OcrTask {

    private static final int THREAD_NUM = 10;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);


    /** 进行OCR 解析
     * @param fileList 文件列表
     * @since 1.0
     * @author zongf
     * @created 2019-10-27
     */
    public static void doOcrTask(String ak, String sk, List<ImagePO> fileList){
        // 创建阻塞队列
        ConcurrentLinkedQueue<ImagePO> queue = new ConcurrentLinkedQueue<>(fileList);

        for (int i = 0; i < THREAD_NUM; i++) {
            threadPool.submit(new OcrRunnable(ak, sk, queue));
        }
    }

}
