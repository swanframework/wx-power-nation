package org.zongf.wx.power.nation.vo;

import org.zongf.wx.power.nation.po.SpecialItemPO;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;

import java.util.List;

public class SpecialQuestionVO {

    private SpecialQuestionPO question;

    private List<SpecialItemPO> itemList;

    public SpecialQuestionVO() {
    }

    public SpecialQuestionVO(SpecialQuestionPO question, List<SpecialItemPO> itemList) {
        this.question = question;
        this.itemList = itemList;
    }

    public SpecialQuestionPO getQuestion() {
        return question;
    }

    public void setQuestion(SpecialQuestionPO question) {
        this.question = question;
    }

    public List<SpecialItemPO> getItemList() {
        return itemList;
    }

    public void setItemList(List<SpecialItemPO> itemList) {
        this.itemList = itemList;
    }
}
