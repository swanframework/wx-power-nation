package org.zongf.wx.power.nation.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.vo.QuestionPreview;

import java.util.*;

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
        // 处理特殊符号, %号不能传输
        questionPO.setOptions(questionPO.getOptions().replaceAll("###","%"));
        questionPO.setAnswer(questionPO.getAnswer().replaceAll("###","%"));
        questionPO.setTitle(questionPO.getTitle().replaceAll("###","%"));

        // 设置时间
        questionPO.setCreateTime(new Date());
        boolean success = false;

        try {
            this.questionMapper.save(questionPO);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        // 更新图片状态
        ImagePO imagePO = this.imageService.queryInfo(questionPO.getImageId());
        imagePO.setStatus(ImageConstant.STATUS_DONE_PARSED_ANSWER);
        this.imageService.parsedToQuestion(questionPO.getImageId());

    }

    // 查询预览图
    @GetMapping("/nextPreview")
    public QuestionPreview queryNextPreview(int page) {
        if(page < 1) page = 1;
        PageList<QuestionPO> pager = this.questionMapper.queryByPager(new PageBounds(page, 1));

        if(pager.isEmpty()) return null;
        QuestionPO questionPO = pager.get(0);

        QuestionPreview preview = new QuestionPreview();
        preview.setId(questionPO.getId());
        preview.setImageId(questionPO.getImageId());
        preview.setTitle(questionPO.getTitle());
        preview.setTitleLines(questionPO.getTitleLines());
        preview.setOptions(JSONObject.parseObject(questionPO.getOptions(), new TypeReference<List<String>>(){}));
        preview.setAnswerIdx(questionPO.getAnswerIdx());
        preview.setPage(pager.getPaginator().getPage());
        preview.setTotalPage(pager.getPaginator().getTotalPages());
        return preview;
    }
}
