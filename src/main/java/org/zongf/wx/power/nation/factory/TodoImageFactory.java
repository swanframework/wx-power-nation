package org.zongf.wx.power.nation.factory;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.vo.TodoImageInfoVO;
import org.zongf.wx.power.nation.vo.ocr.OcrResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class TodoImageFactory {

    public static TodoImageInfoVO create(ImagePO imagePO, int todoNum) {

        if(imagePO == null) return null;

        // 反向序列化ocr 结果
        OcrResponse ocrResponse = JSONObject.parseObject(imagePO.getAccurateOcr(), OcrResponse.class);

        // 题目列表
        List<String> titleList = new ArrayList<>();
        // 答案列表
        List<String> answerList = new ArrayList<>();

        // 创建栈
        Stack<String> stack = new Stack<>();
        ocrResponse.getWords_result().stream().map(area -> area.getWords()).forEach(stack::push);


        String lastLine = stack.pop();
        // 如果最后一行不以出题开头,则添加到答案列表中, 否则丢弃
        if (!StringUtils.startsWith(lastLine, "出题")) {
            answerList.add(lastLine);
        }

        // 默认行是为答案行
        boolean beginTitleLine = false;

        // 如果栈中还有元素
        while (!stack.empty()) {
            // 弹出一个元素
            String line = stack.pop();

            // 如果是答案行(非标题行), 则进行判断是否要开始进入题目行
            if(!beginTitleLine){
                if (line.endsWith("。") || line.endsWith("？") || line.endsWith("！")
                        || line.endsWith("?") || line.endsWith("!")
                        || line.endsWith(")") || line.endsWith("）")) {
                    beginTitleLine = true;
                }
            }

            // 如果进入标题行,则将当前行添加到标题行. 否则添加到答案行.
            if (beginTitleLine) {
                titleList.add(line);
            }else {
                answerList.add(line);
            }
        }

        // 反转列表
        Collections.reverse(titleList);
        Collections.reverse(answerList);

        // 转换对象
        TodoImageInfoVO todoImageInfoVO = new TodoImageInfoVO();
        todoImageInfoVO.setId(imagePO.getId());
        todoImageInfoVO.setTitleList(titleList);
        todoImageInfoVO.setAnswerList(answerList);
        todoImageInfoVO.setTodoNum(todoNum);
        return todoImageInfoVO;
    }
}
