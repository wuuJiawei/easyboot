package com.easyboot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.easyboot.bean.enums.MenuType;
import com.easyboot.bean.enums.YesOrNo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-04
 */
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @TableField("menu_name")
    private String menuName;

    /**
     * 是否废弃：0否1是
     */
    @TableField("disabled")
    private YesOrNo disabled;

    private String url;

    private Integer pid;

    private Integer orders;

    private String icon;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 类型：0-按钮，1-菜单
     */
    private MenuType type;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public YesOrNo getDisabled() {
        return disabled;
    }

    public void setDisabled(YesOrNo disabled) {
        this.disabled = disabled;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Menu{" +
        "menuId=" + menuId +
        ", menuName=" + menuName +
        ", disabled=" + disabled +
        ", url=" + url +
        ", pid=" + pid +
        ", orders=" + orders +
        ", icon=" + icon +
        ", permission=" + permission +
        ", type=" + type +
        "}";
    }
}
