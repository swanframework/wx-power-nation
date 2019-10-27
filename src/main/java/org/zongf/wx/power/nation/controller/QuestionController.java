package org.zongf.wx.power.nation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.zongf.wx.power.nation.factory.QuestionInfoFactory;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.thread.OcrTask;
import org.zongf.wx.power.nation.util.FileUtils;
import org.zongf.wx.power.nation.vo.QuestionInfoVo;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/parse")
    public String parse(String dirPath) {
        // 地址为空, 则
        if (StringUtils.isEmpty(dirPath)) {
            return null;
        }

        List<String> allFileNames = FileUtils.getFileNames(dirPath);
        List<String> incrementFileNames = new ArrayList<>();

        // 获取新增的图片地址
        for (String fileName : allFileNames) {
            if (!this.questionMapper.isExsists(fileName)) {
                incrementFileNames.add(fileName);
            }
        }
        log.info("在目录{}中共检测到{}个新图片",dirPath, incrementFileNames.size());

        // 开始解析
        OcrTask.doOcrTask(incrementFileNames);

        return "共检测到" + incrementFileNames.size() + "张新图片, 后台正在进行ocr解析";
    }

    @GetMapping("/{id}")
    public QuestionInfoVo info(@PathVariable Long id) {

        // 数据库查询图片
        QuestionPO questionPO = this.questionMapper.findById(id);

        return QuestionInfoFactory.create(questionPO);
    }

    // 更新
    @PutMapping("/{id}")
    public String updateQuestion(QuestionPO questionPO) {
       boolean success = this.questionMapper.updateAnswer(questionPO);
        return success ? "成功" : "失败";
    }
}
