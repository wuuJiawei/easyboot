package com.easyboot.controller;

import com.easyboot.support.BaseController;
import com.easyboot.config.log.annotation.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * IndexController
 *
 * @name: IndexController
 * @author: 12093
 * @date: 2019/1/2
 * @time: 23:22
 */
@Controller
public class IndexController extends BaseController {


    @Log("访问主页")
    @GetMapping({"/index", "/"})
    public String index(){
        return "index";
    }



}