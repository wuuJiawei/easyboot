package com.easyboot.service;

import com.easyboot.bean.App;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-05
 */
public interface IAppService extends IService<App> {

    App findByAppKey(String appKey);
}
