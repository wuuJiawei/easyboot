package com.easyboot.support;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.easyboot.common.ResultBean;
import com.easyboot.common.layui.LayTable;
import com.easyboot.common.layui.LayTableArg;
import com.easyboot.config.log.annotation.Log;
import com.easyboot.config.log.annotation.LogSubject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * BaseCrudController
 *
 * @name: BaseCrudController
 * @author: 12093
 * @date: 2019/1/18
 * @time: 13:46
 */
public abstract class BaseCrudController<T, S extends IService<T>> extends BaseController {

    /**
     * 获得服务层
     * @return
     */
    protected abstract S getService();

    /**
     * 获取日志对象
     * @return
     */
    protected abstract cn.hutool.log.Log getLog();

    /**
     * 加载完成后通过反射动态设置Log注解的完整value
     */
    @PostConstruct
    public void initCrudController() {
        getLog().info(getService().getClass().getName() + "初始化成功");

        Class controllerClass = getClass();
        LogSubject logSubject = (LogSubject) controllerClass.getAnnotation(LogSubject.class);
        String subjectName = "";
        if (logSubject != null) {
            subjectName = logSubject.value();
        }

        try {
            Method[] methods = controllerClass.getMethods();
            for (Method method : methods) {
                Log log = method.getAnnotation(Log.class);
                if (log == null) { continue; }
                String logValue = log.value();
                //获取这个代理实例所持有的 InvocationHandler
                InvocationHandler h = Proxy.getInvocationHandler(log);
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                Field hField = null;
                hField = h.getClass().getDeclaredField("memberValues");
                // 因为这个字段是 private final 修饰，所以要打开权限
                hField.setAccessible(true);
                Map memberValues = (Map) hField.get(h);
                // 修改 权限注解value 属性值
                memberValues.put("value", logValue + subjectName);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据查询前
     * @param arg
     * @param wrapper
     * @return
     */
    protected LayTable<T> queryBefore(LayTableArg arg, QueryWrapper<T> wrapper){
        return null;
    }

    @RequestMapping("query")
    @ResponseBody
    @Log("查询")
    public LayTable<T> query(LayTableArg arg){
        QueryWrapper<T> wrapper = new QueryWrapper<T>();

        LayTable<T> beforeResult = queryBefore(arg, wrapper);
        if (beforeResult != null) {
            return beforeResult;
        }

        if (StrUtil.isNotBlank(arg.getParam())) {
            wrapper.like("name", arg.getParam());
        }

        String field = StrUtil.isNotBlank(arg.getField()) ? StrUtil.toSymbolCase(arg.getField(), '_') : "orders";
        wrapper.orderBy(true, arg.isAsc(), field);

        Page<T> page = new Page<>(arg.getPage(), arg.getLimit());
        IPage<T> list = getService().page(page, wrapper);

        return new LayTable<T>(list.getRecords(), (int)list.getTotal());
    }

    /**
     * 数据插入前
     * @param m
     * @return
     */
    protected ResultBean insertBefore(T m) {
        return null;
    }

    /**
     * 数据库插入后
     * @param m
     * @return
     */
    protected ResultBean insertAfter(T m) {
        return null;
    }

    @PostMapping("insert")
    @ResponseBody
    @Log("新增")
    public ResultBean insert(T m) {
        ResultBean beforeResult = insertBefore(m);
        if (beforeResult != null) {
            return beforeResult;
        }

        try {
            if (getService().save(m)) {
                ResultBean afterResult = insertAfter(m);
                if (afterResult != null) {
                    return afterResult;
                }
                return ResultBean.ok(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
            getLog().error(e.getMessage());
            ResultBean.error("添加数据出现异常");
        }
        return ResultBean.error();
    }

    /**
     * 数据删除前
     *
     * @param ids
     * @param wrapper
     * @return
     */
    protected ResultBean deleteBefore(String ids, Wrapper<T> wrapper) {
        return null;
    }

    /**
     * 数据删除后
     *
     * @param ids
     * @return
     */
    protected ResultBean deleteAfter(String ids) {
        return null;
    }

    @PostMapping("delete")
    @ResponseBody
    @Log("删除")
    public ResultBean delete(String ids) {
        List<String> idArr = StrUtil.split(ids, ',');
        if (ArrayUtil.isEmpty(idArr)){
            return ResultBean.error("请选择要删除的数据");
        }

        QueryWrapper<T> wrapper = new QueryWrapper<>();
        ResultBean beforeResult = deleteBefore(ids, wrapper);
        if (beforeResult != null) {
            return beforeResult;
        }

        try {
            if (getService().removeByIds(idArr)){
                ResultBean afterResult = deleteAfter(ids);
                if (afterResult != null) {
                    return afterResult;
                }
                return ResultBean.ok();
            }
        }  catch (Exception e) {
            e.printStackTrace();
            getLog().error(e.getMessage());
            ResultBean.error("删除数据出现异常");
        }
        return ResultBean.error();
    }

    /**
     * 数据更新前
     * @param m
     * @return
     */
    protected ResultBean updateBefore(T m) {
        return null;
    }

    /**
     * 数据更新后
     * @param m
     * @return
     */
    protected ResultBean updateAfter(T m) {
        return null;
    }

    @PostMapping("update")
    @ResponseBody
    @Log("更新")
    public ResultBean update(T m) {
        if (m == null) {
            return ResultBean.error("请指定要修改的数据");
        }

        ResultBean beforeResult = updateBefore(m);
        if (beforeResult != null) {
            return beforeResult;
        }

        try {
            if (getService().updateById(m)){
                ResultBean afterResult = updateAfter(m);
                if (afterResult != null) {
                    return afterResult;
                }
                return ResultBean.ok(m);
            }
        }  catch (Exception e) {
            e.printStackTrace();
            getLog().error(e.getMessage());
            ResultBean.error("更新数据出现异常");
        }
        return ResultBean.error();
    }

}