package org.zongf.wx.power.nation.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.ibatis.annotations.Mapper;
import org.zongf.wx.power.nation.po.QuestionPO;

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

    // 修改
    long update(QuestionPO questionPO);

    // 通过id查询
    QuestionPO findById(Long id);

    // 分页查询
    PageList<QuestionPO> queryByPager(PageBounds pageBounds);

    // 清空表
    void clear();

    // 是否存在
    boolean isExsists(String filePath);

    // 更新答案
    boolean updateAnswer(QuestionPO questionPO);

}
