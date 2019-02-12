package com.easyboot.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import com.easyboot.bean.enums.YesOrNo;


/**
 * <p>
 * 测试
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-29
 */
@TableName("sys_test")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


    private LocalDateTime ctime;


    private Integer orders;


    private YesOrNo disabled;


    private String name;


    private String url;


    private Integer creator;


    private String otherName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    public YesOrNo getDisabled() {
        return disabled;
    }

    public void setDisabled(YesOrNo disabled) {
        this.disabled = disabled;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    @Override
    public String toString() {
        return "Test{" +
        "id=" + id +
        ", ctime=" + ctime +
        ", orders=" + orders +
        ", disabled=" + disabled +
        ", name=" + name +
        ", url=" + url +
        ", creator=" + creator +
        ", otherName=" + otherName +
        "}";
    }
}
