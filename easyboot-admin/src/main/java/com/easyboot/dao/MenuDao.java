package com.easyboot.dao;

import com.easyboot.bean.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-04
 */
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> selectByUserId(Integer userid);

}
