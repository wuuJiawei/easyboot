package com.easyboot.config.shiro;

import com.easyboot.bean.User;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * \* PasswordHelper
 * \* @name: PasswordHelper
 * \* @author: 12093
 * \* @date: 2018/8/9
 * \* @time: 23:27
 * \
 */
public class PasswordHelper {

    private String newPassword = null;

    public PasswordHelper(){
        newPassword = null;
    }

    public PasswordHelper encryptPassword(User user) {
        Md5Hash md5Hash= new Md5Hash(
                user.getPassword(),
                user.getPhone(),
                3);
        newPassword = md5Hash.toString();
        return this;
    }

    public void print(){
        System.out.println(newPassword);
    }

    public PasswordHelper encryptPassword(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        encryptPassword(user);
        return this;
    }

    public String getNewPassword(){
        return newPassword;
    }

    public static void main(String[] args) {
        PasswordHelper passwordHelper = new PasswordHelper();
        User user = new User();
        user.setPhone("admin");
        user.setPassword("admin");
        passwordHelper.encryptPassword(user).print();
    }
}