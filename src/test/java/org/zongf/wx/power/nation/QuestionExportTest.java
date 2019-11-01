package org.zongf.wx.power.nation;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.QuestionPO;

import java.io.BufferedWriter;
import java.io.FileWriter;
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
        String filePath = "sql/question.json";

        // 查询所有数据
        List<QuestionPO> questionPOList = this.questionMapper.queryAll();

        // 数据转换为json 数据
        String content = JSONObject.toJSONString(questionPOList);

        // 写入文件
        writeFile(content, filePath);
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



}
