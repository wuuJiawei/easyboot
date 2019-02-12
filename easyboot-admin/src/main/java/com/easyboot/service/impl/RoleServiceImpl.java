package com.easyboot.service.impl;

import com.easyboot.bean.Role;
import com.easyboot.dao.RoleDao;
import com.easyboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

    @Autowired
    private RoleDao dao;

    @Override
    public List<Role> listByUserid(int userId) {
        return dao.selectByUserId(userId);
    }
}
