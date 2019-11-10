package org.zongf.wx.power.nation.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zongf.wx.power.nation.constant.ImageConstant;
import org.zongf.wx.power.nation.mapper.ImageSpecialMapper;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.mapper.SpecialItemMapper;
import org.zongf.wx.power.nation.mapper.SpecialQuestionMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.po.SpecialItemPO;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.vo.QuestionPreview;

import java.util.Date;
import java.util.List;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RestController
@RequestMapping("/special/item")
public class SpecialItemController {

    private static Logger log = LoggerFactory.getLogger(SpecialItemController.class);

    @Autowired
    private SpecialItemMapper specialItemMapper;

    @Autowired
    private SpecialQuestionMapper specialQuestionMapper;

    @Autowired
    private ImageSpecialMapper imageSpecialMapper;

    // 更新
    @PostMapping
    public void save(SpecialItemPO specialItemPO, Long imageId) {
        // 处理特殊符号, %号不能传输
        specialItemPO.setAnswers(specialItemPO.getAnswers().replaceAll("###","%"));
        specialItemPO.setTitle(specialItemPO.getTitle().replaceAll("###","%"));

        // 保存题目
        this.specialItemMapper.save(specialItemPO);

        // 更新图片状态
        this.imageSpecialMapper.handleImage(imageId);
    }

}
