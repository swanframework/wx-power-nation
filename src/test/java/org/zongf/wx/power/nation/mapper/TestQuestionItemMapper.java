package org.zongf.wx.power.nation.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.po.SpecialItemPO;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQuestionItemMapper {

    @Autowired
    private SpecialItemMapper questionItemMapper ;


    @Test
    public void testSave(){


    }

    @Test
    public void queryAll(){
        List<SpecialItemPO> specialQuestionPOS = this.questionItemMapper.queryList(1l);
        specialQuestionPOS.forEach(System.out::println);
    }



}
