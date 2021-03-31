package com.bewind.evil.service;

import com.bewind.evil.entity.PageResult;
import com.bewind.evil.entity.QueryPageBean;
import com.bewind.evil.exception.EvilException;
import com.bewind.evil.pojo.Permission;

import java.util.List;


public interface PermissionService {


    PageResult findPage(QueryPageBean queryPageBean);

    void add(Permission permission) throws EvilException;

    Permission findById(int id) throws EvilException;

    void update(Permission permission) throws EvilException;

    void deleteById(int id) throws EvilException;

    List<Permission> findAll();

}
