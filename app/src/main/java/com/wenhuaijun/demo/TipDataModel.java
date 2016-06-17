package com.wenhuaijun.demo;

import com.wenhuaijun.easytagdragview.bean.TitleTip;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据工厂
 *
 * @author maple
 * @time 16/6/17 下午6:13
 */
public class TipDataModel {
    private static String[] dragTips = {"头条", "热点", "娱乐", "体育", "财经", "科技", "段子", "轻松刻",
            "军事", "历史", "游戏", "时尚", "NBA", "漫画", "社会", "中足球", "手机"};
    private static String[] addTips = {"数码", "移互联", "云课堂", "家居", "旅游", "健康", "读书",
            "跑步", "情感", "政务", "艺术", "博客"};

    public static List<TitleTip> getDragTips() {
        List<TitleTip> result = new ArrayList<>();
        for (int i = 0; i < dragTips.length; i++) {
            String temp = dragTips[i];
            TitleTip tip = new TitleTip();
            tip.setTip(temp);
            tip.setId(i);
            result.add(tip);
        }
        return result;
    }

    public static List<TitleTip> getAddTips() {
        List<TitleTip> result = new ArrayList<>();
        for (int i = 0; i < addTips.length; i++) {
            String temp = addTips[i];
            TitleTip tip = new TitleTip();
            tip.setTip(temp);
            tip.setId(i + dragTips.length);
            result.add(tip);
        }
        return result;
    }
}
