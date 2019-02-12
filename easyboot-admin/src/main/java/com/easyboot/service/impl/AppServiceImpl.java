package com.easyboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyboot.bean.App;
import com.easyboot.dao.AppDao;
import com.easyboot.service.IAppService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-05
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppDao, App> implements IAppService {

    @Autowired
    private IAppService service;


    @Override
    public App findByAppKey(String appKey) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("appkey", appKey);
        return service.getOne(wrapper);
    }
}
