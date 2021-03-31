package com.bewind.evil.controller;



import com.bewind.evil.constant.MessageConstant;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.entity.Result;
import com.bewind.evil.pojo.User;
import com.bewind.evil.service.UserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Description：用户数据维护
 * User：
 * Date：2020-11-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户名
     * @return
     */
    @GetMapping("/getUsername")

    public Result getUsername(){
        //从权限中获取
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //打印一下用户名
        System.out.println("登陆的用户名:" + user.getUsername());
        return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
    }

    /**
     * 分页查询用户
     * @param queryPageBean
     * @return
     */
    @PostMapping("findPage")
    @PreAuthorize("hasAnyAuthority('USER_QUERY')")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        //调用服务findPage方法，传入QueryPageBean，返回PageResult<User>
        PageResult<User> pageResult = userService.findPage(queryPageBean);
        //封装结果集（flag、message、data）并返回
        return new Result(true, MessageConstant.QUERY_USER_SUCCESS, pageResult);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("addUser")
    @PreAuthorize("hasAnyAuthority('USER_ADD')")
    public Result addUser(@RequestBody User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 加密密码
        String password = encoder.encode("123456");
        //调用业务层addUser方法，参数传入User
        user.setPassword(password);
        userService.addUser(user);
        //封装结果集（flag，message）并返回
        return new Result(true, MessageConstant.ADD_USER_SUCCESS);
    }

    /**
     * 修改用户密码
     */
    @PostMapping("/uploadPassword")
    public Result uploadPassword(String userName,String password,String newPassword,String queNewPassword){
        //密码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //从数据查询原密码
        String formerPassword=userService.queryPassword(userName);
        //默认原密码不对
        boolean flag=false;
        //两次新密码输入是否正确
        if(!queNewPassword.equals(newPassword)){
            return new Result(false,"两次密码输入不一致,请检查后输入");
        }
        if (encoder.matches(password,formerPassword)) {
            //加密新密码
            String s = encoder.encode(newPassword);
            User user = new User();
            user.setPassword(s);
            user.setUsername(userName);
            //修改密码
            int cnt = userService.updateUser(user);
            if (cnt>0){
                flag = true;
            }
        }
        return new Result(flag,flag?"密码修改成功,请重新登录":"原密码错误,请检查后重新输入");
    }

    /**
     * 根据用户id查询关联的角色id
     * @param id
     * @return
     */
    @PostMapping("checkRoleIdByUserId")
    @PreAuthorize("hasAnyAuthority('USER_QUERY')")
    public Result checkRoleIdByUserId(int id) {
        //调用服务CheckRoleIdByUserId方法，参数传入用户id，返回角色id集合
        List<Integer> roleIds = userService.checkRoleIdByUserId(id);
        //封装结果集（flag，message，data）并返回
        return new Result(true, MessageConstant.QUERY_ROLE_SUCCESS, roleIds);
    }

    /**
     * 关联用户id与角色id
     * @param roleIds
     * @param user
     * @return
     */
    @PostMapping("/updateRoleIdByUserId")
    @PreAuthorize("hasAnyAuthority('USER_EDIT')")
    public Result updateRoleIdByUserId(Integer[] roleIds,@RequestBody User user) {
        int userId = user.getId();
        //调用服务UpdateRoleIdByUserId方法，传入角色id数组，用户id
        userService.UpdateRoleIdByUserId(roleIds, userId);
        //封装结果集并返回
        return new Result(true, MessageConstant.EDIT_ROLE_SUCCESS);
    }

    /**
     * 根据用户id查询用户数据
     * @param userId
     * @return
     */
    @GetMapping("/findById")
    @PreAuthorize("hasAnyAuthority('USER_QUERY')")
    public Result findById(int userId) {
        //调用服务findById方法，参数传入用户id，返回User
        User user = userService.findById(userId);
        //封装结果集（flag，message，data）并返回
        return new Result(true, MessageConstant.QUERY_USER_SUCCESS, user);
    }

    /**
     * 更新用户数据
     * @param user
     * @return
     */
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('USER_EDIT')")
    public Result update(@RequestBody User user) {
        //调用服务updateUser方法，参数User
        userService.updateUser(user);
        //封装结果集（flag，message）
        return new Result(true, MessageConstant.EDIT_USER_SUCCESS);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("/deleteById")
    @PreAuthorize("hasAnyAuthority('USER_DELETE')")
    public Result deleteById(int id) {
        //调用服务deleteById方法，参数传入用户id
        userService.deleteById(id);
        //封装结果集（flag，message）并返回
        return new Result(true, MessageConstant.DELETE_USER_SUCCESS);
    }
}
