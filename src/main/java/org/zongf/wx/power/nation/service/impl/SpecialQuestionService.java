package org.zongf.wx.power.nation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zongf.wx.power.nation.mapper.SpecialItemMapper;
import org.zongf.wx.power.nation.mapper.SpecialQuestionMapper;
import org.zongf.wx.power.nation.po.SpecialItemPO;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;
import org.zongf.wx.power.nation.service.api.ISpecialQuestionService;
import org.zongf.wx.power.nation.vo.SpecialQuestionVO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialQuestionService implements ISpecialQuestionService {

    @Resource
    private SpecialQuestionMapper specialQuestionMapper;

    @Autowired
    private SpecialItemMapper questionItemMapper;

    @Override
    public List<SpecialQuestionVO> queryList(int maxNum) {

        List<SpecialQuestionVO> specialQuestionVOList = new ArrayList<>();

        // 查询主表数据
        List<SpecialQuestionPO> specialQuestionPOS = this.specialQuestionMapper.queryAll(maxNum);

        // 查询详情
        for (SpecialQuestionPO specialQuestionPO : specialQuestionPOS) {
            List<SpecialItemPO> questionItemPOS = this.questionItemMapper.queryList(specialQuestionPO.getId());
            specialQuestionVOList.add(new SpecialQuestionVO(specialQuestionPO, questionItemPOS));
        }

        return specialQuestionVOList;
    }
}
