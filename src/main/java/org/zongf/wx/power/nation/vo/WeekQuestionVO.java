package org.zongf.wx.power.nation.vo;

import org.zongf.wx.power.nation.po.SpecialItemPO;
import org.zongf.wx.power.nation.po.SpecialQuestionPO;
import org.zongf.wx.power.nation.po.WeekItemPO;
import org.zongf.wx.power.nation.po.WeekQuestionPO;

import java.util.List;

public class WeekQuestionVO {

    private WeekQuestionPO question;

    private List<WeekItemPO> itemList;

    public WeekQuestionVO() {
    }

    public WeekQuestionVO(WeekQuestionPO question, List<WeekItemPO> itemList) {
        this.question = question;
        this.itemList = itemList;
    }

    public WeekQuestionPO getQuestion() {
        return question;
    }

    public void setQuestion(WeekQuestionPO question) {
        this.question = question;
    }

    public List<WeekItemPO> getItemList() {
        return itemList;
    }

    public void setItemList(List<WeekItemPO> itemList) {
        this.itemList = itemList;
    }
}
