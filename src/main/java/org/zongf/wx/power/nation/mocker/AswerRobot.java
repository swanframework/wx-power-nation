package org.zongf.wx.power.nation.mocker;

import com.alibaba.fastjson.JSONObject;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.util.BaiduOcrUtil;
import org.zongf.wx.power.nation.util.ImageUtil;
import org.zongf.wx.power.nation.util.MokerUtil;
import org.zongf.wx.power.nation.util.ThreadUtil;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

public class AswerRobot {

    public static void run(){

        // 1. 点击挑战答题
        MokerUtil.clickMenuQuestion();

        ThreadUtil.sleep(5);

        // 截图
        byte[] screenContent = MokerUtil.getScreenContent();

        // 解析图片内容
        OcrResponse ocrResponse = BaiduOcrUtil.doBasicOcr(screenContent);

        // 获取图片类型
        String imageType = ImageUtil.getImageType(JSONObject.toJSONString(ocrResponse));

        switch (imageType) {
            // 答题菜单
            case ImageConstant.TYPE_ANSWER_MENU:
                break;

            // 完成挑战
            case ImageConstant.TYPE_CONGRATULATIONS:
                break;

            // 本剧结束
            case ImageConstant.TYPE_GAMEOVER:
                break;

            // 答题中
            case ImageConstant.TYPE_ANSWERING:
                break;
        }



    }

    public static void main(String[] args) {
        run();
    }
}
