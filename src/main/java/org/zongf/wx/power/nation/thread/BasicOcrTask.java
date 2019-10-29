package org.zongf.wx.power.nation.thread;

import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.vo.ImgImportResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class BasicOcrTask {

    static Logger log = (Logger) LoggerFactory.getLogger(BasicOcrTask.class);

    private static final int THREAD_NUM = 10;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);

    public static void doOcrTask(String imgDir, String category){

        // 读取文件夹中所有图片文件
        List<String> fileNames = FileUtils.getFileNames(imgDir);

        // 创建阻塞队列
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>(fileNames);

        List<Future<ImgImportResult>> futureList = new ArrayList<>();
        for (int i = 0; i < THREAD_NUM; i++) {
            Future<ImgImportResult> future = threadPool.submit(new BasicOcrCallable(queue, category));
            futureList.add(future);
        }

        // 合并线程执行结果
        ImgImportResult totalResult = new ImgImportResult();
        for (Future<ImgImportResult> future : futureList) {
            try {
                ImgImportResult result = future.get();
                log.info("图片导入结果, 线程名:{}, 总数量:{}, 成功数量:{}, 重复数量:{}, 非法图片数量:{}, 失败数量:{}",
                        result.getThreadName(), result.getTotalNum(), result.getIncrNum(), result.getRepeatNum(), result.getIllegalNum(), result.getFailNum());

                totalResult.addTotalNum(result.getTotalNum());
                totalResult.addRepeatNum(result.getRepeatNum());
                totalResult.addFailNum(result.getFailNum());
                totalResult.addIncrNum(result.getIncrNum());
                totalResult.addIllegalNum(result.getIllegalNum());
            } catch (Exception e) {
                log.error("解析导入结果失败", e);
            }
        }

        log.info("图片导入结束: 总数量:{}, 成功数量:{}, 重复数量:{}, 失败数量:{}",
                totalResult.getTotalNum(), totalResult.getIncrNum(), totalResult.getRepeatNum(),totalResult.getIllegalNum(), totalResult.getFailNum());
    }

}
