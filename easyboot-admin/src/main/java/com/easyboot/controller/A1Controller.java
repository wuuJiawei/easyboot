package com.easyboot.controller;

import cn.hutool.log.LogFactory;
import com.easyboot.bean.A1;
import com.easyboot.service.IA1Service;
import com.easyboot.config.log.annotation.LogSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import com.easyboot.support.BaseCrudController;

/**
 * <p>
 * a1测试 前端控制器
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-28
 */
@Controller
@RequestMapping("/a1")
@LogSubject("a1测试")
public class A1Controller extends BaseCrudController<A1, IA1Service>  {

    @Autowired
    private IA1Service service;

    @Override
    protected IA1Service getService() {
        return service;
    }

    @Override
    protected cn.hutool.log.Log getLog() {
        return LogFactory.get();
    }

    @GetMapping("list")
    public String list(){
      return "a1/list";
    }

}
