package com.easyboot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyboot.bean.Log;
import com.easyboot.common.layui.LayTable;
import com.easyboot.common.layui.LayTableArg;
import com.easyboot.service.ILogService;
import com.easyboot.config.log.annotation.LogSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import com.easyboot.support.BaseCrudController;

/**
 * <p>
 * 系统日志 前端控制器
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-28
 */
@Controller
@RequestMapping("/log")
@LogSubject("系统日志")
public class LogController extends BaseCrudController<Log, ILogService>  {

    @Autowired
    private ILogService service;

    @Override
    protected ILogService getService() {
        return service;
    }

    @Override
    protected cn.hutool.log.Log getLog() {
        return LogFactory.get();
    }

    @GetMapping("/")
    public String list(){
      return "sys_log/list";
    }

    @Override
    protected LayTable<Log> queryBefore(LayTableArg arg, QueryWrapper<Log> wrapper){
        if (StrUtil.isNotBlank(arg.getParam())) {
            wrapper.like("message", arg.getParam());
        }

        Page<Log> page = new Page<>(arg.getPage(), arg.getLimit());
        IPage<Log> list = getService().page(page, wrapper);

        return new LayTable<Log>(list.getRecords(), (int)list.getTotal());
    }



}
