package com.easyboot.service.impl;

import com.easyboot.bean.Menu;
import com.easyboot.dao.MenuDao;
import com.easyboot.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> listByUserId(int userid) {
        return menuDao.selectByUserId(userid);
    }
}
