package com.easyboot.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * \* 密码校验比对
 * \* @name: CredentialsMatcher
 * \* @author: 12093
 * \* @date: 2018/8/10
 * \* @time: 9:46
 * \
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        //获得用户输入的密码:
        String inPassword = new String(utoken.getPassword());
        inPassword = new PasswordHelper().encryptPassword(utoken.getUsername().toString(), inPassword).getNewPassword();
        //获得数据库中的密码
        String dbPassword = (String) info.getCredentials();
        //进行密码的比对
        return this.equals(inPassword, dbPassword);
    }

}