package com.easyboot.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import com.easyboot.bean.enums.YesOrNo;


/**
 * <p>
 * 用户
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-29
 */
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;


    /**
     * 手机
     */
    private String phone;


    /**
     * 密码
     */
    private String password;


    /**
     * 昵称
     */
    private String nickname;


    /**
     * 创建时间
     */
    private LocalDateTime ctime;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 头像
     */
    private String avatar;


    /**
     * 是否锁定，0否1是
     */
    private YesOrNo locked;


    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;


    /**
     * 排序
     */
    private Integer orders;




    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public YesOrNo getLocked() {
        return locked;
    }

    public void setLocked(YesOrNo locked) {
        this.locked = locked;
    }
    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", phone=" + phone +
        ", password=" + password +
        ", nickname=" + nickname +
        ", ctime=" + ctime +
        ", email=" + email +
        ", avatar=" + avatar +
        ", locked=" + locked +
        ", lastLoginTime=" + lastLoginTime +
        ", orders=" + orders +
        "}";
    }
}
