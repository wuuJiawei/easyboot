package com.easyboot.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easyboot.dao.GeneratorDao;
import com.easyboot.service.IGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * GeneratorServiceImpl
 *
 * @name: GeneratorServiceImpl
 * @author: 12093
 * @date: 2019/1/17
 * @time: 14:36
 */
@Service
public class GeneratorServiceImpl implements IGeneratorService{

    @Autowired
    private GeneratorDao generatorDao;

    @Override
    public IPage<Map<String, Object>> queryList(Page<Map<String, Object>> page, String tableName) {
        return generatorDao.queryList(page, tableName);
    }

    @Override
    public Map<String, String> queryTable(String tableName) {
        return generatorDao.queryTable(tableName);
    }

    @Override
    public IPage<Map<String, Object>> queryColumns(Page<Map<String, Object>> page, String tableName) {
        return generatorDao.queryColumns(page, tableName);
    }



}