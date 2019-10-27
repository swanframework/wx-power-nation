package org.zongf.wx.power.nation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionMapperTest {

    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void test(){
        System.out.println("hello");
    }

    @Test
    public void save(){

        QuestionPO questionPO = new QuestionPO(null,"", "毛泽东", "万岁", "毛泽东万岁", new Date());

        this.questionMapper.save(questionPO);

        System.out.println(questionPO);


    }

    @Test
    public void isExsists(){
        String imagePath = "1234";
        boolean exsists = this.questionMapper.isExsists(imagePath);
        System.out.println("是否已解析:" + exsists);

    }

    @Test
    public void truncat(){
        this.questionMapper.clear();
    }

    @Test
    public void testJson() {

        QuestionPO questionPO = this.questionMapper.findById(6l);
        QuestionInfoVo questionInfoVo = QuestionInfoFactory.create(questionPO);

        for (String title : questionInfoVo.getTitleList()) {
            System.out.println("标题:" + title);
        }

        for (String answer : questionInfoVo.getAnswerList()) {
            System.out.println("选项:" + answer);
        }

    }

}
