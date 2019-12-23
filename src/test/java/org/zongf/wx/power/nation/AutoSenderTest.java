package org.zongf.wx.power.nation;

import org.junit.Test;
import org.zongf.wx.power.nation.util.RobotUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AutoSenderTest {

    private static final float screenScacle = 1.0f;

    private static final int group_x = 30;
    private static final int group_y = 70;
    private static final int del_x = 48;
    private static final int del_y = 173;

    private static final int item_height = 28;
    private static final int item_x = 1800;

    private static final int list_y_start = 175;
    private static final int list_max = 30;

    private static final int input_x = 200;
    private static final int input_y = 900;

    @Test
    public void sayHello(){
        System.out.println("程序开始...");
        sleep(2000);
        openGroup();


        for (int i = 28; i < 40; i++) {
            sleep(1000);
            openGroup();
            sleep(1000);
            if(i <list_max){
                RobotUtil.mouseLeftDbClick(item_x, list_y_start + item_height *i);
            }else {
                RobotUtil.mouseLeftClick(item_x, list_y_start + item_height *list_max);
                RobotUtil.scroll(1);
            }
            sleep(1000);
            // 复制
            RobotUtil.copy(input_x, input_y);
        }
    }

    private void sendMsg() {
        // 复制
        RobotUtil.copy(input_x, input_y);
    }


    /** 打开群聊天 */
    private void openGroup(){
        RobotUtil.mouseLeftClick(group_x, group_y);
    }

    /** 删除会话 */
    private void removeSession(){
        RobotUtil.mouseLeftClick(del_x, del_y);
    }

    private static void sleep(long msSeconds){
        try {
            Thread.sleep(msSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
