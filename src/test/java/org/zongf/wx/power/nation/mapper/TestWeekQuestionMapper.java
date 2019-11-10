package org.zongf.wx.power.nation.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.SpecialQuestionMapper;
import org.zongf.wx.power.nation.mapper.WeekQuestionMapper;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;
import org.zongf.wx.power.nation.po.WeekQuestionPO;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestWeekQuestionMapper {

    @Autowired
    private WeekQuestionMapper weekQuestionMapper;


    @Test
    public void testSave(){
        WeekQuestionPO weekQuestionPO = new WeekQuestionPO("2019", "11", 1);
        this.weekQuestionMapper.save(weekQuestionPO);
    }





}
