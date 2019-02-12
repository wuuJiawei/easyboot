package com.easyboot.dao;

import com.easyboot.bean.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-05
 */
public interface RoleDao extends BaseMapper<Role> {

    List<Role> selectByUserId(Integer userid);

}
