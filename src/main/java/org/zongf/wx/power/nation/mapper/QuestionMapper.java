package org.zongf.wx.power.nation.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.zongf.wx.power.nation.po.QuestionPO;

import java.util.List;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
@Mapper
public interface QuestionMapper {

    // 保存
    boolean save(QuestionPO questionPO);

    // 删除
    boolean delete(Long id);

    // 通过id查询
    QuestionPO queryById(Long id);

    // 分页查询
    PageList<QuestionPO> queryByPager(PageBounds pageBounds);

    List<QuestionPO> queryAll();

    // 查询最新的num条数据
    List<QuestionPO> queryLatest(int num);

}
