package com.easyboot.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyboot.common.ResultBean;
import com.easyboot.bean.App;
import com.easyboot.bean.enums.Verify;
import com.easyboot.bean.enums.YesOrNo;
import com.easyboot.common.layui.LayTable;
import com.easyboot.common.layui.LayTableArg;
import com.easyboot.support.BaseController;
import com.easyboot.config.log.annotation.Log;
import com.easyboot.config.log.annotation.LogSubject;
import com.easyboot.service.IAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * AppController
 *
 * @name: AppController
 * @author: 12093
 * @date: 2019/1/5
 * @time: 20:44
 */
@Controller
@RequestMapping("app")
@LogSubject("APP")
public class AppController extends BaseController {

    @Autowired
    private IAppService appService;

    @GetMapping("/")
    public String list(){
        return "app/list";
    }

    @GetMapping("apply")
    public String apply(){
        return "app/apply";
    }

    @Log("查询")
    @RequestMapping("query")
    @ResponseBody
    public LayTable<App> query(LayTableArg arg, YesOrNo disabled){
        QueryWrapper<App> wrapper = new QueryWrapper<App>();
        wrapper.eq("disabled", disabled == null ? YesOrNo.NO : disabled);
        if (StrUtil.isNotBlank(arg.getParam())) {
            wrapper.and(andWrapper -> andWrapper.like("name", arg.getParam()).or().like("appkey", arg.getParam()));
        }

        String field = StrUtil.isNotBlank(arg.getField()) ? StrUtil.toSymbolCase(arg.getField(), '_') : "orders";
        wrapper.orderBy(true, arg.isAsc(), field);

        Page<App> page = new Page<>(arg.getPage(), arg.getLimit());
        IPage<App> list = appService.page(page, wrapper);
        return new LayTable<App>(list.getRecords(), (int)list.getTotal());
    }

    @Log("创建")
    @PostMapping("apply")
    @ResponseBody
    public ResultBean insert(App record){
        record.setCreator(getCurrentUser().getUserId());
        String uuid = IdUtil.simpleUUID();
        record.setAppkey(uuid);
        record.setAppsecret(SecureUtil.md5(uuid));
        record.setCtime(LocalDateTime.now());
        record.setDisabled(YesOrNo.NO);
        record.setVerifyStatus(Verify.Load);
        record.setOrders(0);
        boolean success = appService.save(record);
        if (success) {
            return ResultBean.ok(record);
        }
        return ResultBean.error();
    }

    @Log("更新")
    @PostMapping("update")
    @ResponseBody
    public ResultBean update(App record){
        boolean success = appService.saveOrUpdate(record);
        if (success) {
            return ResultBean.ok(record);
        }
        return ResultBean.error();
    }

    @Log("删除")
    @PostMapping("delete")
    @ResponseBody
    public ResultBean delete(String id){
        List<String> ids = StrUtil.split(id, ',');
        boolean success = appService.removeByIds(ids);
        if (success) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }

    @Log("冻结")
    @PostMapping("disable")
    @ResponseBody
    public ResultBean disable(String id){
        List<String> ids = StrUtil.split(id, ',');
        List<App> apps = new ArrayList<>();
        for (String s : ids) {
            App app = new App();
            app.setId(Integer.parseInt(s));
            app.setDisabled(YesOrNo.YES);
            apps.add(app);
        }

        boolean success = appService.updateBatchById(apps);
        if (success) {
            return ResultBean.ok();
        }
        return ResultBean.error();
    }


}