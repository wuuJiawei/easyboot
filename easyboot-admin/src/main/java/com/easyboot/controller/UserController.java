package com.easyboot.controller;

import cn.hutool.log.LogFactory;
import com.easyboot.bean.User;
import com.easyboot.service.IUserService;
import com.easyboot.config.log.annotation.LogSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import com.easyboot.support.BaseCrudController;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-29
 */

@Controller
@RequestMapping("user")
@LogSubject("用户")
public class UserController extends BaseCrudController<User, IUserService>  {

    @Autowired
    private IUserService service;

    @Override
    protected IUserService getService() {
        return service;
    }

    @Override
    protected cn.hutool.log.Log getLog() {
        return LogFactory.get();
    }

    @GetMapping("/")
    public String list(){
      return "user/list";
    }

}

