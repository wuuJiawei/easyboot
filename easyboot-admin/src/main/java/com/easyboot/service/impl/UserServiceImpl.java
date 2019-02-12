package com.easyboot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyboot.bean.User;
import com.easyboot.dao.UserDao;
import com.easyboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Override
    public User selectByUsernameAndPwd(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", username);
        if (StrUtil.isNotBlank(password)) {
            wrapper.eq("password", password);
        }
        return getOne(wrapper);
    }
}
