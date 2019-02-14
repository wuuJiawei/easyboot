package com.easyboot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.easyboot.bean.enums.Verify;
import com.easyboot.bean.enums.YesOrNo;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-05
 */
public class App implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String appkey;

    private String appsecret;

    private String name;

    private String info;

    private String ip;

    private String type;

    private YesOrNo disabled;

    private Integer orders;

    private LocalDateTime ctime;

    /**
     * 授权截止日期
     */
    @TableField("end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @TableField("begin_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    private Integer intervals;

    /**
     * 审核状态：0-未审核，1-通过，2-拒绝
     */
    @TableField("verify_status")
    private Verify verifyStatus;

    /**
     * 审核说明
     */
    @TableField("verify_info")
    private String verifyInfo;

    private String logo;

    /**
     * 申请人
     */
    private Integer creator;

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getIntervals() {
        return intervals;
    }

    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public YesOrNo getDisabled() {
        return disabled;
    }

    public void setDisabled(YesOrNo disabled) {
        this.disabled = disabled;
    }
    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
    public LocalDateTime getCtime() {
        return ctime;
    }

    public void setCtime(LocalDateTime ctime) {
        this.ctime = ctime;
    }
    public Verify getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Verify verifyStatus) {
        this.verifyStatus = verifyStatus;
    }
    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "App{" +
        "id=" + id +
        ", appkey=" + appkey +
        ", appsecret=" + appsecret +
        ", name=" + name +
        ", info=" + info +
        ", ip=" + ip +
        ", type=" + type +
        ", disabled=" + disabled +
        ", orders=" + orders +
        ", ctime=" + ctime +
        ", verifyStatus=" + verifyStatus +
        ", verifyInfo=" + verifyInfo +
        ", logo=" + logo +
        ", creator=" + creator +
        "}";
    }
}
