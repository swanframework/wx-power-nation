package org.zongf.wx.power.nation.service.api;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Param;
import org.zongf.wx.power.nation.po.ImagePO;
import org.zongf.wx.power.nation.vo.TodoImageInfoVO;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public interface IImageService {

    // 保存
    boolean save(ImagePO imagePO);

    // 删除
    boolean delete(Long id);

    // 查询图片信息
    ImagePO queryInfo(Long id);

    // 查询图片内容
    byte[] queryContent(Long id);

    // 查询待处理的图片
    TodoImageInfoVO queryToDoImage(String type);

    // 更新状态
    boolean handleImage(Long id);

    // 分页查询
    PageList<ImagePO> queryByPager(PageBounds pageBounds, String category, String status);

    // 更新locOcr
    boolean updateLocOcr(Long id, String locOcr);

    // 批量更新AccurateOcr 字段
    boolean batchAccurateOcr(String category);

}
