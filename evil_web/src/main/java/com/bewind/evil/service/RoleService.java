package com.bewind.evil.service;

import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.exception.EvilException;
import com.bewind.evil.pojo.Role;

import java.util.Set;

/**
 * Description：用户数据维护
 * User：JuZhao
 * Date：2020-11-06
 */
public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    Set<Role> findAll();

    /**
     * 分页查询角色列表
     * @param queryPageBean
     * @return
     */
    PageResult<Role> findPage(QueryPageBean queryPageBean);

    /**
     * 添加角色信息
     * @param role
     * @param permissionIds
     * @param menuIds
     */
    void add(Role role, Integer[] permissionIds, Integer[] menuIds)throws EvilException;

    /**
     * 根据ID删除角色信息
     * @param id
     */
    void deleteById(Integer id) throws EvilException;

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    Role findById(Integer id);

    void update(Role role, Integer[] permissionIds, Integer[] menuIds) throws EvilException;
}
