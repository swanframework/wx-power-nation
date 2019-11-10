package org.zongf.wx.power.nation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zongf.wx.power.nation.mapper.ImageWeekMapper;
import org.zongf.wx.power.nation.po.ImageSpecialPO;
import org.zongf.wx.power.nation.po.ImageWeekPO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Controller
@RequestMapping("/image/week")
public class ImageWeekController {

    @Autowired
    private ImageWeekMapper imageWeekMapper;

    // 获取文件内容
    @GetMapping("/content/{id}")
    public void getImage(@PathVariable long id, HttpServletResponse response) throws Exception {

        byte[] content = this.imageWeekMapper.queryInfo(id).getContent();
        if(content != null){
            response.getOutputStream().write(content);
        }
    }

    // 查询下一个待解析的图片
    @ResponseBody
    @GetMapping("/todo")
    public ImageWeekPO todo() {
        ImageWeekPO imageSpecialPO = this.imageWeekMapper.queryNextTodo();
        return imageSpecialPO;
    }

}
