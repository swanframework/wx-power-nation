package org.zongf.wx.power.nation.controller;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zongf.wx.power.nation.factory.QuestionInfoFactory;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.vo.QuestionInfoVo;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageMapper imageMapper;

    @GetMapping("/{id}")
    public void getImage(@PathVariable long id, HttpServletResponse response) throws Exception {
        ImagePO imagePO = this.imageMapper.findById(id);
        response.getOutputStream().write(imagePO.getContent());
    }

    @ResponseBody
    @GetMapping("/info/{id}")
    public QuestionInfoVo getImageInfo(@PathVariable long id) throws Exception {
        ImagePO imagePO = this.imageMapper.findById(id);
        return QuestionInfoFactory.create(imagePO);
    }


}
