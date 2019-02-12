package com.easyboot.service;

import com.easyboot.bean.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-05
 */
public interface IRoleService extends IService<Role> {

    List<Role> listByUserid(int userId);
}
