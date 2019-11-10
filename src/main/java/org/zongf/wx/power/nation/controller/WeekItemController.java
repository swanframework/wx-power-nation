package org.zongf.wx.power.nation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zongf.wx.power.nation.mapper.ImageWeekMapper;
import org.zongf.wx.power.nation.mapper.WeekItemMapper;
import org.zongf.wx.power.nation.po.WeekItemPO;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RestController
@RequestMapping("/week/item")
public class WeekItemController {

    private static Logger log = LoggerFactory.getLogger(WeekItemController.class);

    @Autowired
    private WeekItemMapper weekItemMapper;

    @Autowired
    private ImageWeekMapper imageWeekMapper;

    // 更新
    @PostMapping
    public void save(WeekItemPO weekItemPO, Long imageId) {
        // 处理特殊符号, %号不能传输
        weekItemPO.setAnswers(weekItemPO.getAnswers().replaceAll("###","%"));
        weekItemPO.setTitle(weekItemPO.getTitle().replaceAll("###","%"));

        // 保存题目
        this.weekItemMapper.save(weekItemPO);

        // 更新图片状态
        this.imageWeekMapper.handleImage(imageId);
    }

}
