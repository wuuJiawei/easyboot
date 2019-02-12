package com.easyboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * Created by 12093 on 2019/1/17.
 */
public interface IGeneratorService {

    IPage<Map<String, Object>> queryList(Page<Map<String, Object>> page, String tableName);

    Map<String, String> queryTable(String tableName);

    IPage<Map<String, Object>> queryColumns(Page<Map<String, Object>> page, String tableName);

}
