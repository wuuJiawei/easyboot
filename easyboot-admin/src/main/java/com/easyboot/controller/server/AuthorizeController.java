package com.easyboot.controller.server;

import cn.hutool.core.util.StrUtil;
import com.easyboot.common.ResultBean;
import com.easyboot.bean.App;
import com.easyboot.common.OAuth2Constants;
import com.easyboot.config.log.annotation.Log;
import com.easyboot.config.log.utils.IPUtils;
import com.easyboot.support.BaseController;
import com.easyboot.service.IAppService;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

/**
 * AuthorizeCobtroller
 *
 * @name: AuthorizeCobtroller
 * @author: 12093
 * @date: 2019/1/6
 * @time: 21:54
 */
@Controller
@RequestMapping("connect/oauth2")
public class AuthorizeController extends BaseController{

    @Autowired
    private IAppService appService;


    /**
     * 进行授权，校验授权许可，通过后重定向到客户端地址
     * @return
     * @throws OAuthSystemException
     * @throws OAuthProblemException
     */
    @RequestMapping("authorize")
    @Log("校验授权许可，进行授权")
    public ModelAndView authorize() throws OAuthSystemException, OAuthProblemException {
        ModelAndView mav = new ModelAndView();
        boolean error = false;

        //构建OAuth请求
        OAuthAuthzRequest oAuthzRequest = new OAuthAuthzRequest(request);
        //获取OAuth客户端Id
        String clientId = oAuthzRequest.getClientId();

        // 进行许可校验
        App app = appService.findByAppKey(clientId);
        String errorMsg = verifyApp(app, null);
        if (StrUtil.isNotEmpty(errorMsg)) {
            mav.addObject(OAuth2Constants.OAUTH_AUTHORIZE_FAILED_KEY, errorMsg);
            mav.setViewName("auth/authorizeFailed");
            return mav;
        }

        // 生成授权码
        String authCode = null;
        String responseType = oAuthzRequest.getResponseType();
        // responseType仅支持CODE和TOKEN

        if (StrUtil.equalsIgnoreCase(responseType, ResponseType.CODE.toString())){
            OAuthIssuerImpl oAuthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            authCode = oAuthIssuerImpl.authorizationCode();

        }

        // 构建OAuth响应
        OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);

        // 设置授权码
        builder.setCode(authCode);

        // 获取客户端的重定向地址
        String redirectUri = oAuthzRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

        // 构建响应
        OAuthResponse response = builder.location(redirectUri).buildBodyMessage();
        // 返回ResponseEntity响应
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setLocation(new URI(response.getLocationUri()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mav.addObject(OAuth.OAUTH_CLIENT_ID, clientId);
        mav.addObject(OAuth.OAUTH_CLIENT_SECRET, app.getAppsecret());
        mav.addObject(OAuth.OAUTH_CODE, authCode);
        mav.addObject(OAuth.OAUTH_STATE, oAuthzRequest.getParam(OAuth.OAUTH_STATE));
        mav.setViewName("redirect:" + redirectUri);
        return mav;
    }

    @Log("获取Access_Token")
    @RequestMapping("access_token")
    @ResponseBody
    public ResultBean accessToken() throws OAuthProblemException, OAuthSystemException {
        OAuthAuthzRequest oAuthRequest = new OAuthAuthzRequest(request);
        String clientId = oAuthRequest.getClientId();
        String clientSecreet = oAuthRequest.getClientSecret();
        String authCode = oAuthRequest.getParam(OAuth.OAUTH_CODE);

        // 进行许可校验
        App app = appService.findByAppKey(clientId);
        String errorMsg = verifyApp(app, clientSecreet);

        if (StrUtil.isNotEmpty(errorMsg)) {
            return ResultBean.error(errorMsg);
        } else {
            // 校验类型和授权码
            if (oAuthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE)) {


            }

        }



        return ResultBean.ok();
    }

    /**
     * 多项检验
     * @param clientId
     * @return
     */
    private String verifyApp(App app, String clientSecret){
        String msg = "";
        if (app == null) {
            msg = "无效的client_id";
        } else {
            // 校验IP
            String ip = app.getIp(),
                    requestIp = IPUtils.getIpAddr(request);
            if (StrUtil.isEmpty(msg) && !StrUtil.equals(ip, requestIp)) {
                msg = "不匹配的IP";
            }

            // 校验授权时长
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime endTime = app.getEndTime();
            if (StrUtil.isEmpty(msg) && now.isAfter(endTime)) {
                msg = "授权已过期";
            }

            // 校验secret
            String secret = app.getAppsecret();
            if (StrUtil.isNotEmpty(clientSecret) && StrUtil.isEmpty(msg) && !StrUtil.equals(secret, clientSecret)){
                msg = "secret认证失败";
            }
        }
        return msg;
    }


}