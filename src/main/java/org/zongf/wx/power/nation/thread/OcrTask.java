package org.zongf.wx.power.nation.thread;

import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.util.FileUtils;

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

    private static final int THREAD_NUM = 1;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);

    public static void doOcrTask(String imgDir, String category){

        // 读取文件夹中所有图片文件
        List<String> fileNames = FileUtils.getFileNames(imgDir);

        // 创建阻塞队列
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>(fileNames);

        for (int i = 0; i < THREAD_NUM; i++) {
            threadPool.submit(new OcrRunnable(queue, category));
        }
    }

}
