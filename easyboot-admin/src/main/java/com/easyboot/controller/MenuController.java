package com.easyboot.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.easyboot.common.ResultBean;
import com.easyboot.bean.Menu;
import com.easyboot.bean.enums.YesOrNo;
import com.easyboot.support.BaseController;
import com.easyboot.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 武佳伟
 * @since 2019-01-04
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("sidebar")
    @ResponseBody
    public ResultBean getSideBar(){
        List<Menu> menus = menuService.list(new QueryWrapper<Menu>().eq("disabled", YesOrNo.NO).orderByAsc("orders"));
        List<Map> menuList = getMenuTree(menus, 0);
        return ResultBean.ok(menuList);
    }

    /**
     * 递归生成多级菜单
     * @return
     */
    private List<Map> getMenuTree(List<Menu> menus, int pid){
        List<Map> list = new ArrayList<>();
        for (Menu menu : menus) {
            if (pid == menu.getPid()) {
                Map map = new HashMap<>(4);
                map.put("name", menu.getMenuName());
                map.put("icon", StrUtil.isNotEmpty(menu.getIcon()) ? menu.getIcon() : "layui-icon layui-icon-star");
                String url = menu.getUrl();
                if (url.equals("/")) {
                    url = "javascript:;";
                } else if (url.indexOf("http") == -1) {
                    url = basePath() + url;
                }
                map.put("url", url);
                List<Map> subMenus = getMenuTree(menus, menu.getMenuId());
                if (subMenus.size() > 0) {
                    map.put("subMenus", subMenus);
                }
                list.add(map);
            }
        }
        return list;
    }
}
