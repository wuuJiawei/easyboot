package com.easyboot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;


/**
 * <p>
 * a1测试
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-28
 */
public class A1 implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "a1", type = IdType.AUTO)
    private Integer a1;


    private Integer a2;


    private Integer a3;


    public Integer getA1() {
        return a1;
    }

    public void setA1(Integer a1) {
        this.a1 = a1;
    }
    public Integer getA2() {
        return a2;
    }

    public void setA2(Integer a2) {
        this.a2 = a2;
    }
    public Integer getA3() {
        return a3;
    }

    public void setA3(Integer a3) {
        this.a3 = a3;
    }

    @Override
    public String toString() {
        return "A1{" +
        "a1=" + a1 +
        ", a2=" + a2 +
        ", a3=" + a3 +
        "}";
    }
}
