package com.easyboot.service.impl;

import com.easyboot.bean.Log;
import com.easyboot.dao.LogDao;
import com.easyboot.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-28
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, Log> implements ILogService {

}
