package org.zongf.wx.power.nation.export;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.mapper.SpecialQuestionMapper;
import org.zongf.wx.power.nation.po.QuestionPO;
import org.zongf.wx.power.nation.service.api.ISpecialQuestionService;
import org.zongf.wx.power.nation.vo.SpecialQuestionVO;

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
public class SpecialQuestionExport {

    @Autowired
    private ISpecialQuestionService specialQuestionService;

    /** 到处文件 */
    @Test
    public void export(){
        String filePath = "sql/special.js";

        List<SpecialQuestionVO> list = this.specialQuestionService.queryList(10);


        // 数据转换为json 数据
        String content = JSONObject.toJSONString(list);

        StringBuffer sb = new StringBuffer();
        sb.append("let specialList = ").append("\n");
        sb.append(content).append("\n");
        sb.append(";").append("\n");
        sb.append("module.exports = { specialList };");

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



}
