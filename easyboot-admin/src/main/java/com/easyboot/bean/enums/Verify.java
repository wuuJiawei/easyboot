package com.easyboot.bean.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by 12093 on 2019/1/4.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Verify implements IEnum{
    /**
     * 未审核
     */
    Load(0, "未审核"),
    /**
     * 通过
     */
    Path(1, "通过"),
    /**
     * 拒绝
     */
    Refuse(2, "拒绝");

    private int value;
    private String desc;

    Verify(final int value, final String desc) {
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
