package org.zongf.wx.power.nation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {


    @Test
    public void doTask() throws InterruptedException {
        String path = "/media/zongf/document/xxqg-imags";
//        OcrTask.doOcr(path);

        Thread.sleep(Integer.MAX_VALUE);

    }
}
