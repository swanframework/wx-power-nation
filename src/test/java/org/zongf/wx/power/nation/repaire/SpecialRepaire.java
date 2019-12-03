package org.zongf.wx.power.nation.repaire;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.SimilarResult;
import org.zongf.wx.power.nation.StringSimilarUtil;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.QuestionPO;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SpecialRepaire {



    @Autowired
    private QuestionMapper questionMapper;


    // 查看是否包含含有出题单位字样的题目
    @Test
    public void queryTitle(){

        List<QuestionPO> questionPOList = this.questionMapper.queryAll();

        for (QuestionPO questionPO : questionPOList) {
            String title = questionPO.getTitle();
            if (title.contains("出题单位")) {
                System.out.println(title);
            }
        }
    }

    // 修复包含出题单位的题目
    @Test
    public void repaire(){

        List<QuestionPO> questionPOList = this.questionMapper.queryAll();

        for (QuestionPO questionPO : questionPOList) {
            String title = questionPO.getTitle();
            if (title.contains("出题单位")) {
                title = title.replaceAll("[\\(]*\\s*出题单位.*\\)", "");
                questionPO.setTitle(title);
                try {
                    this.questionMapper.update(questionPO);
                }catch (DuplicateKeyException ex){
                    this.questionMapper.delete(questionPO.getId());
                }
            }
        }
    }


    // 统计题目相似度
    @Test
    public void testSimilar(){
        List<QuestionPO> questionPOS = this.questionMapper.queryAll();

        for (QuestionPO questionPO : questionPOS) {
            List<SimilarResult> similarResult = getSimilarResult(questionPO, questionPOS);
            if(similarResult.size() >0){
                System.out.println(questionPO.getId() + ":");
                System.out.println("    " + questionPO.getTitle());
                for (SimilarResult result : similarResult) {
                    System.out.println("    "+ result.getTitle() + result.getSimilar().toString().substring(0,3) + "-" + result.getIdx() + "-" );
                }
            }
        }
    }

    public List<SimilarResult> getSimilarResult(QuestionPO questionPO, List<QuestionPO> list) {
        List<SimilarResult> resultList = new ArrayList<>();
        for (QuestionPO question : list) {
            if(question.getId() == questionPO.getId()) continue;
            double similar = StringSimilarUtil.similar(questionPO.getTitle(), question.getTitle());
            if(similar > 0.96){
                resultList.add(new SimilarResult(similar, question.getId(), question.getTitle()));
            }
        }
        return resultList;
    }

}
