package org.zongf.wx.power.nation.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.SpecialQuestionMapper;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSpecialQuestionMapper {

    @Autowired
    private SpecialQuestionMapper specialQuestionMapper ;


    @Test
    public void testSave(){
        SpecialQuestionPO specialQuestionPO = new SpecialQuestionPO("学习十九届四种全会精神(一)", "2019.11.05", "2020.01.05");
        this.specialQuestionMapper.save(specialQuestionPO);
    }

    @Test
    public void queryAll(){
        List<SpecialQuestionPO> specialQuestionPOS = this.specialQuestionMapper.queryAll(10);
        specialQuestionPOS.forEach(System.out::println);
    }



}
