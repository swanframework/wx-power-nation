package org.zongf.wx.power.nation.service.api;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import org.zongf.wx.power.nation.mapper.ImageMapper;
import org.zongf.wx.power.nation.po.ImagePO;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public interface IImageService {


    // 通过id 查询
    ImagePO queryById(Long id);

    // 通过图片状态和类型查询
    PageList<ImagePO> queryPager(PageBounds pageBounds, String type, String status);

    // 删除图片
    boolean delete(Long id);

    // 批量保存图片
    boolean batchSave(String ak, String sk, String path, String type);


}
