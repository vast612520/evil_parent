package com.bewind.evil.security;


import com.bewind.evil.pojo.Permission;
import com.bewind.evil.pojo.Role;
import com.bewind.evil.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SpringSecurityUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据登陆用户名称查询用户权限信息
        com.bewind.evil.pojo.User userInDB = userService.findByUsername(username);
        //判断查询是否有数据
        if (null != userInDB) {
            //创建权限集合
            List<GrantedAuthority> authorities = new ArrayList<>();
            //获取用户的角色
            Set<Role> roles = userInDB.getRoles();
            if (null != roles) {
                GrantedAuthority sga = null;
                //遍历用户的角色与拥有的权限
                for (Role role : roles) {
                    //角色名, 授予角色
                    sga = new SimpleGrantedAuthority(role.getKeyword());
                    //授予权限, 这个角色下所拥有的权限
                    Set<Permission> permissions = role.getPermissions();
                    if (null != permissions){
                        //遍历所有的权限
                        for (Permission permission : permissions) {
                            //授予权限
                            sga = new SimpleGrantedAuthority(permission.getKeyword());
                            authorities.add(sga);
                        }
                    }
                }
            }

            //创建权限用户
            User securityUser = new User(username, userInDB.getPassword(), authorities);
            //返回
            return securityUser;
        }
        return null;
    }
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 加密密码
        System.out.println(encoder.encode("admin123"));
        // 校验密码, 第1个参数为明文，第2个为密文
        //System.out.println(encoder.matches("1234",
        //        "$2a$10$C2I8PHWnBtqMJlvKD7DsCuP9Kl4uQT4TIqBTgda1y/Pekp6Tb/4GO"));
    }
}
