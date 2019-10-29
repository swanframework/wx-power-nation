package org.zongf.wx.power.nation.util;

import org.zongf.wx.power.nation.constant.ImageConstant;

public class ImageUtil {


    /** 根据图片ocr内容判断图片类型
     * @param orcJson 图片ocr内容json字符串
     * @return String 图片类型
     * @since 1.0
     * @author zongf
     * @created 2019-10-29
     */
    public static String getImageType(String orcJson) {
        if (orcJson.contains("每日答题")) {
            return ImageConstant.TYPE_ANSWER_MENU;
        }else if (orcJson.contains("完成挑战")) {
            return ImageConstant.TYPE_CONGRATULATIONS;
        }else if (orcJson.contains("结束本局")) {
            return ImageConstant.TYPE_GAMEOVER;
        }else {
            return ImageConstant.TYPE_ANSWERING;
        }
    }
}
