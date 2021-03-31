package com.bewind.evil.service;

import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.Menu;

import java.util.List;

/**
 * @version 1.0
 * @author： zzy
 * @date： 2020-09-04 18:04
 */
public interface MenuService {
    /**
     * 初始化菜单列表
     * @param username
     * @return
     */
    List<Menu> findMenuByUsername(String username);

    List<Menu> findAll();

    /**
     * 回显角色菜单信息
     * @param id
     * @return
     */
    Integer[] findMenuIdsByRoleId(Integer id);

    /**
     * 增加菜单
     * @param menu
     */
    void add(Menu menu);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<Menu> findPage(QueryPageBean queryPageBean);

    /**
     * 查询父菜单信息
     * @return
     */
    List<Menu> findAllParent();

    /**
     * 删除菜单
     * @param id
     */
    void deleteById(int id);

    /**
     * 查询单个菜单
     * @param id
     * @return
     */
    Menu findById(int id);

    /**
     * 编辑菜单
     * @param menu
     */
    void update(Menu menu);}
