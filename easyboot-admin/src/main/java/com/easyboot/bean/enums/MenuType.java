package com.easyboot.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by 12093 on 2019/1/4.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MenuType implements IEnum{
    /**
     * 菜单
     */
    MENU(1, "菜单"),
    /**
     * 按钮
     */
    BUTTON(0, "按钮");

    private int value;
    private String desc;

    MenuType(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    public String getDesc(){
        return this.desc;
    }
}
