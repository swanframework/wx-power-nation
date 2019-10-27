package org.zongf.wx.power.nation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zongf.wx.power.nation.factory.QuestionInfoFactory;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.vo.QuestionInfoVo;

import java.util.Date;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    private static Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionMapper questionMapper;


    // 更新
    @PostMapping
    public String save(QuestionPO questionPO) {
        questionPO.setCreateTime(new Date());
        boolean success = this.questionMapper.save(questionPO);
        return success ? "成功" : "失败";
    }
}
