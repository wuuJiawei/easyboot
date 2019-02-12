package com.easyboot.controller;

import com.easyboot.common.ResultBean;
import com.easyboot.support.BaseController;
import com.easyboot.config.geetest.GeetestConfig;
import com.easyboot.config.geetest.GeetestLib;
import com.easyboot.config.log.annotation.Log;
import com.easyboot.config.log.utils.IPUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * LoginController
 *
 * @name: LoginController
 * @author: 12093
 * @date: 2019/1/2
 * @time: 23:22
 */
@Controller
public class LoginController extends BaseController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @Log("登录")
    @PostMapping("login")
    @ResponseBody
    public ResultBean doLogin(String username, String password, String code, boolean rememberMe){
        if (!verifyCaptcha()) {
            return ResultBean.error("验证失败，无法登陆");
        }

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            token.setRememberMe(rememberMe);
            SecurityUtils.getSubject().login(token);
            return ResultBean.ok();
        } catch (IncorrectCredentialsException ice) {
            return ResultBean.error("密码错误");
        } catch (UnknownAccountException uae) {
            return ResultBean.error("账号不存在");
        } catch (LockedAccountException e) {
            return ResultBean.error("账号被锁定");
        } catch (ExcessiveAttemptsException eae) {
            return ResultBean.error("操作频繁，请稍后再试");
        }
    }

    /**
     * 初始化极验，获取流水标识并设置状态码
     * @return
     */
    @ResponseBody
    @PostMapping("captcha/start")
    public ResultBean startCaptcha(String username){
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());

        String resStr = "{}";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>(3);
        param.put("user_id", username);
        //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("client_type", "web");
        //传输用户请求验证时所携带的IP
        param.put("ip_address", IPUtils.getIpAddr(request));

        //进行验证预处理
        int gtServerStatus = gtSdk.preProcess(param);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
        //将userid设置到session中
        request.getSession().setAttribute("captcha:userid", username);

        resStr = gtSdk.getResponseStr();
        return ResultBean.ok(resStr);
    }

    /**
     * 二次验证
     * @return
     */
    private boolean verifyCaptcha() {
        GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(),
                GeetestConfig.isnewfailback());

        String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLib.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);

        //从session中获取gt-server状态
        int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

        //从session中获取userid
        String userid = (String)request.getSession().getAttribute("captcha:userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid);
        param.put("client_type", "web");
        param.put("ip_address", IPUtils.getIpAddr(request));

        int gtResult = 0;

        if (gt_server_status_code == 1) {
            //gt-server正常，向gt-server进行二次验证
            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
            System.out.println(gtResult);
        } else {
            // gt-server非正常情况下，进行failback模式验证
            System.out.println("failback:use your own server captcha validate");
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
            System.out.println(gtResult);
        }

        if (gtResult == 1) {
            return true;
        }
        return false;
    }
}