package org.zongf.wx.power.nation.export;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.SimilarResult;
import org.zongf.wx.power.nation.StringSimilarUtil;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.QuestionPO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/** 数据库导出工具
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionExportTest {

    @Autowired
    private QuestionMapper questionMapper;

    /** 到处文件 */
    @Test
    public void exportQuestion(){
        String filePath = "sql/challenge.js";



        // 查询所有数据
        List<QuestionPO> questionPOList = this.questionMapper.queryAll();

        for (QuestionPO questionPO : questionPOList) {
            questionPO.setOptions(questionPO.getOptions().replaceAll(", ]", " ]"));
        }

        // 数据转换为json 数据
        String content = JSONObject.toJSONString(questionPOList);

        StringBuffer sb = new StringBuffer();
        sb.append("let challengeList = ").append("\n");
        sb.append(content).append("\n");
        sb.append(";").append("\n");
        sb.append("module.exports = { challengeList };");

        // 写入文件
        writeFile(sb.toString(), filePath);
    }

    /** 字符串写入文件 */
    private void writeFile(String content, String fileName) {
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            br.write(content);
            br.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // 筛选相似度
    @Test
    public void testSimilar(){
        List<QuestionPO> questionPOS = this.questionMapper.queryAll();

        for (QuestionPO questionPO : questionPOS) {
            List<SimilarResult> similarResult = getSimilarResult(questionPO, questionPOS);
            if(similarResult.size() >0){
                System.out.println(questionPO.getId() + ":");
                System.out.println("    " + questionPO.getTitle());
                for (SimilarResult result : similarResult) {
                    System.out.println("    "+ result.getTitle() + result.getSimilar().toString().substring(0,4) + "-" + result.getIdx() + "-" );
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
