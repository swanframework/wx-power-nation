package org.zongf.wx.power.nation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.vo.ToParserImgInfo;
import org.zongf.wx.power.nation.vo.TodoImageInfoVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private IImageService imageService;

    // 获取文件内容
    @GetMapping("/content/{id}")
    public void getImage(@PathVariable long id, HttpServletResponse response) throws Exception {
        byte[] content = this.imageService.queryContent(id);
        if(content != null){
            response.getOutputStream().write(content);
        }
    }

    // 查询下一个待解析的图片
    @ResponseBody
    @GetMapping("/nextAnswer")
    public ToParserImgInfo getNextAnswer(String category) {
        return this.imageService.queryToDoImage(category);
    }

    // 删除图片
    @DeleteMapping("/{id}")
    public boolean getImage(@PathVariable long id) throws Exception {
        return this.imageService.delete(id);
    }
}
