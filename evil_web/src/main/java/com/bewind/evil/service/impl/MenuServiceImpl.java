package com.bewind.evil.service.impl;


import com.bewind.evil.dao.MenuDao;
import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.pojo.Menu;
import com.bewind.evil.service.MenuService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 1.0
 * @author： zzy
 * @date： 2020-09-04 18:08
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    /**
     * 初始化菜单列表
     * @param username
     * @return
     */
    @Override
    public List<Menu> findMenuByUsername(String username) {
        return menuDao.findMenuByUsername(username);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    /**
     * 回显角色菜单信息
     * @param id
     * @return
     */
    @Override
    public Integer[] findMenuIdsByRoleId(Integer id) {
        return menuDao.findMenuIdsByRoleId(id);
    }
    /**
     * 增加菜单
     * @param menu
     */
    @Override
    public void add(Menu menu) {
        menuDao.add(menu);
    }

    @Override
    public PageResult<Menu> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //模糊查询拼接
        //判断是否有查询条件
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())) {
            //查询条件，拼接
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<Menu> page=menuDao.findByCondition(queryPageBean.getQueryString());
        PageResult<Menu> pageResult=new PageResult<>(page.getTotal(),page.getResult());
        return pageResult;
    }

    /**
     * 查询父菜单信息
     * @return
     */
    @Override
    public List<Menu> findAllParent() {
        return menuDao.findAllParent();
    }

    /**
     * 删除菜单
     * @param id
     */
    @Override
    public void deleteById(int id) {
        menuDao.deleteById(id);
    }

    /**
     * 查询单个菜单
     * @param id
     * @return
     */
    @Override
    public Menu findById(int id) {
        return menuDao.findById(id);
    }

    /**
     * 编辑菜单
     * @param menu
     */
    @Override
    public void update(Menu menu) {
        menuDao.update(menu);
    }}