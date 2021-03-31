package com.bewind.evil.controller;

import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.entity.Result;
import com.bewind.evil.pojo.Menu;
import com.bewind.evil.service.MenuService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @version 1.0
 * @author： zzy
 * @date： 2020-09-04 18:01
 */
@RestController
@RequestMapping("/menu")

public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 初始化菜单列表
     *
     * @return
     */
    @GetMapping("/findMenuByUsername")
    public Result findMenuByUsername() {
        // 获取登陆用户的认证信息
        User loginUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 登陆用户名
        String username = loginUser.getUsername();
        List<Menu> menuList = menuService.findMenuByUsername(username);
        return new Result(true, "初始化菜单成功", menuList);
    }

    /**
     * 查询所有菜单列表
     *
     * @return
     */
    @GetMapping("/findAll")
    @PreAuthorize("hasAnyAuthority('MENU_QUERY')")
    public Result findAll() {
        List<Menu> menuList = menuService.findAll();
        return new Result(true, "查询所有菜单列表成功", menuList);
    }

    /**
     * 回显角色菜单信息
     *
     * @param id
     * @return
     */
    @GetMapping("/findMenuIdsByRoleId")
    @PreAuthorize("hasAnyAuthority('MENU_QUERY')")
    public Result findMenuIdsByRoleId(Integer id) {
        Integer[] menuIds = menuService.findMenuIdsByRoleId(id);
        return new Result(true, "回显角色菜单信息成功", menuIds);
    }

    /**
     * 增加菜单
     */
    @RequestMapping("/add")
    @PreAuthorize("hasAnyAuthority('MENU_ADD')")
    public Result add(@RequestBody Menu menu) {
        menuService.add(menu);
        return new Result(true, "添加菜单成功");
    }

    /**
     * 分页查询
     */
    @RequestMapping("/findPage")
    @PreAuthorize("hasAnyAuthority('MENU_QUERY')")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<Menu> menuPageResult = menuService.findPage(queryPageBean);
        return new Result(true, "分页查询成功", menuPageResult);
    }

    /**
     * 查询父菜单信息
     */
    @RequestMapping("/findAllParent")
    @PreAuthorize("hasAnyAuthority('MENU_QUERY')")
    public Result findAllParent() {
        List<Menu> menus = menuService.findAllParent();
        return new Result(true, "查询成功", menus);
    }

    /**
     * 删除菜单
     */
    @RequestMapping("/deleteById")
    @PreAuthorize("hasAnyAuthority('MENU_DELETE')")
    public Result deleteById(int id) {
        menuService.deleteById(id);
        return new Result(true,"删除成功");
    }

    /**
     * 查询单个菜单
     */
    @RequestMapping("/findById")
    @PreAuthorize("hasAnyAuthority('MENU_QUERY')")
    public Result findById(int id) {
        Menu menu = menuService.findById(id);
        return new Result(true,"查询菜单成功",menu);
    }


    /**
     * 编辑菜单
     */
    @RequestMapping("/update")
    @PreAuthorize("hasAnyAuthority('MENU_EDIT')")
    public Result update(@RequestBody Menu menu) {
        menuService.update(menu);
        return new Result(true,"编辑成功");
    }
}

