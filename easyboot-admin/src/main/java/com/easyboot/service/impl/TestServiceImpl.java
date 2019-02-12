package com.easyboot.service.impl;

import com.easyboot.bean.Test;
import com.easyboot.dao.TestDao;
import com.easyboot.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试 服务实现类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-29
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestDao, Test> implements ITestService {

}
