package org.zongf.wx.power.nation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zongf.wx.power.nation.mapper.ImageMapper;
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

    private QuestionMapper questionMapper;

    @Autowired
    private IImageService imageService;


    // 更新
    @PostMapping
    public String save(QuestionPO questionPO) {
        questionPO.setCreateTime(new Date());
        boolean success = this.questionMapper.save(questionPO);
        ImagePO imagePO = this.imageService.queryInfo(questionPO.getImageId());
        imagePO.setStatus("1");
        this.imageService.handleImage(questionPO.getImageId());
        return success ? "成功" : "失败";
    }
}
