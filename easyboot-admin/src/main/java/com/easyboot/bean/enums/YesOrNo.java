package com.easyboot.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by 12093 on 2019/1/4.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum YesOrNo implements IEnum{
    /**
     * 是
     */
    YES(1, "是"),
    /**
     * 否
     */
    NO(0, "否");

    private int value;
    private String desc;

    YesOrNo(final int value, final String desc) {
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
