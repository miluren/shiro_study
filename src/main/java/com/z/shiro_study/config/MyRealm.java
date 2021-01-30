package com.z.shiro_study.config;

import com.z.shiro_study.dao.PermissionDao;
import com.z.shiro_study.dao.RoleDao;
import com.z.shiro_study.dao.UserDao;
import com.z.shiro_study.entity.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 继承AuthorizingRealm（实现了Realm接口的类）
 * @author Mr zhang
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;


    public MyRealm() {

    }

    /**
     * 返回Realm的自定义名称
     * @return 名称
     */
    @Override
    public String getName() {
        return "myRealm";
    }

    /**
     * 获取授权信息
     * @param principals  当事人（用户名），从 doGetAuthenticationInfo返回的第一个变量获取
     * @return 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前用户的用户名
        String username = (String) principals.iterator().next();
        // 根据用户名查询当前用户的权限列表
        Set<String> roles = roleDao.queryRoleNamesByUsername(username);
        // 根据用户名查询当前用户的权限列表
        Set<String> ps = permissionDao.queryPermissionByUsername(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 把角色列表添加到 SimpleAuthorizationInfo
        //  info.addRoles(); 添加
        info.setRoles(roles);
        // 把权限列表添加到 SimpleAuthorizationInfo
        info.setStringPermissions(ps);

        return info;
    }

    /**
     * 获取认证信息
     * 给 doGetAuthorizationInfo 传递参数
     * @param token 就是传递 subject.login(token)
     * @return 认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken token1 = (UsernamePasswordToken) token;
        String username = token1.getUsername();

        User user = userDao.queryUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 验证信息
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                // 用户名
                username,
                // 从数据库查询出来的安全密码
                user.getUserPwd(),
                // 返回盐
                ByteSource.Util.bytes(user.getPwdSalt()),
                // Realm 名字
                getName());

        return info;
    }
}
