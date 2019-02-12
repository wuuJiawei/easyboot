package com.easyboot.service.impl;

import com.easyboot.bean.UserRole;
import com.easyboot.dao.UserRoleDao;
import com.easyboot.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和用户关联的表 服务实现类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-05
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements IUserRoleService {

}
