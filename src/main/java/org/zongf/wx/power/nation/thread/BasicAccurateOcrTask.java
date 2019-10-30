package org.zongf.wx.power.nation.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.wx.power.nation.vo.ImgLocOcrResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class BasicAccurateOcrTask {

    static Logger log = LoggerFactory.getLogger(BasicAccurateOcrTask.class);

    // 线程数
    private static final int THREAD_NUM = 10;

    //
    private static AtomicInteger pageNum = new AtomicInteger(1);

    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_NUM);

    public static void doOcrTask(String category) {

        // 创建多线程任务
        List<Future<ImgLocOcrResult>> futureList = new ArrayList<>();
        for (int i = 0; i < THREAD_NUM; i++) {
            Future<ImgLocOcrResult> future = threadPool.submit(new BasicAccurateOcrCallable(category, pageNum));
            futureList.add(future);
        }

        // 合并线程执行结果
        ImgLocOcrResult totalResult = new ImgLocOcrResult();
        for (Future<ImgLocOcrResult> future : futureList) {
            try {
                ImgLocOcrResult result = future.get();
                log.info("图片精确ocr结果, 线程名:{}, 总数量:{}, 成功数量:{}, 失败数量:{}",
                        result.getThreadName(), result.getTotalNum(), result.getSuccessNum(), result.getFailNum());

                totalResult.addTotalNum(result.getTotalNum());
                totalResult.addSuccessNum(result.getSuccessNum());
                totalResult.addFailNum(result.getFailNum());
            } catch (Exception e) {
                log.error("解析导入结果失败", e);
            }
        }

        log.info("图片精确ocr结束: 总数量:{}, 成功数量:{}, 失败数量:{}",
                totalResult.getTotalNum(), totalResult.getSuccessNum(), totalResult.getFailNum());

    }

}
