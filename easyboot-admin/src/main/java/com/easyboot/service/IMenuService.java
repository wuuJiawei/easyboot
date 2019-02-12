package com.easyboot.service;

import com.easyboot.bean.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-04
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> listByUserId(int userid);

}
