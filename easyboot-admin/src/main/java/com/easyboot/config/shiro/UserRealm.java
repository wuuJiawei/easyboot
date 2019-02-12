package com.easyboot.config.shiro;

import cn.hutool.core.util.StrUtil;
import com.easyboot.bean.Menu;
import com.easyboot.bean.Role;
import com.easyboot.bean.User;
import com.easyboot.bean.enums.YesOrNo;
import com.easyboot.service.IMenuService;
import com.easyboot.service.IRoleService;
import com.easyboot.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * \* 通过realm进行身份验证
 * \* @name: UserRealm
 * \* @author: 12093
 * \* @date: 2018/8/7
 * \* @time: 16:18
 * \
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;

    /**
     * 获取身份信息，通过该方法从数据库获取该用户的权限和角色信息
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int userId = user.getUserId();
        // 通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 权限
        List<Menu> menuList = menuService.listByUserId(userId);
        Set<String> menuSet = new HashSet<>();
        for (Menu menu : menuList) {
            String auth = menu.getPermission();
            if (StrUtil.isNotEmpty(auth)) {
                menuSet.add(auth);
            }
        }
        info.setStringPermissions(menuSet);
        // 角色
        List<Role> roleList = roleService.listByUserid(userId);
        Set<String> roleSet = new HashSet<>();
        for (Role role : roleList) {
            roleSet.add(role.getRoleName());
        }
        info.setRoles(roleSet);

        return info;
    }

    /**
     * 登陆认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        User user = userService.selectByUsernameAndPwd(username, null);
        if (user == null) {
            // 账号不存在
            throw new UnknownAccountException();
        }
        if (user.getLocked() == YesOrNo.YES) {
            // 账号被锁定
            throw new LockedAccountException();
        }
        // 加载进shiro
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                //此处传入user对象，realm会将该对象存储到session中
                user,
                user.getPassword(),
                //realm name
                getName()
        );
        return authenticationInfo;
    }
}