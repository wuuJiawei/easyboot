package com.easyboot.controller;

import cn.hutool.log.LogFactory;
import com.easyboot.bean.Test;
import com.easyboot.service.ITestService;
import com.easyboot.config.log.annotation.LogSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import com.easyboot.support.BaseCrudController;

/**
 * <p>
 * 测试 前端控制器
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-29
 */

@Controller
@RequestMapping("test")
@LogSubject("测试")
public class TestController extends BaseCrudController<Test, ITestService>  {

    @Autowired
    private ITestService service;

    @Override
    protected ITestService getService() {
        return service;
    }

    @Override
    protected cn.hutool.log.Log getLog() {
        return LogFactory.get();
    }

    @GetMapping("/")
    public String list(){
      return "test/list";
    }

}

