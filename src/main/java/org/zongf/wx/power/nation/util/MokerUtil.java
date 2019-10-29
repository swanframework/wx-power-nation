package org.zongf.wx.power.nation.util;

/** 模拟器点击类
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class MokerUtil {

    /** 点击答题页-挑战答题, 开始答题
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static void clickMenuQuestion() {
        RobotUtil.mouseLeftClick(150, 760);
    }

    /** 点击返回按钮
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static void clickReturn() {
        RobotUtil.mouseLeftClick(570, 910);
    }

    /** 点击截图按钮
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static void clickCapture() {
        RobotUtil.mouseLeftClick(570, 240);
    }

    /** 获取屏幕内容
     * @since 1.0
     * @author zongf
     * @created 2019-10-28
     */
    public static byte[] getScreenContent() {
        return RobotUtil.screenCapture(0, 68, 554, 950);
    }
}
