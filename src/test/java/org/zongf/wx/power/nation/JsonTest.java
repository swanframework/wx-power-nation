package org.zongf.wx.power.nation;

import org.junit.Test;
import org.zongf.wx.power.nation.factory.QuestionInfoFactory;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.vo.QuestionInfoVo;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class JsonTest {

    @Test
    public void test(){


    }

    public static void main(String[] args) {

        QuestionPO questionPO = new QuestionPO();

        questionPO.setOcr("{\"log_id\":3769095602291380379,\"words_result\":[{\"words\":\"NB9:17\"},{\"words\":\"《歌唱祖国》词曲作者\"},{\"words\":\"是第一位为五星\"},{\"words\":\"红旗谱写歌曲的人。1951年10月29日,在全国\"},{\"words\":\"政协会议上,毛泽东主席得知作者列席会议,\"},{\"words\":\"对他说,这首歌好!并特地将刚刚出版的《毛\"},{\"words\":\"泽东选集》送给他,并亲笔签名。\"},{\"words\":\"王莘\"},{\"words\":\"贺绿汀\"},{\"words\":\"李涣之\"},{\"words\":\"出题:北京市西城区军休办党委\"}],\"words_result_num\":11}");

        QuestionInfoVo questionInfoVo = QuestionInfoFactory.create(questionPO);

        System.out.println(questionInfoVo);
    }

}
