package org.zongf.wx.power.nation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zongf.wx.power.nation.mapper.ImageSpecialMapper;
import org.zongf.wx.power.nation.po.ImageSpecialPO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Controller
@RequestMapping("/image/special")
public class ImageSpecialController {

    @Autowired
    private ImageSpecialMapper imageSpecialMapper;

    // 获取文件内容
    @GetMapping("/content/{id}")
    public void getImage(@PathVariable long id, HttpServletResponse response) throws Exception {

        byte[] content = this.imageSpecialMapper.queryInfo(id).getContent();
        if(content != null){
            response.getOutputStream().write(content);
        }
    }

    // 查询下一个待解析的图片
    @ResponseBody
    @GetMapping("/todo")
    public ImageSpecialPO todo() {
        ImageSpecialPO imageSpecialPO = this.imageSpecialMapper.queryNextTodo();
        return imageSpecialPO;
    }

}
