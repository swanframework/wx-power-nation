package org.zongf.wx.power.nation.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.sun.scenario.effect.impl.ImagePool;
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
import org.zongf.wx.power.nation.service.api.IImageService;
import org.zongf.wx.power.nation.service.impl.ImageService;
import org.zongf.wx.power.nation.vo.LatestImageInfo;
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
    private IImageService imageService;

    // 获取文件内容
    @GetMapping("/content/{id}")
    public void getImage(@PathVariable long id, HttpServletResponse response) throws Exception {
        byte[] content = this.imageService.queryContent(id);
        if(content != null){
            response.getOutputStream().write(content);
        }
    }

    // 查询待处理的图片
    @ResponseBody
    @GetMapping("/todo")
    public LatestImageInfo getImageInfo(String type) throws Exception {
        return this.imageService.queryToDoImage(type);
    }
}
