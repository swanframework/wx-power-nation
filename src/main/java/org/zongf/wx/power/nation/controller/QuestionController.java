package org.zongf.wx.power.nation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.service.api.IImageService;

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

    @Autowired
    private IImageService imageService;

    // 更新
    @PostMapping
    public void save(QuestionPO questionPO) {

        // 设置时间
        questionPO.setCreateTime(new Date());
        boolean success = this.questionMapper.save(questionPO);

        // 更新图片状态
        ImagePO imagePO = this.imageService.queryInfo(questionPO.getImageId());
        imagePO.setStatus(ImageConstant.STATUS_DONE_PARSED_ANSWER);
        this.imageService.parsedToQuestion(questionPO.getImageId());

    }

    @GetMapping("/nextPreview")
    public QuestionPO queryNextPreview(int id) {
        return this.questionMapper.queryNextPreview(id);
    }
}
