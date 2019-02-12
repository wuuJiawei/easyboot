package com.easyboot.service;

import com.easyboot.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-29
 */
public interface IUserService extends IService<User> {

    User selectByUsernameAndPwd(String username, String password);
}
