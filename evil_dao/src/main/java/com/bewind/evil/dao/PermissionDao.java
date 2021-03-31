package com.bewind.evil.dao;

import com.github.pagehelper.Page;
import com.bewind.evil.pojo.Permission;

import java.util.List;

public interface PermissionDao {

    Page<Permission> findPage(String queryString);

    int add(Permission permission);

    Permission findById(int id);

    int update(Permission permission);

    int deleteById(int id);

    int queryPermissionAndRole(int id);

    List<Permission> findAll();

}
