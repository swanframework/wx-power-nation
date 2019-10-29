package org.zongf.wx.power.nation.util;

import org.zongf.wx.power.nation.exception.RobotException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/** 鼠标键盘模拟工具类
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class RobotUtil {

    /** 获取硬件操作对象
     * @return Robot
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    private static Robot getRobot(){
        try {
            return new Robot();
        } catch (Exception ex) {
            throw new RobotException("获取硬件失败", ex);
        }
    }

    /** 控制鼠标 移动。 屏幕最左上角为(0,0)处
     * @param x x轴坐标
     * @param y y轴坐标
     * @param screenScacle
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static void moveMouseAt(int x, int y, float screenScacle) {
        // 根据屏幕文字缩放比重新计算x轴和y轴坐标.
        x = (int) (x / screenScacle);
        y = (int) (y / screenScacle);

        // 控制鼠标移动
        getRobot().mouseMove(x, y);
    }

    /** 屏幕截图, 图片格式为png, 屏幕左上角为(0,0)
     * @param x x轴坐标
     * @param y y轴坐标
     * @param width 图片宽度
     * @param heigth 图片高度
     * @return byte[] 图片字节数组
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static byte[] screenCapture(int x, int y, int width, int heigth) {
        try {
            //  创建截图区域
            Rectangle captureRect = new Rectangle(x, y, width, heigth);

            // 截图
            BufferedImage bufferedImage = getRobot().createScreenCapture(captureRect);

            // 以png 格式写入输出流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png",  bos);

            return bos.toByteArray();
        } catch (IOException e) {
            throw new RobotException("截图失败");
        }
    }

    /** 打开文件或目录
     * @param file
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static void openFile(File file) {
        if (!file.exists()) throw new RobotException("文件不存在");

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception ex) {
            throw new RobotException("打开文件失败", ex);
        }
    }

    /** 模拟按键点击
     * @param keyCode 按键编码
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static void mouseClick(int keyCode) {
        Robot robot = getRobot();
        robot.mousePress(keyCode);
        robot.mouseRelease(keyCode);
    }

    /** 模拟按键点击
     * @param x x轴坐标
     * @param y y轴坐标
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static void mouseLeftClick(int x, int y) {

        // 移动鼠标
        moveMouseAt(x, y, 1.0f);

        // 点击
        mouseClick(InputEvent.BUTTON1_MASK);
    }


}
