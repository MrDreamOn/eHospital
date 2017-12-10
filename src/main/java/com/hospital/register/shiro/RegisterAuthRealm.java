package com.hospital.register.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hospital.register.bean.Permission;
import com.hospital.register.bean.Role;
import com.hospital.register.bean.User;
import com.hospital.register.service.PermissionService;
import com.hospital.register.service.RoleService;
import com.hospital.register.service.UserService;

public class RegisterAuthRealm extends AuthorizingRealm {
	
    private static final Logger logger = LoggerFactory.getLogger(RegisterAuthRealm.class);


	@Autowired
    private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	//授权
    /* 
     * 授权的方法是在碰到<shiro:hasPermission>标签的时候调用的,它会去检测shiro框架中的权限(这里的permissions)是否包含有该标签的name值,如果有,里面的内容显示,如果没有,里面的内容不予显示(这就完成了对于权限的认证.)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		logger.info("授权doGetAuthorizationInfo!");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		User user = (User)principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<Role> roles = roleService.getRolesByUserId(user.getUserId());
        if (roles.size() > 0) {
            for (Role role : roles) {
            	info.addRole(role.getRoleName());//将角色放入shiro中.
            	List<Permission> permissions = permissionService.getPermissionsByRoleId(role.getRoleId());
                if (permissions.size() > 0) {
                    for (Permission permission : permissions) {
                    	info.addStringPermission(permission.getPermission());//将权限放入shiro中.
                    }
                }
            }
        }
        return info;
	}

	//认证.登录
    /* 
     *这个方法主要是做登录验证，就是去数据库里面校验用户是否存在，注意这里不需要进行秘密校验，shiro会帮我们做密码校验
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		logger.info("认证doGetAuthenticationInfo!");
		try {
            UsernamePasswordToken utoken = (UsernamePasswordToken)token;//获取用户输入的token
            String userName = utoken.getUsername();
            User user = userService.findUserByName(userName);
            if (user != null) {
                // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
                return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

}
