package com.bewind.evil.dao;

import com.github.pagehelper.Page;
import com.bewind.evil.pojo.Menu;

import java.util.List;

/**
 * @version 1.0
 * @author： zzy
 * @date： 2020-09-04 18:23
 */public interface MenuDao {
    /**
     * 初始化菜单列表
     * @param username
     * @return
     */
    List<Menu> findMenuByUsername(String username);

    List<Menu> findAll();

    Integer[] findMenuIdsByRoleId(Integer id);
    /**
     * 增加菜单
     * @param menu
     */
    void add(Menu menu);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<Menu> findByCondition(String queryString);

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