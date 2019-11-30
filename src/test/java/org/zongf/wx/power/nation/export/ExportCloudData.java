package org.zongf.wx.power.nation.export;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.wx.power.nation.mapper.QuestionMapper;
import org.zongf.wx.power.nation.po.*;
import org.zongf.wx.power.nation.service.api.ISpecialQuestionService;
import org.zongf.wx.power.nation.service.api.IWeekQuestionService;
import org.zongf.wx.power.nation.vo.MonthQuestionVO;
import org.zongf.wx.power.nation.vo.SpecialQuestionVO;
import org.zongf.wx.power.nation.vo.WeekQuestionVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 导出云端数据
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExportCloudData {


    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private ISpecialQuestionService specialQuestionService;

    @Autowired
    private IWeekQuestionService weekQuestionService;


    @Test
    public void test(){

        // 查询挑战答题数据
        List<QuestionPO> challengeList = this.questionMapper.queryAll();
        for (QuestionPO questionPO : challengeList) {
            questionPO.setOptions(questionPO.getOptions().replaceAll(", ]", " ]"));
        }

        // 查询专项答题数据
        List<SpecialQuestionVO> specialList = this.specialQuestionService.queryList(10);


        // 查询每周答题数据
        List<MonthQuestionVO> monthList = this.weekQuestionService.queryList(10);

        System.out.println("challengeList:\n" + JSONObject.toJSONString(challengeList));
        System.out.println("monthList:\n" + JSONObject.toJSONString(monthList));
        System.out.println("specialList:\n" + JSONObject.toJSONString(specialList));
    }

    @Test
    public void testMock(){

        // 查询挑战答题数据
        List<Integer> specialIdList = Arrays.asList(
                3, 4, 7, 9, 10, 12, 13, 15, 17, 18, 20, 22, 25, 26, 31, 32, 34, 35, 36, 37, 38, 39, 40, 42, 45, 46, 49, 52, 54, 55, 56, 58, 63, 66, 67,
                71, 72, 74, 75, 77, 78, 79, 80, 81, 83, 84, 85, 87, 88, 89, 90, 93, 94, 96, 97, 98, 100,101, 102, 103, 104, 105, 106, 109, 111, 112, 121,
                123, 126, 128, 129, 133, 136, 139, 144, 150, 151, 152, 153, 158, 178, 186, 189, 190, 193, 194, 197, 199, 200
                );
        List<QuestionPO> challengeList = new ArrayList<>();
        for (Integer id : specialIdList) {
            QuestionPO questionPO = this.questionMapper.queryById(Long.valueOf(id));
            questionPO.setOptions(questionPO.getOptions().replaceAll(", ]", " ]"));
            challengeList.add(questionPO);
        }

        // 查询专项答题数据
        List<SpecialQuestionVO> specialList = getMockSpecialList();


        // 查询每周答题数据
        List<MonthQuestionVO> monthList = getMockMothList();

        System.out.println("challengeList:\n" + JSONObject.toJSONString(challengeList));
        System.out.println("monthList:\n" + JSONObject.toJSONString(monthList));
        System.out.println("specialList:\n" + JSONObject.toJSONString(specialList));
    }

    private List<SpecialQuestionVO> getMockSpecialList() {
        List<SpecialQuestionVO> specialList = new ArrayList<>();

        // 构造第一个数据
        SpecialQuestionPO specialQuestionPO = new SpecialQuestionPO("学习经典古诗词 (一) ", "2019.09.12", "2019.11.12");
        List<SpecialItemPO> item1List = new ArrayList<>();
        item1List.add(new SpecialItemPO("填空题", 1, "不拘一格降人才是诗人___的诗句。", "[\"龚自珍\"]"));
        item1List.add(new SpecialItemPO("填空题", 2, "王安石《明妃曲》 ( 其二 ) 中写到“汉恩自浅胡恩深，人生乐在相知心”，这首诗中的“明妃”指的是___。", "[\"王昭君\"]"));
        item1List.add(new SpecialItemPO("填空题", 3, "“乘风好去，长空万里，直下看山河出自辛弃疾的《太常引建康中秋夜为吕叔潜赋》，请问“太常引是这首词的___。", "[\"词牌\"]"));
        item1List.add(new SpecialItemPO("填空题", 4, "“春蚕到死丝方尽，蜡炬成灰泪始干”的作者是唐代的___ 。", "[\"李商隐\"]"));
        item1List.add(new SpecialItemPO("填空题", 5, "“飞来山上千寻塔，闻说鸡鸣见日升。___，自缘身在最高层”，选取以下诗句补齐诗歌。", "[\"不畏浮云遮望眼\"]"));
        specialList.add(new SpecialQuestionVO(specialQuestionPO, item1List));

        SpecialQuestionPO specialQuestionPO2 = new SpecialQuestionPO("学习经典古诗词 (二) ", "2019.08.12", "2019.10.12");
        List<SpecialItemPO> item1List2 = new ArrayList<>();
        item1List2.add(new SpecialItemPO("填空题", 1, "“我家洗砚池边树，朵朵花开淡墨痕。不要人夸好颜色，只留清气满乾坤。写的植物是___。", "[\"梅花\"]"));
        item1List2.add(new SpecialItemPO("填空题", 2, "风雨送春归，飞雪迎春到。 ___ ，犹有花枝俏。", "[\"已是悬崖百丈冰\"]"));
        item1List2.add(new SpecialItemPO("填空题", 3, "唐代有一种诗体叫“长吉体”，“长吉体”诗善于运用神话传说和怪诞、华美的语汇，创造出异想天开、从未有过的意象。在遣辞与设色方面，多用\"泣\"、\"腥\"、\"冷\"、\"血\"、\"死\"之类的字眼以及\"冷艳怪丽\"的风格，使诗歌带有伤感冷艳的风格，体现诗人的内心情感。那么。开创“长吉体诗歌的是___。", "[\"李贺\"]"));
        item1List2.add(new SpecialItemPO("填空题", 4, "诗句“布谷飞飞劝早耕，舂锄扑扑趁春晴。”描绘出一幅热闹的___图。 ", "[\"春耕\"]"));
        item1List2.add(new SpecialItemPO("填空题", 5, "“风雨巴山遗恨远，至今人念大将军”说的人物是___。", "[\"邹容\"]"));
        specialList.add(new SpecialQuestionVO(specialQuestionPO2, item1List2));


        SpecialQuestionPO specialQuestionPO3 = new SpecialQuestionPO("学习现代科学与技术", "2019.07.18", "2019.09.18");
        List<SpecialItemPO> item1List3 = new ArrayList<>();
        item1List3.add(new SpecialItemPO("填空题", 1, "碳-14断代法可以用来___。", "[\"判定完全相同地域环境下的两件物品中哪个更古老\"]"));
        item1List3.add(new SpecialItemPO("填空题", 2, "自然现象___的交替是由地球公转产生的。", "[\"春、夏、秋、冬四季\"]"));
        item1List3.add(new SpecialItemPO("填空题", 3, "关于人工智能，下列说法不正确的是___。", "[\"人工智能的发展不会对人类产生任何风险\"]"));
        item1List3.add(new SpecialItemPO("填空题", 4, "锂离子电池充电时，锂离子运动的方向是___。", "[\"锂离子从正极运动到负极\"]"));
        item1List3.add(new SpecialItemPO("填空题", 5, "关于物质的“分子”，下列说法最正确的是___。", "[\"物质中能够独立存在并保持该物质一切化学性质的最小微粒\"]"));
        specialList.add(new SpecialQuestionVO(specialQuestionPO3, item1List3));

        return specialList;
    }

    private List<MonthQuestionVO> getMockMothList() {
        List<MonthQuestionVO> mothList = new ArrayList<>();

        List<WeekQuestionVO> weekQuestionVOList = new ArrayList<>();

        // 第一周
        List<WeekItemPO> weekItemList1 = new ArrayList<>();
        weekItemList1.add(new WeekItemPO("填空题", 1, "关于人工智能，下列说法不正确的是___。", "[\"人工智能的发展不会对人类产生任何风险\"]"));
        weekItemList1.add(new WeekItemPO("填空题", 2, "自然现象___的交替是由地球公转产生的。", "[\"春、夏、秋、冬四季\"]"));
        weekItemList1.add(new WeekItemPO("填空题", 3, "长江是世界第___大河，流经湖北里程达1061公里。", "[\"三\"]"));
        weekItemList1.add(new WeekItemPO("填空题", 4, "下列___不属于个人所得税专项附加扣除项目。", "[\"住房公积金\"]"));
        weekItemList1.add(new WeekItemPO("填空题", 5, "中国地质年龄最年轻的火山岛是___。", "[\"北海涠洲岛\"]"));
        weekQuestionVOList.add(new WeekQuestionVO(new WeekQuestionPO("2019", "11", 1), weekItemList1));

        List<WeekItemPO> weekItemList2 = new ArrayList<>();
        weekItemList2.add(new WeekItemPO("填空题", 1, "有些人喝品质合格的牛奶会腹痛，最有可能的原因是___。", "[\"乳糖不耐受\"]"));
        weekItemList2.add(new WeekItemPO("填空题", 2, "有一位唐代诗人在对贺知章诵读《乌栖曲》时，被贺知章称赞道“你莫不是天上下凡的仙人吧，不然怎么能写出这么感人的诗呢？这位被称之为“谪仙人”的诗人是___。", "[\"李白\"]"));
        weekItemList2.add(new WeekItemPO("填空题", 3, "“三过家门而不入”说的人物是___。", "[\"禹\"]"));
        weekItemList2.add(new WeekItemPO("填空题", 4, "研究表明，1980-2018年中国沿海海平面上升速率为3.3___。", "[\"毫米/年\"]"));
        weekItemList2.add(new WeekItemPO("填空题", 5, "纳税人赡养一位及以上被赡养人的赡养支出，纳税人为独生子女的，按照每月___元的标准定额扣除。", "[\"2000\"]"));
        weekQuestionVOList.add(new WeekQuestionVO(new WeekQuestionPO("2019", "11", 2), weekItemList2));

        List<WeekItemPO> weekItemList3 = new ArrayList<>();
        weekItemList3.add(new WeekItemPO("填空题", 1, "灞桥纸是___时期的一种纸？", "[\"西汉\"]"));
        weekItemList3.add(new WeekItemPO("填空题", 2, "下面不属于我国的五大古都的是___。", "[\"咸阳\"]"));
        weekItemList3.add(new WeekItemPO("填空题", 3, "李白有诗云:“朝辞白帝彩云间，千里江陵一日还。两岸猿声啼不住，轻舟已过万重山。”诗中描写的白帝城位于___。", "[\"奉节县\"]"));
        weekItemList3.add(new WeekItemPO("填空题", 4, "人们平常打点滴 ( 吊针 ) 用的生理盐水 ( 氯化钠注射液 ) 浓度是___%", "[\"0.9\"]"));
        weekItemList3.add(new WeekItemPO("填空题", 5, "我国将每年的___定为中国水周。", "[\"3月22至28日\"]"));
        weekQuestionVOList.add(new WeekQuestionVO(new WeekQuestionPO("2019", "11", 3), weekItemList3));

        List<WeekItemPO> weekItemList4 = new ArrayList<>();
        weekItemList4.add(new WeekItemPO("填空题", 1, "关于地理上“阴”“阳”的解释，正确的一项是___。", "[\"山的北面为阴，水的北面为阳\"]"));
        weekItemList4.add(new WeekItemPO("填空题", 2, "《雨打芭蕉》是我国哪个地方的音乐代表___。", "[\"广东\"]"));
        weekItemList4.add(new WeekItemPO("填空题", 3, "三月不知肉味这句话一般用来形容___。", "[\"音乐\"]"));
        weekItemList4.add(new WeekItemPO("填空题", 4, "我国淡水资源在全球水资源占比 ___。", "[\"百分之六\"]"));
        weekItemList4.add(new WeekItemPO("填空题", 5, "“文章千古事，得失寸心知”是谁的名句？___", "[\"杜甫\"]"));
        weekQuestionVOList.add(new WeekQuestionVO(new WeekQuestionPO("2019", "11", 4), weekItemList4));


        mothList.add(new MonthQuestionVO(2019,"11", weekQuestionVOList));
        return mothList;
    }

}
